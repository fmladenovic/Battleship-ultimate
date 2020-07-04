package com.sbz.battleship.domain.model.decisions;

import com.sbz.battleship.domain.model.Ships;
import com.sbz.battleship.domain.model.Tuple;
import com.sbz.battleship.domain.model.enums.Formation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormationDecision {

    private Set<Formation> computerFormations;


    private Formation computerLastUsedFormation; // for ship positions
    private Set<Formation> computerMostUsedFormations;

    private List<Tuple> dangerPositions;

    private boolean readyForDecision = false;
    private Ships decision;

    public Formation getRandomFromSet() {
        int size = computerFormations.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for(Formation obj : computerFormations)
        {
            if (i == item)
                return obj;
            i++;
        }
        return Formation.INITIAL;
    }

}
