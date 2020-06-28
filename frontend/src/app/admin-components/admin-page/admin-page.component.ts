import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit(): void {
  }


  onStyleChanged( i: number ) {
    this.adminService.changeStyle( i );
  }

  onChancesChanged( i: number ) {
    this.adminService.changeChances( i );
  }

}
