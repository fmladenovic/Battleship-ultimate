import { Component, OnInit } from '@angular/core';
import { SignupRequest } from 'src/app/shared/dto/signup-request';
import { SigninRequest } from 'src/app/shared/dto/signin-request';

import { AuthService } from '../service/auth.service';


@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.css']
})
export class SignComponent implements OnInit {

  slide: boolean;

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.slide = false;

  }

  onSignUp($event: SignupRequest) {
    this.authService.signUp($event);
  }

  onSignIn($event: SigninRequest) {
    this.authService.signIn($event);
  }

  onSlide() {
    this.slide = !this.slide;
  }
}
