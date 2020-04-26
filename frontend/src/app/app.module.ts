import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardComponentsModule } from './board-components/board-components.module';
import { BattleshipComponentsModule } from './battleship-components/battleship-components.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    BoardComponentsModule,
    BattleshipComponentsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
