package com.fbm.dto;

import com.fbm.domain.Card;

/**
 * Created by Mocart on 20-Aug-17.
 */
public class CardMapper {
    public CardDto convertToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getCardId());
        cardDto.setPlayerName(card.getPlayer().getName());
        cardDto.setTeam(card.getPlayer().getTeam().getName());
        cardDto.setType(card.determinateType());

        return cardDto;
    }
}
