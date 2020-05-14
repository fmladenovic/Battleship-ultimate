import { Component, OnInit, Output, EventEmitter, Input, OnChanges } from '@angular/core';

@Component({
  selector: 'app-battleships',
  templateUrl: './battleships.component.html',
  styleUrls: ['./battleships.component.css']
})
export class BattleshipsComponent implements OnInit, OnChanges {

  statuses = [null, null, null, null, null];
  ships = [ [0, 5], [1, 4], [2, 3], [3, 3], [4, 2] ];


  @Input() setShip: number;
  @Output() chosenShip = new EventEmitter();

  constructor() { }

  ngOnChanges() {
    this.setStatus();

  }

  ngOnInit() {
    this.statuses[0] = false;
    this.chosenShip.emit(this.ships[0]);
  }


  onBattleshipClick($event: number) {
    this.chosenShip.emit(this.ships[$event]);
    if (this.statuses[$event]) {
      return;
    }
    for (let i = 0; i < this.statuses.length; i++ ) {
      if (this.statuses[i] === false) {
        this.statuses[i] = null;
      }
    }
    if (this.statuses[$event] === null) {
      this.statuses[$event] = false;
    }
  }

  setStatus() {
    if (this.setShip !== -1) {
      this.statuses[this.setShip] = true;
      for ( let i = 0; i < this.statuses.length; i++) {
        if (this.statuses[i] === null) {
          this.statuses[i] = false;
          this.chosenShip.emit(this.ships[i]);
          return;
        }
      }
      this.chosenShip.emit([-1, -1]);
    }
  }




}
