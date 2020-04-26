import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BoardComponent } from './board/board.component';
import { BoardSecondPhaseComponent } from './board-second-phase/board-second-phase.component';
import { BoardFirstPhaseComponent } from './board-first-phase/board-first-phase.component';



@NgModule({
  declarations: [
    BoardComponent,
    BoardFirstPhaseComponent,
    BoardSecondPhaseComponent
  ],
  exports: [
    BoardComponent,
    BoardFirstPhaseComponent,
    BoardSecondPhaseComponent
  ],
  imports: [
    CommonModule
  ]
})
export class BoardComponentsModule { }
