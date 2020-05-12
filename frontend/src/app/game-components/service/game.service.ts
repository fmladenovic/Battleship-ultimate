import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import Swal from 'sweetalert2';

import { Game } from 'src/app/shared/model/game';
import { Ship } from 'src/app/shared/model/ship';
import { SignupRequest } from 'src/app/shared/dto/signup-request';
import { SigninRequest } from 'src/app/shared/dto/signin-request';
import { Ships } from 'src/app/shared/model/ships';
import { Move } from 'src/app/shared/model/move';
import { GameStatus } from 'src/app/shared/model/game-status';
import { Tuple } from 'src/app/shared/tuple';
import { SpinnerService } from 'src/app/shared/service/spinner.service';

const PLAYER = '/api/players'
const GAME = '/api/games'

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

  playerMovesInRow: Move[] = [];

    
  private gameStatusOb = new BehaviorSubject<GameStatus>(new GameStatus());
  private gameStatusHolder: GameStatus = new GameStatus();
  gameStatus = this.gameStatusOb.asObservable();


  constructor(
    private http: HttpClient,
    private spinnerService: SpinnerService
  ) { }

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

  setGameStatus( value: GameStatus ) {
    this.gameStatusHolder = value;
    this.gameStatusOb.next( this.gameStatusHolder );
  }


  setGame( value: Game ) {
    this.gameHolder = value;
    this.gameOb.next(this.gameHolder);
  }

  addShip( ship: Ship ) {
    this.gameHolder.playerShips.ships.push(ship);
    this.setGame(this.gameHolder);
  }

  setComputerShips( ships: Ships ) {
    this.gameHolder.computerShips = ships;
    this.setGame(this.gameHolder);
  }

  addPlayerMove( move: Move ) {
    if( move.hit ) {
      this.playerHitStatus();
    } else {
      this.playerMissStatus();
    }

    this.gameHolder.playerMoves.push(move);
    this.setGame(this.gameHolder);

    this.playerMovesInRow.push(move);

    console.log(this.playerMovesInRow);
  }


  addComputerMove( move: Move ) {
    if( move.hit ) {
      this.computerHitStatus();
    } else {
      this.computerMissStatus();
    }

    this.gameHolder.computerMoves.push(move);
    this.setGame(this.gameHolder);
  }

  // SEND TO BACKEND // Send rowPlayerMoves, switch moves
  computerTurn() {
    this.delay(1500).then( () => {
      this.computerPickStatus();
      this.putPlayerMoves(this.playerMovesInRow);
    });
  }

  computerPlay() {
    this.spinnerService.showSpinner();
    this.http.put<Move>(GAME + `/${this.gameHolder.id}/computer`, null)
    .subscribe(
      success => {
        this.delay(1500).then(
          () => {
            this.spinnerService.hideSpinner();
            this.addComputerMove(success);
            if ( success.hit ) {
              this.computerPlay();
            } else {
              this.playerPlay();
            }
          });
      },
      error => {
        this.errorMessage(error.error.message);
        this.spinnerService.hideSpinner();
      }
    );

    // this.spinnerService.showSpinner();
    // this.delay(2000)
    // .then(
    //   () => {
    //     const move = new Move();
    //     move.hit = false;
    //     move.position = new Tuple(0, 0);
    //     move.region = 'TOP_LEFT';
    //     move.strategy = 'EVERY_TWO';
    //     this.addComputerMove(move);
    //     if ( move.hit ) {
    //       this.computerPlay();
    //     } else {
    //       this.computerMissStatus();
    //       this.playerPlay();
    //     }
    //     this.spinnerService.hideSpinner();

    //   }
    // );


  }

  playerPlay() {
    this.delay(1500)
    .then( () => {
      this.playerMovesInRow = [];
      this.playerPickStatus();
    })
  }



  putPlayerMoves( playerMovesInRow: Move[] ) {
    // this.delay(2000)
    // .then(
    //   () => this.computerPlay()
    // );
    this.http.put(GAME + `/${this.gameHolder.id}/moves`, playerMovesInRow)
    .subscribe(
      success => {        
        this.delay(1500).then( () => this.computerPlay() );
      },
      error => this.errorMessage(error.error.message)
    );
  }


  signup( value: SignupRequest ) {
    return this.http.post(PLAYER, value);
    
  }

  setShips() {
    const ships = [];
    this.gameHolder.playerShips.ships.forEach( ship => ships.push(ship));
    this.http.put<Ships>(GAME + `/${this.gameHolder.id}/set_ships`, ships)
    .subscribe(
      success => {
        // this.setComputerShips(success); In the case that i want to include this formation i decision
        this.nextPhase();
      },
      error => this.errorMessage(error.error.message)
    );
  }

  signin( value: SigninRequest ) {
    this.http.put<Game>(PLAYER, value)
    .subscribe(
      succes => {
        // this.successMessage('You have sign up successfully');
        this.setGame(succes);
        this.nextPhase();
      },
      error => {
        this.errorMessage(error.error.message);
      } 
    );
  }

  
  successMessage( message: string ) {
    Swal.fire(
      'Success!',
      message,
      'success'
    );
  }

  errorMessage( message: string ) {
    Swal.fire(
      'Ooops!',
      message,
      'error'
    );
  }


  computerPickStatus() {
    this.gameStatusHolder.turn = 'computer';
    this.gameStatusHolder.status = 'pick';
    this.setGameStatus( this.gameStatusHolder );
  }
  computerHitStatus() {
    this.gameStatusHolder.turn = 'computer';
    this.gameStatusHolder.status = 'hit';
    this.setGameStatus( this.gameStatusHolder );
  }
  computerMissStatus() {
    this.gameStatusHolder.turn = 'computer';
    this.gameStatusHolder.status = 'miss';
    this.setGameStatus( this.gameStatusHolder );
  }


  playerPickStatus() {
    this.gameStatusHolder.turn = 'player';
    this.gameStatusHolder.status = 'pick';
    this.setGameStatus( this.gameStatusHolder );
  }
  playerHitStatus() {
    this.gameStatusHolder.turn = 'player';
    this.gameStatusHolder.status = 'hit';
    this.setGameStatus( this.gameStatusHolder );
  }
  playerMissStatus() {
    this.gameStatusHolder.turn = 'player';
    this.gameStatusHolder.status = 'miss';
    this.setGameStatus( this.gameStatusHolder );
  }



  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
