package com.fbm.dto;

import com.fbm.domain.Team;
import com.fbm.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamMapper implements Mapper<Team, TeamDto> {
    @Autowired
    private CountryService countryService;

    @Override
    public TeamDto convertToDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamId(team.getTeamId());
        teamDto.setName(team.getName());
        teamDto.setCountry(team.getCountry().getCountryName());
        teamDto.setChance(team.getChance());

        return teamDto;
    }

    @Override
    public Team convertToNewEntity(TeamDto teamDto) {
        Team team = new Team();
        return convertToExistEntity(teamDto, team);
    }

    @Override
    public Team convertToExistEntity(TeamDto teamDto, Team team) {
        team.setName(teamDto.getName());
        team.setCountry(countryService.getByName(teamDto.getCountry()));
        team.setChance(teamDto.getChance());

        return team;
    }
}
