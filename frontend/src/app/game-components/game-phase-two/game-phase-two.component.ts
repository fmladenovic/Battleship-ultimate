import { Component, OnInit } from '@angular/core';
import { GameStatus } from 'src/app/shared/model/game-status';
import { GameService } from '../service/game.service';
import { ThrowStmt } from '@angular/compiler';
import { SpinnerService } from 'src/app/shared/service/spinner.service';

const MESSAGES = [
    'Please select field where you think that opponents ship could be!',
    'Congradulations! \nYou have hit ship! \nPlease, continue your move!',
    'You have missed your chance. Now is computers turn!',
    'Wait for your opponent to choose his move! \nWe\'ll let you know when is your turn!',
    'Computer hit your ship! \nBe patient, your turn will be soon!',
    'Computer has missed his chance. Now is your turn!',

]

@Component({
  selector: 'app-game-phase-two',
  templateUrl: './game-phase-two.component.html',
  styleUrls: ['./game-phase-two.component.css']
})
export class GamePhaseTwoComponent implements OnInit {



  gameStatus: GameStatus;
  showSpinner: boolean;

  constructor(
    private gameService: GameService,
    private spinnerService: SpinnerService
  ) { }

  ngOnInit() {
    this.gameService.playerPickStatus();
    this.gameService.gameStatus.subscribe( data => this.gameStatus = data );
    this.spinnerService.spinner.subscribe( data => this.showSpinner = data );
  }

  extractMessage() {
    const messages = [];
    if ( this.gameStatus.turn === 'player' ) {
      messages.push('Player turn!');
      if ( this.gameStatus.status === 'pick' ) {
        messages.push(MESSAGES[0]);
      } else if( this.gameStatus.status === 'hit' ) {
        messages.push(MESSAGES[1]);
      } else {
        messages.push(MESSAGES[2]);
      }
    } else {
      messages.push('Computer turn!');
      if ( this.gameStatus.status === 'pick' ) {
        messages.push(MESSAGES[3]);
      } else if( this.gameStatus.status === 'hit' ) {
        messages.push(MESSAGES[4]);
      } else {
        messages.push(MESSAGES[5]);
      }
    }
    return messages;
  }

}
