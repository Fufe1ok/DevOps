import { CarService } from '../car.service';
import { Car } from '../car';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user';
import { Location } from "../location";
import { LocationService } from "../location.service";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-create-car',
  templateUrl: './create-car.component.html',
  styleUrls: ['./create-car.component.css']
})
export class CreateCarComponent implements OnInit {

  car: Car = new Car();
  submitted = false;

  users: Observable<User[]>;
  locations: Observable<Location[]>;

  selectedLevel: number = -1;
  selectedLevel1: number = -1;
  carInfo;

  constructor(private carService: CarService,
    private router: Router, private userService: UserService, private locationService: LocationService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.locations = this.locationService.getLocationsList();
    this.users = this.userService.getUsersList();
  }

  newCar(): void {
    this.submitted = false;
    this.car = new Car();
  }

  async save() {
    this.carInfo = await this.carService.createCar(this.car).toPromise();
      
    var carId = JSON.parse(JSON.stringify(this.carInfo))["carId"];

    if (this.selectedLevel1 != -1 && this.selectedLevel != -1 && carId != null) {
      this.carService.addCarUser(this.selectedLevel, carId)
      .subscribe(data => {
        console.log(data);
        this.carService.addCarLocation(this.selectedLevel1, carId)
        .subscribe(data => {
          console.log(data)
        }, error => console.log(error));
      }, error => console.log(error));
    }

    if (this.selectedLevel != -1 && carId != null) {
      this.carService.addCarUser(this.selectedLevel, carId)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
    }

    if (this.selectedLevel1 != -1 && carId != null) {
      this.carService.addCarLocation(this.selectedLevel1, carId)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
    }

    this.car = new Car();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/cars']);
  }
}