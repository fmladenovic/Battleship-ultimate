import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import Swal from 'sweetalert2';
import { SigninRequest } from 'src/app/shared/dto/signin-request';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  @Output() signIn = new EventEmitter();

  credential: SigninRequest;

  constructor() { }

  ngOnInit() {
    this.credential = new SigninRequest();
  }

  onSubmit() {
    if (!this.credential.email && !this.credential.password) {
      this.errorHandling('Email and password are required');
      return;
    }
    this.signIn.emit(this.credential);
  }

  errorHandling(message: string) {
    Swal.fire(
      'Oops!',
      message,
      'error'
    );

  }
}
