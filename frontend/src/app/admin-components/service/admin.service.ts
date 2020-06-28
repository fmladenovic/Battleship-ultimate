import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


const RULES = 'api/rules';

@Injectable({
  providedIn: 'root'
})
export class AdminService {



  constructor(
    private http: HttpClient
  ) { }


  changeStyle( i: number ) {
    let params;
    if( i === 0 ) {
      params = {
        playerRegular: 10,
        playerExtended: 10
      };
    }
    if( i === 1 ) {
      params = {
        playerRegular: 20,
        playerExtended: 10
      };
    }
    if( i === 2 ) {
      params = {
        playerRegular: 40,
        playerExtended: 20
      };
    }
    this.http.post(RULES + `/afk`, params).subscribe(
      () => {},
      error => console.error(error) 
    );
  }

  changeChances( i: number ) {
    let params = {};
    if( i === 0 ) {
      params = {
        n: 2,
        denieTime: 1,
        failTime: 2
      };
    }
    if( i === 1 ) {
      params = {
        n: 3,
        denieTime: 1,
        failTime: 2
      };
    }
    if( i === 2 ) {
      params = {
        n: 5,
        denieTime: 2,
        failTime: 10
      };
    }
    this.http.post(RULES + `/signin`, params).subscribe(
      () => {},
      error => console.error(error) 
    );
  }


}
