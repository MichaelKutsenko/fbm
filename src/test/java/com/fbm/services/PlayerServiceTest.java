package com.fbm.services;

import com.fbm.domain.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Created by Mocart on 05-Aug-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerServiceTest {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    @Test
    @Transactional
    public void addPlayer() throws Exception {
        Team team = teamService.getById(1);

        assertThat(playerService.getAllPlayers().size()).isEqualTo(10);
        assertThat(playerService.getByTeamId(1).size()).isEqualTo(7);
        assertThat(playerService.getByTeamName("dinamo").size()).isEqualTo(7);
        assertThat(playerService.getByTeamName("DinaMo").size()).isEqualTo(7);
        assertThat(playerService.getByTeamName("Dinamo!").size()).isEqualTo(0);
        assertThat(playerService.getById(11).getTeam().getName()).isEqualTo("dinamo");
        assertThat(playerService.getById(11).getTeam()).isEqualTo(team);
    }

    @Test
    public void updatePlayer() throws Exception {
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void getByname() throws Exception {
    }

    @Test
    public void getAllPlayers() throws Exception {
    }

    @Test
    public void getByTeamId() throws Exception {
    }

    @Test
    public void getByTeamName() throws Exception {
    }

}