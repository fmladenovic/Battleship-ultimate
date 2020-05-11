import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Tuple } from 'src/app/shared/tuple';

import Swal from 'sweetalert2';
import { GameService } from 'src/app/game-components/service/game.service';
import { Ship } from 'src/app/shared/model/ship';
import { Game } from 'src/app/shared/model/game';


@Component({
  selector: 'app-board-first-phase',
  templateUrl: './board-first-phase.component.html',
  styleUrls: ['./board-first-phase.component.css']
})
export class BoardFirstPhaseComponent implements OnInit {

  @Input() ship: [number, number];
  @Input() direction: [number, number];
  @Output() setShip = new EventEmitter();

  n = 14;
  m = 14;

  rows = [];
  columns = [];

  currentMarked = [];

  takenTuples: Tuple[] = [];

  error = false;

  constructor(
    private gameService: GameService
  ) { }

  ngOnInit() {
    for (let i = 0; i < this.n; i++) {
      this.rows.push(i);
    }

    for (let i = 0; i < this.m; i++) {
      this.columns.push(i);
    }
  }

  onMouseEnter( i: number, j: number ) {
    this.generateMarked(i, j);
  }

  onMouseLeave() {
    this.currentMarked = [];
    this.error = false;
  }

  isTupleTaken( tuple: Tuple ) {
    for (let i = 0; i < this.takenTuples.length; i++) {
      if (tuple.equalsT(this.takenTuples[i])) {
        return true;
      }
    }
    return false;
  }

  isTupleMarked( tuple: Tuple ) {
    for (let i = 0; i < this.currentMarked.length; i++) {
      if (tuple.equalsT(this.currentMarked[i])) {
        return true;
      }
    }
    return false;
  }


  isTupleOnTable( tuple: Tuple ) {
    if ( (0 <= tuple.i && tuple.i < this.n) && ( 0 <= tuple.j && tuple.j < this.m )) {
      return true;
    }
    return false;
  }


  generateMarked(i: number, j: number) {
    if (this.direction[0] === 0 && this.direction[1] === 1) {
      for ( let b = 0; b < this.ship[1]; b++ ) {
        const tuple = new Tuple(i, j + b);
        if (!this.isTupleOnTable(tuple) || this.isTupleTaken(tuple)) {
          this.error = true;
        }
        this.currentMarked.push(tuple);
      }
    }
    if (this.direction[0] === 0 && this.direction[1] === -1) {
      for ( let b = 0; b < this.ship[1]; b++ ) {
        const tuple = new Tuple(i, j - b);
        if (!this.isTupleOnTable(tuple) || this.isTupleTaken(tuple)) {
          this.error = true;
        }
        this.currentMarked.push(tuple);
      }
    }
    if (this.direction[0] === 1 && this.direction[1] === 0) {
      for ( let b = 0; b < this.ship[1]; b++ ) {
        const tuple = new Tuple(i + b, j);
        if (!this.isTupleOnTable(tuple) || this.isTupleTaken(tuple)) {
          this.error = true;
        }
        this.currentMarked.push(tuple);
      }
    }
    if (this.direction[0] === -1 && this.direction[1] === 0) {
      for ( let b = 0; b < this.ship[1]; b++ ) {
        const tuple = new Tuple(i - b, j);
        if (!this.isTupleOnTable(tuple) || this.isTupleTaken(tuple)) {
          this.error = true;
        }
        this.currentMarked.push(tuple);
      }
    }
  }

  fieldStatus( i: number, j: number) {
    const tuple = new Tuple(i, j);
    const isTupleTaken = this.isTupleTaken(tuple);

    const isMarked = this.isTupleMarked(tuple);
    if (isMarked) {
      if (this.error) {
        return 'error';
      } else {
        return 'marked';
      }
    } else {
      if (isTupleTaken) {
        return 'taken';
      } else {
        return 'default';
      }
    }
  }

  onClick() {
    if (this.ship[0] === -1) {
      // Swal.fire(
      //   'Ooops!',
      //   'First phase is over!',
      //   'error'
      // );
      this.gameService.nextPhase();
      return;
    }

    if (!this.error) {

      const ship = new Ship();
      ship.positions = this.currentMarked;
      ship.size = this.currentMarked.length;
      this.gameService.addShip(ship);

      this.currentMarked.forEach( tuple => this.takenTuples.push(tuple));
      this.currentMarked = [];
      this.setShip.emit(this.ship[0]);

    } else {
      Swal.fire(
        'Ooops!',
        'You are not able to pick this position! :(',
        'error'
      );
    }
  }


}
