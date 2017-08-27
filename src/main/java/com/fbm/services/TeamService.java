package com.fbm.services;

import com.fbm.domain.Team;
import com.fbm.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mocart on 05-Aug-17.
 */
@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getByName(String name) {
        return teamRepository.findByName(name);
    }

    public Team getById(long id) {
        return teamRepository.findOne(id);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<Team> getByCountryName(String countryName) {
        return teamRepository.findByCountry_CountryName(countryName);
    }

    public List<Team> getByCountryId(long countryId) {
        return teamRepository.findByCountry_CountryId(countryId);
    }

    public Team getByTeamNameAndCountryName(String teamName, String countryName) {
        return teamRepository.findByNameAndCountry_CountryName(teamName, countryName);
    }

    public void deleteTeamById(long teamId) {
        teamRepository.delete(teamId);
    }
}
