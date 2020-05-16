package com.sbz.battleship.domain.model.decisions;

import com.sbz.battleship.domain.model.Tuple;
import com.sbz.battleship.domain.model.enums.Formation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormationDecision {

    private Formation computerLastUsedFormation; // for ship positions
    private List<Formation> computerMostUsedFormations;
    private List<Tuple> commonPlayerFirst5Strikes; //or maybe 10
    private List<Tuple> lastPlayerFirst5Strikes; // or maybe 10

}
