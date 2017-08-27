package com.fbm.controllers.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fbm.domain.*;
import com.fbm.constants.ErrorCode;
import com.fbm.services.ResponseService;
import com.fbm.services.TeamService;
import com.fbm.services.UserCardService;
import com.fbm.services.UserService;
import com.fbm.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Mocart on 07-Aug-17.
 */
@RestController
@RequestMapping("/")
public class RewardController {
    private static final int COUNT_OF_CARDS = 2;
    private static final int COUNT_OF_REWARD = 3;

    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserCardService userCardService;
    @Autowired
    private ResponseService responseService;

    /**
     * Gets #COUNT_OF_REWARD random cards for user if last reward was got at previous period (not this day)
     * @param userId The user to be awarded)
     * @return #COUNT_OF_REWARD random cards of the common set of cards (not depends on user has them)
     * @throws JsonProcessingException
     */
    @RequestMapping(path = "/reward/byId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getRewardForUser(@PathVariable long userId) { //todo add exception handler and delete exception from the signature
        User user = userService.getById(userId);

        if (checkLastRewardDate(user)) {
            return responseService.buildErrorResponse(HttpStatus.FORBIDDEN, ErrorCode.UNFINISHED_PERIOD);
        }

        List<Card> rewardCards = geRewardCards();

        for (Card card : rewardCards) {
            UserCard userCard = userCardService.getByUserIdAndCardIds(userId, card.getCardId());
            if (userCard == null) {
                userCard = buildNewUserCardRelation(user, card);
                userCardService.addUserCard(userCard);
            }
        }

        user.setLastRewardDate(System.currentTimeMillis());
        userService.updateUser(user);

        return responseService.buildSuccessPackageResponse(rewardCards);//todo always sends activated status of card false - fix it
    }

    /**
     * Calculates reward cards according to their chance of being knocked out.
     * @return a set of Cards.
     */
    private List<Card> geRewardCards() {
        Random random = new Random();

        Set<Card> rewardCards = new HashSet<>();
        List<Team> teams = teamService.getAllTeams();
        int countOfTeams = teams.size();
        while (rewardCards.size() < COUNT_OF_REWARD) {
            Team team = getRandomTeam(random, teams, countOfTeams);

            List<Player> possiblePlayers = team.getPlayers();
            int countOfPlayers = possiblePlayers.size();
            Player player = getRandomPlayer(random, possiblePlayers, countOfPlayers);

            List<Card> possibleCards = player.getCards();
            Card card = geRandomCard(random, possibleCards);

            rewardCards.add(card);
        }

        List<Card> resultRewardCards = new ArrayList<>();
        resultRewardCards.addAll(rewardCards);

        return resultRewardCards;
    }

    private Team getRandomTeam(Random random, List<Team> teams, int countOfTeams) {
        Team team = null;

        while (team == null) {
            Team possibleTeam = teams.get(random.nextInt(countOfTeams));
            if (possibleTeam.getChance() > random.nextInt(100)) {
                team = possibleTeam;
            }
        }

        return team;
    }

    private Player getRandomPlayer(Random random, List<Player> possiblePlayers, int countOfPlayers) {
        Player player = null;
        while (player == null){
            Player possiblePlayer = possiblePlayers.get(random.nextInt(countOfPlayers));
            if (possiblePlayer.getChance() > random.nextInt(100)) {
                player = possiblePlayer;
            }
        }

        return player;
    }

    private Card geRandomCard(Random random, List<Card> possibleCards) {
        Card card = null;
        while (card == null){
            Card possibleCard = possibleCards.get(random.nextInt(COUNT_OF_CARDS));
            if (possibleCard.getChance() > random.nextInt(100)) {
                card = possibleCard;
            }
        }

        return card;
    }

    /**
     * Checks whether the user has already got a reward this day.
     * @param user the user to be checked
     * @return true if the user's received the reward.
     */
    private boolean checkLastRewardDate(User user) {
        long lastReward = user.getLastRewardDate();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(lastReward);
        int day = cal.get(Calendar.DAY_OF_YEAR);
        int year = cal.get(Calendar.YEAR);

        Calendar now = Calendar.getInstance();
        int today = now.get(Calendar.DAY_OF_YEAR);
        int currentYear = now.get(Calendar.YEAR);

        if (day == today && year == currentYear){
            return true;
        }
        return false;
    }

    private UserCard buildNewUserCardRelation(User user, Card card) {
        UserCard userCard;
        long id = 0;
        boolean idOK = false;
        while (!idOK) {
            id = IdGenerator.generateRandomId();
            idOK = !userCardService.containsId(id);
        }
        userCard = new UserCard(user, card);
        userCard.setUserCardId(id);

        Set<UserCard> userCards = userCardService.getByUserIdAndPlayerId(user.getUserId(), card.getPlayer().getPlayerId());
        if (userCards.size() == 0) userCard.setActivated(true);

        return userCard;
    }
}
