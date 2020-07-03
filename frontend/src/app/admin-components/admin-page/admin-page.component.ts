import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  golden = 1;
  silver = 1;
  bronze = 1;

  message1 = 1;
  message2 = 1;
  message3 = 1;



  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit(): void {

  }

  setGolden($event) {
    this.golden = $event.target.value;
  }
  setsilver($event) {
    this.silver = $event.target.value;
  }
  setBronze($event) {
    this.bronze = $event.target.value;
  }
  setMessage1($event) {
    this.message1 = $event.target.value;
  }
  setMessage2($event) {
    this.message2 = $event.target.value;
  }
  setMessage3($event) {
    this.message3 = $event.target.value;
  }


  onConfirmStatus() {
    if( this.golden === this.silver || this.golden === this.bronze || this.bronze === this.silver ) {
      Swal.fire(
        'Ooops!',
        "Victories must be different values!",
        'error'
      );
      return;
    }
    this.adminService.changeStatus(this.golden, this.silver, this.bronze);
  }

  onConfirmMessage() {
    if( this.message1 === this.message2 || this.message1 === this.message3 || this.message2 === this.message3 ) {
      Swal.fire(
        'Ooops!',
        "Hits must be different values!",
        'error'
      );
      return;
    }
    this.adminService.changeMessage(this.message1, this.message2, this.message3);
  }

}
