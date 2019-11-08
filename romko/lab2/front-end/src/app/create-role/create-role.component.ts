import { RoleService } from '../role.service';
import { Role } from '../role';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-role',
  templateUrl: './create-role.component.html',
  styleUrls: ['./create-role.component.css']
})
export class CreateRoleComponent implements OnInit {

  role: Role = new Role();
  submitted = false;

  constructor(private roleService: RoleService,
    private router: Router) { }

  ngOnInit() {
  }

  newRole(): void {
    this.submitted = false;
    this.role = new Role();
  }

  save() {
    this.roleService.createRole(this.role)
      .subscribe(data => console.log(data), error => console.log(error));
    this.role = new Role();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/roles']);
  }
}