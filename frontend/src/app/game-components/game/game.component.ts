import { Component, OnInit } from '@angular/core';
import { GameService } from '../service/game.service';
import { Game } from 'src/app/shared/model/game';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  phases: boolean[];
  game: Game;

  constructor(
    private gameService: GameService
  ) { }

  ngOnInit(): void {
    this.gameService.phases.subscribe( data => this.phases = data );
    this.gameService.game.subscribe( data => this.game = data );
    
  }

}
