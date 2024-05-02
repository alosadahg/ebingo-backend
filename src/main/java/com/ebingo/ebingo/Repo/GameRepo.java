package com.ebingo.ebingo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebingo.ebingo.Model.Game;

public interface GameRepo extends JpaRepository<Game,String> {

}
