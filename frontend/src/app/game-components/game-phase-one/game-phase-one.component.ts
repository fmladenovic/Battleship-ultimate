import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-game-phase-one',
  templateUrl: './game-phase-one.component.html',
  styleUrls: ['./game-phase-one.component.css']
})
export class GamePhaseOneComponent implements OnInit {

  
  ship: [number, number];
  direction: [number, number];

  setShip = -1;

  constructor() { }

  ngOnInit() {
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

}
