import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import Swal from 'sweetalert2';

import { GameService } from 'src/app/game-components/service/game.service';
import { GameStatus } from '../model/game-status';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {


  webSocketEndPoint = '/api';
  path = '/topic/notice';
  stompClient: any;


  constructor(
    private gameService: GameService
  ) { }


  
  connect() {
    console.log('Initialize WebSocket Connection');
    const that = this;
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    that.stompClient.connect(
        {},
        () => that.stompClient.subscribe(that.path, (response: any) => that.onNotice(response)),
        this.errorCallBack
    );
  }

  disconnect() {
    if (this.stompClient !== null) {
        this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }


  errorCallBack(error: string) {
    console.log('errorCallBack -> ' + error);
    setTimeout(() => {
        this.connect();
    }, 5000);
  }

  onNotice(response: any) {
    const notice = JSON.parse( response.body );
    this.gameService.handleAfk(notice);
  }

  

}
