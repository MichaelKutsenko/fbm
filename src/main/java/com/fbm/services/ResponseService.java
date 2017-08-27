package com.fbm.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fbm.domain.Card;
import com.fbm.domain.Country;
import com.fbm.domain.Player;
import com.fbm.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mocart on 20-Aug-17.
 */
@Service
public class ResponseService<T> {

    public ResponseEntity buildSuccessPackageResponse(List<Card> cards) {
        CardMapper cardMapper = new CardMapper();

        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            cardDtos.add(cardMapper.convertToDto(card));
        }

        return new ResponseEntity(cardDtos, HttpStatus.OK);
    }

    public ResponseEntity buildSuccessCountryResponse(List<Country> countries) {
        CountryMapper countryMapper = new CountryMapper();

        List<CountryDto> countryDtos = new ArrayList<>();
        for (Country country: countries){
            countryDtos.add(countryMapper.convertToDto(country));
        }

        return new ResponseEntity(countryDtos, HttpStatus.OK);
    }

    public ResponseEntity buildSuccessPlayerResponse(List<Player> players) { //todo delete
        PlayerMapper teamMapper = new PlayerMapper();

        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player: players){
            playerDtos.add(teamMapper.convertToDto(player));
        }

        return new ResponseEntity(playerDtos, HttpStatus.OK);
    }

    public ResponseEntity buildErrorResponse(HttpStatus httpStatus, String errorCode) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", errorCode);

        return new ResponseEntity(errors, httpStatus);
    }

    public ResponseEntity buildSuccessResponse(T responseObject) {
        return new ResponseEntity(responseObject, HttpStatus.OK);
    }

    public ResponseEntity buildSuccessResponse(HttpStatus httpStatus) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
