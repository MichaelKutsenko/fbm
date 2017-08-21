package com.fbm.controllers.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fbm.domain.Card;
import com.fbm.domain.Player;
import com.fbm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mocart on 20-Aug-17.
 */

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserCardService userCardService;
    @Autowired
    private ResponseService responseService;

    //todo delete or redo this method

//    @RequestMapping(path = "/cards/byId/{userId}",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getUserCards(@PathVariable long userId) throws JsonProcessingException {
//        List<Card> cards = cardService.getByUserId(userId);
//
//        List<Team> teams = new ArrayList<>();
////        List<Player> players = new ArrayList<>();
//        List<Card> playerCards = new ArrayList<>();
//        for (Card card: cards) {
//            Player player = card.getPlayer();
//            Team team = player.getTeam();
//
//            int index = teams.indexOf(team);
//            if (index == -1){
//                teams.add(team);
//            } else {
//                List players = teams.get(index).getPlayers();
//                int indexOfPlayer = team.getPlayers().indexOf(player);
//
//                if (index == -1){
//                    team.getPlayers().add(player);
//                }
//            }
//        }
//        Set<Card> cardSet = new HashSet<>();
//        cardSet.addAll(cards);
//
//        return ResponseService.buildSuccessPackageResponse(cardSet);
//    }

    @RequestMapping(path = "/byUserId/{userId}/byTeam/{teamName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getCardsByUserIdAndTeamName(@PathVariable long userId, @PathVariable String teamName) throws JsonProcessingException {
        Set<Card> cards = cardService.getByUserIdAndTeamName(userId, teamName);

        return responseService.buildSuccessTeamCardsResponse(cards, userId);
    }
}
