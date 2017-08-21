package com.fbm.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fbm.domain.Card;
import com.fbm.domain.Player;
import com.fbm.domain.UserCard;
import com.fbm.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Mocart on 20-Aug-17.
 */
@Service
public class ResponseService {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserCardService userCardService;

    public String buildSuccessTeamCardsResponse(Set<Card> cards, long userId) throws JsonProcessingException {
        PlayerMapper playerMapper = new PlayerMapper();
        CardMapper cardMapper = new CardMapper();

        Map<Player, PlayerDto> playerMap = groupCardsByPlayers(cards, playerMapper, cardMapper);
        Set<PlayerDto> resultPlayerDtoSet = sortPlayersByOrder(playerMap);
        determinateActiveCards(userId, resultPlayerDtoSet);
        Response<PlayerDto> response = buildResponse(Status.SUCCESS, ErrorCode.SUCCESS, resultPlayerDtoSet);

        return mapper.writeValueAsString(response);
    }

    private Map<Player, PlayerDto> groupCardsByPlayers(Set<Card> cards, PlayerMapper playerMapper, CardMapper cardMapper) {
        Map<Player, PlayerDto> playerMap = new HashMap<>();
        for (Card card: cards) {
            Player player = card.getPlayer();
            CardDto cardDto = cardMapper.convertToDto(card);

            if (playerMap.containsKey(player)){
                playerMap.get(player).getCards().add(cardDto);
            } else {
                PlayerDto playerDto = playerMapper.convertToDto(player);

                Set<CardDto> playerCardSet = new TreeSet<>();
                playerCardSet.add(cardDto);
                playerDto.setCards(playerCardSet);
                playerMap.put(player, playerDto);
            }
        }
        return playerMap;
    }

    private Set<PlayerDto> sortPlayersByOrder(Map<Player, PlayerDto> playerMap) {
        Set<PlayerDto> resultPlayerDtoSet = new TreeSet<>();
        resultPlayerDtoSet.addAll(playerMap.values());
        return resultPlayerDtoSet;
    }

    private void determinateActiveCards(long userId, Set<PlayerDto> playerDtoSet) {
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

    public String buildErrorResponse(String status, String errorCode) throws JsonProcessingException {
        Response response = buildResponse(status, errorCode, null);
        return mapper.writeValueAsString(response);
    }

    public String buildSuccessPackageResponse(Set<Card> cards) throws JsonProcessingException {
        CardMapper cardMapper = new CardMapper();

        Set<CardDto> cardDtos = new HashSet<>();
        for (Card card: cards) {
            cardDtos.add(cardMapper.convertToDto(card));
        }
        Response<CardDto> response = buildResponse(Status.SUCCESS, ErrorCode.SUCCESS, cardDtos);
        return mapper.writeValueAsString(response);
    }

    private Response buildResponse(String status, String errorCode, Set elements) {
        Response response = new Response();
        response.setStatus(status);
        response.setCode(errorCode);
        response.setElements(elements);
        return response;
    }
}
