import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {Subject, takeUntil} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit, OnDestroy {
  constructor(private userService: UserService) {
  }

  private destroyed$: Subject<boolean> = new Subject<boolean>();

  users = [{ id:'', name: '', email: ''}];

  ngOnInit(): void {
    this.userService.getAllUser().pipe(takeUntil(this.destroyed$)).subscribe(res => {
      this.users = res
    })
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

}
