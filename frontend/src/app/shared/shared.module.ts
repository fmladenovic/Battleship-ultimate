import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpinnerService } from './service/spinner.service';
import { WebSocketService } from './service/web-socket.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
    SpinnerService,
    WebSocketService
  ]
})
export class SharedModule { }
