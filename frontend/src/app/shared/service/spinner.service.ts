import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  private spinnerOb = new BehaviorSubject<boolean>(false);
  private spinnerHolder = false;
  spinner = this.spinnerOb.asObservable();


  typingTimer;                // timer identifier
  doneTypingInterval = 500;

  constructor() { }


  showSpinner() {
    this.spinnerHolder = true;
    this.spinnerOb.next(this.spinnerHolder);
  }

  hideSpinner() {
    this.spinnerHolder = false;
    this.spinnerOb.next(this.spinnerHolder);
  }

  onKeydown() {
    this.showSpinner();
    clearTimeout(this.typingTimer);
  }

  onKeyup(context: any, func: any, params: any[]) {
    clearTimeout(this.typingTimer);
    this.typingTimer = setTimeout(() => func(this, context, params), this.doneTypingInterval);
  }

}
