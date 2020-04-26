import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-battleship',
  templateUrl: './battleship.component.html',
  styleUrls: ['./battleship.component.css']
})
export class BattleshipComponent implements OnInit {

  @Input() battleshipType: string;
  @Input() status: boolean = null; // true - rasporedjen - disabled
                          // false - selektovan - na rasporedjivanju
                          // null - nista


  src = '';
  fileds = 0;

  constructor() { }

  ngOnInit() {
    this.setShip();
  }


  setShip() {
    if (this.battleshipType === 'bs2') {
      this.src = '../../../assets/images/battleships/bs2.png';
      this.fileds = 5;
    }

    if (this.battleshipType === 'bs4') {
      this.src = '../../../assets/images/battleships/bs4.png';
      this.fileds = 4;
    }

    if (this.battleshipType === 'bs1') {
      this.src = '../../../assets/images/battleships/bs1.png';
      this.fileds = 3;
    }

    if (this.battleshipType === 'bs3') {
      this.src = '../../../assets/images/battleships/bs3.png';
      this.fileds = 3;
    }

    if (this.battleshipType === 'bs5') {
      this.src = '../../../assets/images/battleships/bs5.png';
      this.fileds = 2;
    }

  }

}
