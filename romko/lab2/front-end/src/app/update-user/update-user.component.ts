import { User } from '../user';
import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
import { UserListComponent } from '../user-list/user-list.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Role } from "../role";
import { RoleService } from "../role.service";
import { Fare } from '../fare';
import { FareService } from '../fare.service';
import { Observable } from 'rxjs';
import { CarService } from '../car.service';
import { Car } from '../car';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id: number;
  user: User;
  updatedUser: User;
  submitted = false;

  roles: Observable<Role[]>;
  carsOfUser: Observable<Car[]>;
  userFares: Observable<Fare[]>;

  selectedLevel: number;

  constructor(private route: ActivatedRoute,private router: Router,    private fareService: FareService,
    private userService: UserService, private roleService: RoleService, private carService: CarService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];

    this.reloadData();
    this.user = new User();
    this.updatedUser = new User();

    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
      }, error => console.log(error));
  }

  reloadData() {
    this.roles = this.roleService.getRolesList();
    this.carsOfUser = this.userService.getUserCarsList(this.id);
    this.userFares = this.userService.getUserFaresList(this.id);
  }

  onSubmitAssign() {
    this.submitted = true;
    this.userService.assignUserToRole(this.selectedLevel, this.id)
    .subscribe(data => {
      console.log(data)
    }, error => console.log(error));
  }

  updateUser() {
    this.userService.updateUser(this.id, this.updatedUser)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
  }

  toggleVisibility() {
    var field = document.getElementById("password");
    if (field.getAttribute("type") === "password") {
      field.setAttribute("type", "text");
    } else {
      field.setAttribute("type", "password");
    }
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  save() {
    this.updatedUser.userFares = [];
    this.userService.updateUser(this.id, this.updatedUser)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
  }

  list(){
    this.router.navigate(['users']);
  }

  dissociateFare(id: number) {
    this.submitted = true;
    this.fareService.dissociateFareFromUser(id)
    .subscribe(data => console.log(data), error => console.log(error));
  }

  dissociateCar(id: number) {
    this.submitted = true;
    this.carService.dissociateCarFromUser(id)
    .subscribe(data => console.log(data), error => console.log(error));
  }
}
