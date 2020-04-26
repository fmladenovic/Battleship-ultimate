import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BattleshipComponent } from './battleship/battleship.component';
import { DirectionsComponent } from './directions/directions.component';
import { BattleshipsComponent } from './battleships/battleships.component';



@NgModule({
  declarations: [
    BattleshipComponent,
    DirectionsComponent,
    BattleshipsComponent
  ],
  exports: [
    BattleshipsComponent,
    DirectionsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class BattleshipComponentsModule { }
