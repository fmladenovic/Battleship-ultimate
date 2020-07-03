import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';


const RULES = 'api/rules';

@Injectable({
  providedIn: 'root'
})
export class AdminService {



  constructor(
    private http: HttpClient
  ) { }


  changeStatus( golden: number, silver: number, bronze: number ) {
    const params = {
      golden,
      silver,
      bronze
    };
    this.http.post(RULES + `/status`, params).subscribe(
      () => this.successMessage( 'Rule is crated!'),
      error => {
        console.error(error);
        this.errorMessage('Something went wrong!');
      }
    );
  }

  changeMessage( message1: number, message2: number, message3: number ) {
    const params = {
      message1,
      message2,
      message3
    };
    this.http.post(RULES + `/message`, params).subscribe(
      () => this.successMessage( 'Rule is crated!'),
      error => {
        console.error(error);
        this.errorMessage('Something went wrong!');
      }
    );
  }


  successMessage( message: string ) {
    Swal.fire(
      'Success!',
      message,
      'success'
    );
  }

  errorMessage( message: string ) {
    Swal.fire(
      'Ooops!',
      message,
      'error'
    );
  }
}
