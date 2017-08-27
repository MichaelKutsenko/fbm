package com.fbm.controllers.admin;

import com.fbm.domain.Team;
import com.fbm.constants.ErrorCode;
import com.fbm.dto.TeamDto;
import com.fbm.dto.TeamMapper;
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
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private ResponseService responseService;

    @RequestMapping(path = "/teams/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return getTeamResponseEntity(teams);
    }

    @RequestMapping(path = "/teams/byCountry/{countryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTeamsByCountry(@PathVariable long countryId) {
        List<Team> teams = teamService.getByCountryId(countryId);
        return getTeamResponseEntity(teams);
    }

    private ResponseEntity getTeamResponseEntity(List<Team> teams) {
        Collections.sort(teams);

        TeamMapper teamMapper = new TeamMapper();

        List<TeamDto> teamDtos = new ArrayList<>();
        for (Team team: teams){
            teamDtos.add(teamMapper.convertToDto(team));
        }
        return responseService.buildSuccessResponse(teamDtos);
    }

    @RequestMapping(path = "/add/team",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addTeam(@RequestBody TeamDto teamDto) {
        Team team = teamService.getByTeamNameAndCountryName(teamDto.getName(), teamDto.getCountry());

        if (team != null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_EXISTS);

        TeamMapper teamMapper = new TeamMapper();
        team = teamMapper.convertToNewEntity(teamDto);
        try {
            team = teamService.addTeam(team);
            return responseService.buildSuccessResponse(team);
        }catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/update/team",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTeam(@RequestBody TeamDto teamDto) {
        Team team = teamService.getById(teamDto.getTeamId());

        if (team == null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_DOES_NOT_EXIST);

        TeamMapper teamMapper = new TeamMapper();
        team = teamMapper.convertToExistEntity(teamDto, team);
        try {
            team = teamService.updateTeam(team);
            return responseService.buildSuccessResponse(team);
        }catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/delete/team/byId/{teamId}",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteTeam(@PathVariable long teamId) {
        try {
            teamService.deleteTeamById(teamId);
            return responseService.buildSuccessResponse(HttpStatus.OK);
        } catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }
}
