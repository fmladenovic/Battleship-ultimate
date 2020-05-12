import { Component, OnInit, Input } from '@angular/core';
import { Tuple } from 'src/app/shared/tuple';

import Swal from 'sweetalert2';
import { Ships } from 'src/app/shared/model/ships';
import { GameService } from 'src/app/game-components/service/game.service';
import { Move } from 'src/app/shared/model/move';
import { GameStatus } from 'src/app/shared/model/game-status';


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

  @Input() player: string; // who own table
  @Input() ships: Ships;

  triedMoves: Move[];


  error = false;

  gameStatus: GameStatus;


  constructor(
    private gameService: GameService
  ) { }

  ngOnInit() {
    if( this.player === 'player' ) {
      this.gameService.game.subscribe( data => this.ships = data.playerShips );
    } else {
      this.gameService.game.subscribe( data => this.ships = data.computerShips );
    }
    
    if ( this.player === 'player' ) {
      this.gameService.game.subscribe( data => this.triedMoves = data.computerMoves );
    } else {
      this.gameService.game.subscribe( data => this.triedMoves = data.playerMoves );
    }

    this.gameService.gameStatus.subscribe( data => this.gameStatus = data );


    for (let i = 0; i < this.n; i++) {
      this.rows.push(i);
    }

    for (let i = 0; i < this.m; i++) {
      this.columns.push(i);
    }
  }

  onClick(i: number, j: number) {
    if ( this.gameStatus.status === 'miss' ) {
      return;
    }

    if ( this.player === 'player' ) {
      Swal.fire(
        'Ooops!',
        'Computer should perform move!',
        'error'
      );
      return;
    }
    if ( this.error ) {
      Swal.fire(
        'Ooops!',
        'Field is already chosen!',
        'error'
      );
      return;
    }
    const isHit = this.isInShips(i, j);

    const move = new Move();
    move.position = new Tuple(i, j);
    move.hit = isHit;
    move.region = 'PLAYER';
    move.strategy = 'PLAYER';

    this.gameService.addPlayerMove(move);
    if ( !isHit ) {
      this.gameService.computerTurn();
    }
  }

  onMouseEnter( i: number, j: number ) {
    this.error = this.isInTriedMoves(i, j);
  }

  onMouseLeave() {
    this.error = false;
  }


  fieldStatus( i: number, j: number) {
    const isInShips = this.isInShips(i, j);
    const isInTriedMove = this.isInTriedMoves(i, j);

    if ( this.player === 'player' ) {
      if ( isInShips && isInTriedMove ) {
        return 'computer-hit';
      } else if ( isInTriedMove ) {
        return 'disabled';
      } else {
        return 'default-p'; // check this
      }
    } else {
      if ( isInShips && isInTriedMove ) {
        return 'player-hit';
      } else if ( isInTriedMove ) {
        return 'disabled';
      } else {
        return 'default';
      }
    }
  }

  // isInTriedFileds( i: number, j: number ) {
  //   for ( const tuple of this.triedFileds ) {
  //     if ( tuple.x === i && tuple.y === j ) {
  //       return true;
  //     }
  //   }
  //   return false;
  // }

  isInTriedMoves( i: number, j: number ) {
    for ( const move of this.triedMoves ) {
      if ( move.position.x === i && move.position.y === j ) {
        return true;
      }
    }
    return false;
  }

  isInShips( i: number, j: number ) {
    for ( const ship of this.ships.ships ) {
      for ( const tuple of ship.positions) {
        if(tuple.x === i && tuple.y === j) {
          return true;
        }
      }
    }
    return false;
  }



}
