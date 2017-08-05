package com.fbm.services;

import com.fbm.domain.Player;
import com.fbm.repository.PlayerRepository;
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

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player getById(long userId) {
        return playerRepository.findOne(userId);
    }

    public Player getByname(String name) {
        return playerRepository.findByName(name);
    }

    public List<Player> getAllPlayers(){
        return  playerRepository.findAll();
    }

    public List<Player> getByTeamId(long teamId){
        return playerRepository.findByTeam_TeamId(teamId);
    }

    public List<Player> getByTeamName(String teamName){
        return playerRepository.findByTeam_Name(teamName);
    }
}
