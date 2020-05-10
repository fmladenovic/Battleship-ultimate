import { Component, OnInit } from '@angular/core';
import { GameService } from '../service/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  phases: boolean[];

  constructor(
    private gameService: GameService
  ) { }

  ngOnInit(): void {
    this.gameService.phases.subscribe( data => this.phases = data );
    
  }

}
