package com.fbm.dto;

import com.fbm.domain.Player;

/**
 * Created by Mocart on 21-Aug-17.
 */
public class PlayerMapper {
    public PlayerDto convertToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getPlayerId());
        playerDto.setOrder(player.getOrder());

        return playerDto;
    }
}
