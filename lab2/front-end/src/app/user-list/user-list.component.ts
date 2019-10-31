import { Component, OnInit } from '@angular/core';

import { UserDetailsComponent } from '../user-details/user-details.component';
import { Observable } from "rxjs";
import { UserService } from "../user.service";
import { User } from "../user";
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: Observable<User[]>;
  foundUsers: Observable<User[]>;

  searchText: string;

  constructor(private userService: UserService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersList();
  }

  search(){
    this.userService.findUsersByFirstName(this.searchText).subscribe(data => {
      console.log(data)
      this.foundUsers = data;
    }, error => console.log(error));
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  userDetails(id: number){
    this.router.navigate(['userDetails', id]);
  }

  updateUser(id: number) {
    this.router.navigate(['userUpdate', id]);
  }

}
