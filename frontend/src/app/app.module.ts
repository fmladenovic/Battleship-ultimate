import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardComponentsModule } from './board-components/board-components.module';
import { BattleshipComponentsModule } from './battleship-components/battleship-components.module';
import { GameComponentsModule } from './game-components/game-components.module';
import { AuthComponentsModule } from './auth-components/auth-components.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,


    GameComponentsModule,
    AuthComponentsModule,

    BoardComponentsModule,
    BattleshipComponentsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
