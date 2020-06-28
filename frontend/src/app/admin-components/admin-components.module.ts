import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AdminService } from './service/admin.service';



@NgModule({
  declarations: [
    AdminPageComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [
    AdminService
  ]
})
export class AdminComponentsModule { }
