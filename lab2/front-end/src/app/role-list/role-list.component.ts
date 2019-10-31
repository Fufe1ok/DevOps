import { RoleDetailsComponent } from '../role-details/role-details.component';
import { Observable } from "rxjs";
import { RoleService } from "../role.service";
import { Role } from "../role";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-role-list",
  templateUrl: "./role-list.component.html",
  styleUrls: ["./role-list.component.css"]
})
export class RoleListComponent implements OnInit {
  roles: Observable<Role[]>;

  constructor(private roleService: RoleService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.roles = this.roleService.getRolesList();
  }

  deleteRole(id: number) {
    this.roleService.deleteRole(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }


  updateRole(id: number) {
    this.router.navigate(['roleUpdate', id]);
  }

  roleDetails(id: number){
    this.router.navigate(['roleDetails', id]);
  }
}