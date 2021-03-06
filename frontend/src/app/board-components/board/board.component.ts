import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Tuple } from 'src/app/shared/tuple';

import Swal from 'sweetalert2';


@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {


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

  constructor() { }

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
    if ( (0 <= tuple.x && tuple.x < this.n) && ( 0 <= tuple.y && tuple.y < this.m )) {
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
      Swal.fire(
        'Ooops!',
        'First phase is over!',
        'error'
      );
      return;
    }

    if (!this.error) {
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
