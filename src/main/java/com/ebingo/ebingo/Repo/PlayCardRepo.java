package com.ebingo.ebingo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebingo.ebingo.Model.Playcard;

public interface PlayCardRepo extends JpaRepository<Playcard, String>{
    
}
