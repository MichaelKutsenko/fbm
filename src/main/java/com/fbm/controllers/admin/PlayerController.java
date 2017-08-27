package com.fbm.controllers.admin;

import com.fbm.domain.Player;
import com.fbm.constants.ErrorCode;
import com.fbm.dto.PlayerDto;
import com.fbm.dto.PlayerMapper;
import com.fbm.services.*;
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
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ResponseService responseService;

    @RequestMapping(path = "/players/byTeam/{teamId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getPlayersWithCardsByTeam(@PathVariable long teamId) {
        List<Player> players = playerService.getByTeamId(teamId);
        Collections.sort(players);

        PlayerMapper playerMapper = new PlayerMapper();
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player: players) {
            playerDtos.add(playerMapper.convertToDto(player));
        }

        return responseService.buildSuccessResponse(playerDtos);
    }

    @RequestMapping(path = "/add/player",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addPlayer(@RequestBody PlayerDto playerDto) {
        Player player = playerService.getByPlayerNameAndTeamName(playerDto.getPlayerName(), playerDto.getTeamName());

        if (player != null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_EXISTS);

        PlayerMapper playerMapper = new PlayerMapper();
        player = playerMapper.convertToNewEntity(playerDto);
        try {
            player = playerService.addPlayer(player);
            return responseService.buildSuccessResponse(player);
        }catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/update/player",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updatePlayer(@RequestBody PlayerDto playerDto) {
        Player player = playerService.getById(playerDto.getId());

        if (player == null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_DOES_NOT_EXIST);

        PlayerMapper playerMapper = new PlayerMapper();
        player = playerMapper.convertToExistEntity(playerDto, player);
        try {
            player = playerService.updatePlayer(player);
            return responseService.buildSuccessResponse(player);
        }catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/delete/player/byId/{playerId}",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deletePlayer(@PathVariable long playerId) {
        try {
            playerService.deleteById(playerId);
            return responseService.buildSuccessResponse(HttpStatus.OK);
        } catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }
}
