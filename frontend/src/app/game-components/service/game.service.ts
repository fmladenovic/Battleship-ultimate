import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { Game } from 'src/app/shared/model/game';
import { Ship } from 'src/app/shared/model/ship';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private phasesOb = new BehaviorSubject<boolean[]>([true, false, false]);
  private phasesHolder = [true, false, false];
  phases = this.phasesOb.asObservable();

  
  private gameOb = new BehaviorSubject<Game>(null);
  private gameHolder: Game = null;
  game = this.gameOb.asObservable();


  constructor() { }

  setPhases( value: boolean[] ) {
    this.phasesHolder = value;
    this.phasesOb.next(this.phasesHolder);
  }

  nextPhase() {
    for ( let i in this.phasesHolder ) {
      if ( this.phasesHolder[i] ) {
        this.phasesHolder[i] = false;
        const no = Number(i) + 1;
        this.phasesHolder[no] = true;
        this.setPhases(this.phasesHolder);
        return;
      }
    }
  }


  setGame( value: Game ) {
    this.gameHolder = value;
    this.gameOb.next(this.gameHolder);
  }

  addShip( ship: Ship ) {
    this.gameHolder.playerShips.push(ship);
    this.setGame(this.gameHolder);
  }

  putGame() {
    //todo http request
  }  



}
