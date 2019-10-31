import { UserService } from '../user.service';
import { User } from '../user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from "../role";
import { RoleService } from "../role.service";
import { Observable } from 'rxjs';


@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  submitted = false;
  userInfo;
  selectedLevel: number;

  roles: Observable<Role[]>;

  constructor(private userService: UserService,
    private router: Router, private roleService: RoleService) { }

  ngOnInit() {
    this.reloadData();
  }

  newUser(): void {
    this.submitted = false;
    this.user = new User();
  }

  reloadData() {
    this.roles = this.roleService.getRolesList();
  }

  async save() {
    this.userInfo = await this.userService.createUser(this.user).toPromise();

    var userId = JSON.parse(JSON.stringify(this.userInfo))["userId"];

    if (userId != null) {
      this.userService.addUserRole(this.selectedLevel, userId)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
    }

    this.user = new User();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  toggleVisibility() {
    var field = document.getElementById("password");
    if (field.getAttribute("type") === "password") {
      field.setAttribute("type", "text");
    } else {
      field.setAttribute("type", "password");
    }
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}
