import { Component, OnInit } from '@angular/core';
import { GameService } from '../service/game.service';

@Component({
  selector: 'app-game-phase-one',
  templateUrl: './game-phase-one.component.html',
  styleUrls: ['./game-phase-one.component.css']
})
export class GamePhaseOneComponent implements OnInit {

  
  ship: [number, number];
  direction: [number, number];

  setShip = -1;

  status: string;

  constructor(
    private gameService: GameService
  ) { }

  ngOnInit() {
    this.gameService.game.subscribe( data => this.status = data.player.status );
    console.log(this.status);

  }

  onChosenShip($event: [number, number]) {
    this.ship = $event;
  }

  onChosenDirection($event: [number, number]) {
    this.direction = $event;
  }

  onSetShip($event: number) {
    this.setShip = $event;
  }

  onNext() {
    this.gameService.setShips();
  }

}
