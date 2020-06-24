import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GamePhaseOneComponent } from './game-phase-one/game-phase-one.component';
import { GamePhaseTwoComponent } from './game-phase-two/game-phase-two.component';
import { GamePhaseSignComponent } from './game-phase-sign/game-phase-sign.component';
import { GameComponent } from './game/game.component';
import { AuthComponentsModule } from '../auth-components/auth-components.module';
import { GameService } from './service/game.service';
import { BoardComponentsModule } from '../board-components/board-components.module';
import { BattleshipComponentsModule } from '../battleship-components/battleship-components.module';
import { GamePhaseEndComponent } from './game-phase-end/game-phase-end.component';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    GamePhaseOneComponent, 
    GamePhaseTwoComponent, 
    GamePhaseSignComponent,
    GameComponent,
    GamePhaseEndComponent
  ],
  exports: [
    GameComponent,
    GamePhaseOneComponent
  ],
  imports: [
    CommonModule,
    AuthComponentsModule,

    BoardComponentsModule,
    BattleshipComponentsModule,
    SharedModule
  ],
  providers: [
    GameService
  ]
})
export class GameComponentsModule { }
