package com.brunoneves.dslist.repositories;

import com.brunoneves.dslist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
