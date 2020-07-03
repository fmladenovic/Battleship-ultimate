package com.sbz.battleship.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sbz.battleship.domain.model.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, UUID> {
	
	List<Game> findAllByPlayerId( UUID id );
}
