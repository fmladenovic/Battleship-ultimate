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

  private phasesOb = new BehaviorSubject<boolean[]>([true, false, false, false]);
  private phasesHolder = [true, false, false, false];
  phases = this.phasesOb.asObservable();

  
  private gameOb = new BehaviorSubject<Game>(new Game());
  private gameHolder: Game = new Game();
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
    this.playerMovesInRow.push(move);
    this.gameHolder.playerMoves.push(move);
    this.setGame(this.gameHolder);

    if( move.hit ) {
      this.playerHitStatus();
      this.onWin();
    } else {
      this.playerMissStatus();
      this.putPlayerMoves();

    }
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



  handleAfk(notice: any) {
    // console.log(this.gameHolder.id , notice.gameId)
    if(notice.gameId === this.gameHolder.id) {
      if( !notice.status ) {
        Swal.fire(
          'HURRY UP!',
          'Your turn is about to end!',
          'warning'
        );
      }
      else {
        Swal.fire(
          'SORRY!',
          'You have lost your move!',
          'error'
        ).
        then(() => this.computerPlay() );
      }
    }
  }

  computerPlay() {
    // this.spinnerService.showSpinner(); Enable animation for bombing
    this.computerPickStatus();
    this.http.put<Move>(GAME + `/${this.gameHolder.id}/computer`, null)
    .subscribe(
      success => {
        this.delay(1500).then(
          () => {
            this.spinnerService.hideSpinner();
            this.addComputerMove(success);
            if ( success.hit ) {
              this.delay(1500).then( () => {
                if ( this.checkComputerWin() ) {
                  this.setWin(false);
                } else {
                  this.computerPlay();
                }
              });
            } else {
              this.delay(1500).then( () => this.playerPickStatus() );
            }
          });
      },
      error => {
        this.errorMessage(error.error.message);
        this.spinnerService.hideSpinner();
      }
    );
  }




  putPlayerMoves() {

    this.http.put(GAME + `/${this.gameHolder.id}/moves`, this.playerMovesInRow)
    .subscribe(
      success => {    
          this.playerMovesInRow = [];
          this.delay(1500).then( () => this.computerPlay() );
      },
      error => this.errorMessage(error.error.message)
    );
    
  }

  setWin(value: boolean) {
    // this.spinnerService.showSpinner(); Enable animation for bombing
    this.http.put(GAME + `/${this.gameHolder.id}/end-game?victory=${value}`, null)
    .subscribe(
      success => {
        this.gameHolder.winner = value;
        this.nextPhase();
      },
      error => {
        this.errorMessage(error.error.message);
        this.spinnerService.hideSpinner();
      }
    );
  }

  onWin() {
    if ( this.checkPlayerWin() ) {
    this.http.put(GAME + `/${this.gameHolder.id}/moves`, this.playerMovesInRow)
      .subscribe(
        success => {    
            this.playerMovesInRow = [];
            this.setWin(true);
        },
        error => this.errorMessage(error.error.message)
      );
    }
  }







  signup( value: SignupRequest ) {
    return this.http.post(PLAYER, value);
    
  }

  setShips() {
    const ships = [];
    this.gameHolder.playerShips.ships.forEach( ship => ships.push(ship));

    //delete
    ships.forEach( ship => {
      let forPrint = "Ship ship = new Ship(Arrays.asList(";
      ship.positions.forEach( tuple => forPrint = forPrint + " new Tuple(" + tuple.x +", " + tuple.y + "),"  );
      forPrint = forPrint + "), " + ship.size + ");";
      console.log(forPrint);
    })
    //

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

  playAgain() {
    this.http.put<Game>(PLAYER + `/${this.gameHolder.player.id}/play-again`, null)
    .subscribe(
      succes => {
        this.setGame(succes);
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

  warnMessage( message: string ) {
    Swal.fire(
      'HURRY UP!',
      message,
      'warning'
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


  checkPlayerWin() {
    return this.isWin(this.gameHolder.playerMoves);
  }

  checkComputerWin() {
    return this.isWin(this.gameHolder.computerMoves);
  }

  isWin(list: Move[]) {
    let counter = 0;
    list.forEach( move => {
      if(move.hit){
        counter++;
      }
    });
    return counter === 17
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
