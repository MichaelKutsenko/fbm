package com.fbm.dto;

import com.fbm.domain.Card;
import com.fbm.domain.Player;
import com.fbm.repository.TeamRepository;
import com.fbm.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mocart on 21-Aug-17.
 */
public class PlayerMapper implements Mapper<Player, PlayerDto>{
    CardMapper cardMapper = new CardMapper();

    @Autowired
    private TeamService teamService;

    @Override
    public PlayerDto convertToDto(Player player) {
        PlayerDto dto = new PlayerDto();
        dto.setId(player.getPlayerId());
        dto.setPlayerName(player.getName());
        dto.setNumber(player.getNumber());
        dto.setTeamName(player.getTeam().getName());
        dto.setOrder(player.getOrder());
        dto.setChance(player.getChance());

        List<CardDto> cardDtos = new ArrayList<>(); //add cardDtos
        List<Card> cards = player.getCards();
        Collections.sort(cards);
        for (Card card: cards) {
            cardDtos.add(cardMapper.convertToDto(card));
        }
        dto.setCards(cardDtos);

        return dto;
    }

    @Override
    public Player convertToNewEntity(PlayerDto dto) {
        Player player = new Player();
        return convertToExistEntity(dto, player);
    }

    @Override
    public Player convertToExistEntity(PlayerDto dto, Player player) {
        player.setName(dto.getPlayerName());
        player.setNumber(dto.getNumber());
        player.setChance(dto.getChance());
        player.setOrder(dto.getOrder());
        player.setTeam(teamService.getByName(dto.getTeamName()));

        return player;
    }
}
