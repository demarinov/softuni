package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends  JpaRepository<Player, Long> {

    boolean existsByEmail(String email);

    @Query("select p from Player p where p.birthDate between '1995-01-01' and '2003-01-01'" +
            " order by p.stat.shooting desc, " +
            "p.stat.passing desc, p.stat.endurance desc, p.lastName")
    List<Player> exportBestPlayers();
}
