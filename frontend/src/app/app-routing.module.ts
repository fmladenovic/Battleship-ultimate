import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameComponent } from './game-components/game/game.component';
import { AdminPageComponent } from './admin-components/admin-page/admin-page.component';


const routes: Routes = [
  { path: 'admin', component: AdminPageComponent },
  { path: '**', component: GameComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
