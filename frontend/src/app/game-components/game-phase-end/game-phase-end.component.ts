import { Component, OnInit } from '@angular/core';
import { GameService } from '../service/game.service';

@Component({
  selector: 'app-game-phase-end',
  templateUrl: './game-phase-end.component.html',
  styleUrls: ['./game-phase-end.component.css']
})
export class GamePhaseEndComponent implements OnInit {

  win = true;

  constructor(
    private gameService: GameService
  ) { }

  ngOnInit(): void {
    this.gameService.game.subscribe( data => this.win = data.winner );
  }

  onPlayAgainClick() {
    this.gameService.setPhases([false, true, false, false]);
    this.gameService.playAgain();
  }

  onSignOutClick() {
    this.gameService.setPhases([true, false, false, false]);
    this.gameService.setGame(null);
  }

}
