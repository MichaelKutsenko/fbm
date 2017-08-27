package com.fbm.dto;

import com.fbm.constants.CardType;
import com.fbm.domain.Card;
import com.fbm.domain.CardCartoon;
import com.fbm.domain.CardOfficial;
import com.fbm.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mocart on 20-Aug-17.
 */
public class CardMapper implements Mapper<Card, CardDto>{
    @Autowired
    private PlayerService playerService;

    @Override
    public CardDto convertToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getCardId());
        cardDto.setPlayerName(card.getPlayer().getName());
        cardDto.setType(card.determinateType());
        cardDto.setChance(card.getChance());
        cardDto.setLink(card.getLink());

        return cardDto;
    }

    @Override
    public Card convertToNewEntity(CardDto dto) {
        Card card = new Card();
        return convertToExistEntity(dto, card);
    }

    @Override
    public Card convertToExistEntity(CardDto dto, Card card) {
        card = castType(dto, card);
        card.setPlayer(playerService.getByname(dto.getPlayerName()));
        card.setChance(dto.getChance());
        card.setLink(dto.getLink());
        return card;
    }

    //todo redo if types of cards will be changed
    private Card castType(CardDto dto, Card card) {
        if (card.determinateType() == dto.getType()) return card;

        if (dto.getType() == CardType.CARTOON.getType()) {
            Card cartoonCard = new CardCartoon();
            cartoonCard.setCardId(card.getCardId());
            return cartoonCard;
        }
        else if (dto.getType() == CardType.OFFICIAL.getType()) {
            Card officialCard = new CardOfficial();
            officialCard.setCardId(card.getCardId());
            return officialCard;
        }
        else {
            Card parentCard = new Card();
            parentCard.setCardId(card.getCardId());
            return parentCard;
        }
    }
}
