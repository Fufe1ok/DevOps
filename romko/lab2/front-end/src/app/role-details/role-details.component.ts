import { Role } from '../role';
import { Component, OnInit, Input } from '@angular/core';
import { RoleService } from '../role.service';
import { RoleListComponent } from '../role-list/role-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-role-details',
  templateUrl: './role-details.component.html',
  styleUrls: ['./role-details.component.css']
})
export class RoleDetailsComponent implements OnInit {

  id: number;
  role: Role;

  constructor(private route: ActivatedRoute,private router: Router,
    private roleService: RoleService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    
    this.roleService.getRole(this.id)
      .subscribe(data => {
        console.log(data)
        this.role = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['roles']);
  }
}