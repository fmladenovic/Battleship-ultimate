import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GamePhaseOneComponent } from './game-phase-one/game-phase-one.component';
import { GamePhaseTwoComponent } from './game-phase-two/game-phase-two.component';
import { GamePhaseSignComponent } from './game-phase-sign/game-phase-sign.component';
import { GameComponent } from './game/game.component';
import { AuthComponentsModule } from '../auth-components/auth-components.module';
import { GameService } from './service/game.service';



@NgModule({
  declarations: [
    GamePhaseOneComponent, 
    GamePhaseTwoComponent, 
    GamePhaseSignComponent,
    GameComponent
  ],
  exports: [
    GameComponent
  ],
  imports: [
    CommonModule,
    AuthComponentsModule
  ],
  providers: [
    GameService
  ]
})
export class GameComponentsModule { }
