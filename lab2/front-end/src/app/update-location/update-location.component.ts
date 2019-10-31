import { Location } from '../location';
import { Component, OnInit, Input } from '@angular/core';
import { LocationService } from '../location.service';
import { LocationListComponent } from '../location-list/location-list.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Car } from '../car';
import { CarService } from '../car.service';

@Component({
  selector: 'app-update-location',
  templateUrl: './update-location.component.html',
  styleUrls: ['./update-location.component.css']
})
export class UpdateLocationComponent implements OnInit {

  id: number;
  location: Location;
  updatedLocation: Location;
  submitted = false;

  locationCars: Observable<Car[]>;

  constructor(private route: ActivatedRoute,private router: Router,
    private locationService: LocationService,
    private carService: CarService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.location = new Location();
    this.updatedLocation = new Location();

    this.locationCars = this.locationService.getLocationCarsList(this.id);

    this.locationService.getLocation(this.id)
      .subscribe(data => {
        console.log(data)
        this.location = data;
      }, error => console.log(error));
  }

  updateLocation() {
    this.locationService.updateLocation(this.id, this.updatedLocation)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  save() {
    this.updatedLocation.locationCars = [];
    this.locationService.updateLocation(this.id, this.updatedLocation)
      .subscribe(data => console.log(data), error => console.log(error));
    this.location = new Location();
  }

  list(){
    this.router.navigate(['locations']);
  }

  dissociateCar(id: number) {
    this.submitted = true;
    this.carService.dissociateCarFromLocation(id)
    .subscribe(data => console.log(data), error => console.log(error));
  }
}
