package com.sbz.battleship.domain.model;

import com.sbz.battleship.domain.model.enums.Region;
import com.sbz.battleship.domain.model.enums.Strategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Move {

    private Tuple position;
    private boolean hit;

    private Strategy strategy;
    private Region region;

}
