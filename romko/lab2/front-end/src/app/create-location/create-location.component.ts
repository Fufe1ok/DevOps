import { LocationService } from '../location.service';
import { Location } from '../location';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-location',
  templateUrl: './create-location.component.html',
  styleUrls: ['./create-location.component.css']
})
export class CreateLocationComponent implements OnInit {

  location: Location = new Location();
  submitted = false;

  constructor(private locationService: LocationService,
    private router: Router) { }

  ngOnInit() {
  }

  newLocation(): void {
    this.submitted = false;
    this.location = new Location();
  }

  save() {
    this.locationService.createLocation(this.location)
      .subscribe(data => console.log(data), error => console.log(error));
    this.location = new Location();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/locations']);
  }
}