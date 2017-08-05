package com.fbm.repository;




import com.fbm.domain.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart on 07-Jul-17.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Override
    List<Player> findAll();

    List<Player> findByTeam_TeamId(long teamId);

    List<Player> findByTeam_Name(String name);

    Player findByName(String name);
}
