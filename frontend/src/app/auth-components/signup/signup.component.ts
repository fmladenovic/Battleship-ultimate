import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { SignupRequest } from 'src/app/shared/dto/signup-request';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;

  @Output() signUp = new EventEmitter();

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.registerForm = this.formBuilder.group({
      nick: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });

  }

  get f() { return this.registerForm.controls; }
  get nick() { return this.registerForm.get('nick'); }
  get email() { return this.registerForm.get('email'); }
  get password() { return this.registerForm.get('password'); }


  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
        return;
    }
    const {nick, email, password } = this.registerForm.value;
    const req = new SignupRequest();
    req.email = email;
    req.password = password;
    req.nick = nick;
    this.signUp.emit(req);
  }
}
