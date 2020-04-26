import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-directions',
  templateUrl: './directions.component.html',
  styleUrls: ['./directions.component.css']
})
export class DirectionsComponent implements OnInit {

  dircetionStatuses = [ false, false, false, false ];
  directions = [ [0, -1], [-1, 0], [1, 0], [0, 1] ];

  @Output() chosenDirection = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.dircetionStatuses[0] = true;
    this.chosenDirection.emit(this.directions[0]);
  }

  onDirectionClick($event: number) {
    this.dircetionStatuses = [false, false, false, false];
    this.dircetionStatuses[$event] = true;
    this.chosenDirection.emit(this.directions[$event]);
  }


}
