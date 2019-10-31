import { Role } from '../role';
import { Component, OnInit, Input } from '@angular/core';
import { RoleService } from '../role.service';
import { RoleListComponent } from '../role-list/role-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-role',
  templateUrl: './update-role.component.html',
  styleUrls: ['./update-role.component.css']
})
export class UpdateRoleComponent implements OnInit {

  id: number;
  role: Role;
  updatedRole: Role;
  submitted = false;

  constructor(private route: ActivatedRoute,private router: Router,
    private roleService: RoleService) { }

  ngOnInit() {
    this.role = new Role();
    this.updatedRole = new Role();

    this.id = this.route.snapshot.params['id'];
    
    this.roleService.getRole(this.id)
      .subscribe(data => {
        console.log(data)
        this.role = data;
      }, error => console.log(error));
  }

  updateRole() {
    this.roleService.updateRole(this.id, this.updatedRole)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  save() {
    this.updatedRole.users = [];
    this.roleService.updateRole(this.id, this.updatedRole)
      .subscribe(data => console.log(data), error => console.log(error));
    this.role = new Role();
  }

  list(){
    this.router.navigate(['roles']);
  }
}