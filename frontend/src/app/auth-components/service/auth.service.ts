import { Injectable } from '@angular/core';
import { SignupRequest } from 'src/app/shared/dto/signup-request';
import { SigninRequest } from 'src/app/shared/dto/signin-request';
import { GameService } from 'src/app/game-components/service/game.service';
import { Game } from 'src/app/shared/model/game';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private gameService: GameService
  ) { }

  signUp(user: SignupRequest) {
    console.log(user);
  }

  signIn(user: SigninRequest) {
    //TEST
    this.gameService.setGame(new Game());
    //
    this.gameService.nextPhase();
    
  }

}
