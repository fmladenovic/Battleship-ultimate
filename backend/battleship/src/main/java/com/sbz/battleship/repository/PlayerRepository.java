package com.sbz.battleship.repository;

import com.sbz.battleship.domain.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends MongoRepository<Player, UUID> {

    List<Player> findByEmail(String email);
    List<Player> findByNick(String nick);

    Optional<Player> findByEmailAndPassword(String email, String password);

}
