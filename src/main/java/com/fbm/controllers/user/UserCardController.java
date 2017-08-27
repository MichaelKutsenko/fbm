package com.fbm.controllers.user;

import com.fbm.constants.ErrorCode;
import com.fbm.domain.Card;
import com.fbm.domain.Player;
import com.fbm.domain.UserCard;
import com.fbm.dto.CardDto;
import com.fbm.dto.PlayerDto;
import com.fbm.dto.PlayerMapper;
import com.fbm.exeptions.FbmServerException;
import com.fbm.exeptions.ForbiddenException;
import com.fbm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Mocart on 20-Aug-17.
 */

@RestController
@RequestMapping("/")
public class UserCardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private UserCardService userCardService;

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

    //todo delete this method???
//    @RequestMapping(path = "/cards/byUserId/{userId}/byTeam/{teamName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity getCardsByUserIdAndTeamName(@PathVariable long userId, @PathVariable String teamName) {
//        List<Card> cards = cardService.getByUserIdAndTeamName(userId, teamName); //todo delete this method???
//
//        return responseService.buildSuccessTeamCardsResponse(cards, userId);
//    }

    //todo redo according to authorizaiton security
    @RequestMapping(value = "/files/{fileName}/byUserId/{userId}/byCardId/{cardId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public FileSystemResource getFile(@PathVariable("fileName") String fileName,
                                      @PathVariable("userId") long userId,
                                      @PathVariable("cardId") long cardId) {

        UserCard userCard = userCardService.getByUserIdAndCardIds(userId, cardId);
        if (userCard.isActivated() == false) throw new ForbiddenException(ErrorCode.EXCESS_DENIED);

        try {
            File file = new ClassPathResource("cards/" + fileName.toLowerCase() + ".jpg").getFile();
            return new FileSystemResource(file);
        } catch (IOException e) {
            throw new FbmServerException(ErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @RequestMapping(path = "/cards/byUserId/{userId}/byTeamId/{teamId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getCardsByUserIdAndTeamId(@PathVariable long userId, @PathVariable long teamId) {
        List<Card> cards = cardService.getByUserIdAndTeamId(userId, teamId);
        List<Player> players = groupCardsByPlayers(cards);

        PlayerMapper playerMapper = new PlayerMapper();
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player: players) {
            playerDtos.add(playerMapper.convertToDto(player));
        }
        determinateActiveCards(userId, playerDtos);

        return new ResponseEntity(playerDtos, HttpStatus.OK);
    }

    private List<Player> groupCardsByPlayers(List<Card> cards) {
        Map<Player, Player> playerMap = new HashMap<>();
        for (Card card: cards) {
            Player player = card.getPlayer();

            if (playerMap.containsKey(player)){
                playerMap.get(player).addCard(card);
            } else {
                setCardsForSpecificUser(card, player);
                playerMap.put(player, player);
            }
        }
        List<Player> players = new ArrayList<>();
        players.addAll(playerMap.values());
        Collections.sort(players);
        return players;
    }

    private void setCardsForSpecificUser(Card card, Player player) {
        player.getCards().clear();
        player.addCard(card);
    }

    private void determinateActiveCards(long userId, List<PlayerDto> playerDtoSet) {
        for (PlayerDto playerDto : playerDtoSet) {
            for (CardDto cardDto: playerDto.getCards()){
                UserCard userCard = userCardService.getByUserIdAndCardIds(userId, cardDto.getId());
                if (userCard.isActivated()) {
                    cardDto.setActive(true);
                    break;
                }
            }
        }
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity handleForbiddenException(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getError());
    }

    @ExceptionHandler(FbmServerException.class)
    public ResponseEntity handleFbmServerException(FbmServerException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getError());
    }
}
