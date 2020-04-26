import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'battleship';

  ship: [number, number];
  direction: [number, number];

  setShip = -1;


  onChosenShip($event: [number, number]) {
    this.ship = $event;
  }

  onChosenDirection($event: [number, number]) {
    this.direction = $event;
  }

  onSetShip($event: number) {
    this.setShip = $event;
  }


}
