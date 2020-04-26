import { Component, OnInit, Input } from '@angular/core';
import { Tuple } from 'src/app/shared/tuple';

import Swal from 'sweetalert2';


@Component({
  selector: 'app-board-second-phase',
  templateUrl: './board-second-phase.component.html',
  styleUrls: ['./board-second-phase.component.css']
})
export class BoardSecondPhaseComponent implements OnInit {

  n = 14;
  m = 14;

  rows = [];
  columns = [];

  @Input() player: string;
  @Input() ships: Tuple[];

  triedFileds: Tuple[];

  error = false;


  constructor() { }

  ngOnInit() {
    this.ships = [];
    this.ships.push(
      new Tuple(0, 0),
      new Tuple(0, 1),
      new Tuple(0, 2),
      new Tuple(0, 3),
      new Tuple(0, 4),

      new Tuple(1, 0),
      new Tuple(2, 0),
      new Tuple(3, 0),
      new Tuple(4, 0),

      new Tuple(9, 0),
      new Tuple(9, 1),
      new Tuple(9, 2),

      new Tuple(5, 0),
      new Tuple(5, 1),
      new Tuple(5, 2),

      new Tuple(8, 0),
      new Tuple(8, 1)
    );

    this.triedFileds = [];

    for (let i = 0; i < this.n; i++) {
      this.rows.push(i);
    }

    for (let i = 0; i < this.m; i++) {
      this.columns.push(i);
    }
  }

  onClick(i: number, j: number) {
    if ( this.error || this.player === 'player') {
      Swal.fire(
        'Ooops!',
        'Something went wrong xD!',
        'error'
      );
      return;
    }
    this.triedFileds.push(new Tuple(i, j));
  }

  onMouseEnter( i: number, j: number ) {
    this.error = this.isInTriedFileds(i, j);
    console.log(this.error);
  }

  onMouseLeave() {
    this.error = false;
  }


  fieldStatus( i: number, j: number) {
    const isInShips = this.isInShips(i, j);
    const isInTriedFileds = this.isInTriedFileds(i, j);

    if ( this.player === 'player' ) {
      if ( isInShips && isInTriedFileds ) {
        return 'computer-hit';
      } else if ( isInTriedFileds ) {
        return 'disabled';
      } else {
        return 'default-p'; // check this
      }
    } else {
      if ( isInShips && isInTriedFileds ) {
        return 'player-hit';
      } else if ( isInTriedFileds ) {
        return 'disabled';
      } else {
        return 'default';
      }
    }
  }

  isInTriedFileds( i: number, j: number ) {
    for ( const tuple of this.triedFileds ) {
      if (tuple.equals(i, j)) {
        return true;
      }
    }
    return false;
  }

  isInShips( i: number, j: number ) {
    for ( const tuple of this.ships ) {
      if (tuple.equals(i, j)) {
        return true;
      }
    }
    return false;
  }



}
