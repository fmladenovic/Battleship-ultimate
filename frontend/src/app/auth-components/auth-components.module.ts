import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

import { AuthService } from './service/auth.service';

import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { SignComponent } from './sign/sign.component';



@NgModule({
  declarations: [
    SigninComponent, 
    SignupComponent, 
    SignComponent
  ],
  exports: [
    SignComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [
    AuthService
  ]
})
export class AuthComponentsModule { }
