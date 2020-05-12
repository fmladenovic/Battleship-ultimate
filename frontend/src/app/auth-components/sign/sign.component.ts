import { Component, OnInit } from '@angular/core';
import { SignupRequest } from 'src/app/shared/dto/signup-request';
import { SigninRequest } from 'src/app/shared/dto/signin-request';

import { AuthService } from '../service/auth.service';
import { GameService } from 'src/app/game-components/service/game.service';


@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.css']
})
export class SignComponent implements OnInit {

  slide: boolean;

  constructor(
    private gameService: GameService
  ) { }

  ngOnInit(): void {
    this.slide = false;

  }

  onSignUp($event: SignupRequest) {
    this.gameService.signup($event).subscribe(
      succes => {
        this.gameService.successMessage('You have sign up successfully');
        this.slide = false;
      },
      error => {
        this.gameService.errorMessage(error.error.message)
        this.slide = true;
      } 
    );
  }

  onSignIn($event: SigninRequest) {
    this.gameService.signin($event);
  }

  onSlide() {
    this.slide = !this.slide;
  }
}
