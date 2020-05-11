package com.sbz.battleship.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "games")
public class Game {

    @Id
    private UUID id;
    private UUID playerId;

    private List<Move> playerMoves;
    private Ships computerShips;

    private List<Move> computerMoves;
    private Ships playerShips;

    private Boolean winner; // true computer, false player

}
