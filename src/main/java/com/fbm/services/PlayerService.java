package com.fbm.services;

import com.football.fbm.domain.Player;
import com.football.fbm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mocart on 30-Jul-17.
 */
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;


    public Player getPlayerById(long userId) {
        return playerRepository.findOne(userId);
    }

    public Player getPlayerByname(String name) {
        return playerRepository.findByName(name);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers(){
        return  playerRepository.findAll();
    }

    public List<Player> getListByTeamId(long teamId){
        return playerRepository.findByTeam_TeamId(teamId);
    }

    public List<Player> getListByTeamName(String teamName){
        return playerRepository.findByTeam_Name(teamName);
    }
}
