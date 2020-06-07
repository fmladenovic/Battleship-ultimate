package com.sbz.battleship.domain.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sbz.battleship.domain.model.enums.Formation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "players")
public class Player {

    @Id
    private UUID id;

    private String email;
    private String nick;
    private String password;

    @DBRef(lazy = true)
    private List<Game> games;

    // For computer attack
    private List<Ship> lastPlayShipsPositions;
    private List<Tuple> mostCommonShipPosition;

    // For computer defence
    private Formation computerLastUsedFormation; // for ship positions
    private Set<Formation> computerMostUsedFormations;
    private List<Tuple> commonFirst5Strikes; //or maybe 10
    private List<Tuple> lastFirst5Strikes; // or maybe 10

    //Will see
    private Boolean lastGameVictory;


}
