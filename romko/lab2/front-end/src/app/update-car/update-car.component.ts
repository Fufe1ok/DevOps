import { Car } from '../car';
import { Component, OnInit, Input } from '@angular/core';
import { CarService } from '../car.service';
import { CarListComponent } from '../car-list/car-list.component';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user';
import { Location } from "../location";
import { LocationService } from "../location.service";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.css']
})
export class UpdateCarComponent implements OnInit {

  id: number;
  car: Car;
  updatedCar: Car;
  submitted = false;

  users: Observable<User[]>;
  locations: Observable<Location[]>;

  selectedLevel: number;
  selectedLevel1: number;

  constructor(private route: ActivatedRoute,private router: Router,
    private carService: CarService, private userService: UserService, private locationService: LocationService) { }

  ngOnInit() {
    this.reloadData();
    this.car = new Car();
    this.updatedCar = new Car();

    this.id = this.route.snapshot.params['id'];
    
    this.carService.getCar(this.id)
      .subscribe(data => {
        console.log(data)
        this.car = data;
      }, error => console.log(error));
  }

  reloadData() {
    this.locations = this.locationService.getLocationsList();
    this.users = this.userService.getUsersList();
  }

  onSubmitAssign() {
    this.submitted = true;
    this.carService.assignCarToUser(this.selectedLevel, this.id)
    .subscribe(data => {
      console.log(data)
    }, error => console.log(error));
    this.carService.assignCarToLocation(this.selectedLevel1, this.id)
    .subscribe(data => {
      console.log(data)
    }, error => console.log(error));
  }

  updateCar() {
    this.carService.updateCar(this.id, this.updatedCar)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
  }

  dissociateUser() {
    this.submitted = true;
    this.carService.dissociateCarFromUser(this.id)
    .subscribe(data => console.log(data), error => console.log(error));
  }

  dissociateLocation() {
    this.submitted = true;
    this.carService.dissociateCarFromLocation(this.id)
    .subscribe(data => console.log(data), error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  save() {
    this.carService.updateCar(this.id, this.updatedCar)
      .subscribe(data => console.log(data), error => console.log(error));
    this.car = new Car();
  }

  list(){
    this.router.navigate(['cars']);
  }
}
