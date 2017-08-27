package com.fbm.controllers.admin;

import com.fbm.domain.Card;
import com.fbm.dto.CardDto;
import com.fbm.dto.CardMapper;
import com.fbm.constants.ErrorCode;
import com.fbm.services.CardService;
import com.fbm.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/") //todo add "admin" to url
//todo add null pointer exception handling
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private ResponseService responseService;

    @RequestMapping(path = "/cards/byPlayer/{playerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getPlayersWithCardsByTeam(@PathVariable long playerId) {
        List<Card> cards = cardService.getByPlayerId(playerId);
        Collections.sort(cards);

        CardMapper cardMapper = new CardMapper();
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card: cards) {
            cardDtos.add(cardMapper.convertToDto(card));
        }

        return responseService.buildSuccessResponse(cardDtos);
    }

    @RequestMapping(path = "/add/card",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addPlayer(@RequestBody CardDto cardDto) {
        Card card = cardService.getByChanceAndPlayerName(cardDto.getChance(), cardDto.getPlayerName()); //todo change to unique value (f.e. url)

        if (card != null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_EXISTS);

        CardMapper cardMapper = new CardMapper();
        card = cardMapper.convertToNewEntity(cardDto);
        try {
            card = cardService.addCard(card);
            return responseService.buildSuccessResponse(card);
        }catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/update/card",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updatePlayer(@RequestBody CardDto cardDto) {
        Card card = cardService.getById(cardDto.getId());

        if (card == null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_DOES_NOT_EXIST);

        CardMapper cardMapper = new CardMapper();
        card = cardMapper.convertToExistEntity(cardDto, card);
        try {
            card = cardService.updateCard(card);
            return responseService.buildSuccessResponse(card);
        }catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/delete/card/byId/{cardId}",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deletePlayer(@PathVariable long cardId) {
        try {
            cardService.deleteById(cardId);
            return responseService.buildSuccessResponse(HttpStatus.OK);
        } catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }
}
