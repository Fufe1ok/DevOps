import { LocationDetailsComponent } from '../location-details/location-details.component';
import { Observable } from "rxjs";
import { LocationService } from "../location.service";
import { Location } from "../location";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-location-list",
  templateUrl: "./location-list.component.html",
  styleUrls: ["./location-list.component.css"]
})
export class LocationListComponent implements OnInit {
  locations: Observable<Location[]>;

  foundLocations: Observable<Location[]>;

  searchText: string;

  constructor(private locationService: LocationService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.locations = this.locationService.getLocationsList();
  }

  search(){
    this.locationService.findLocationsByName(this.searchText).subscribe(data => {
      console.log(data)
      this.foundLocations = data;
    }, error => console.log(error));
  }

  deleteLocation(id: number) {
    this.locationService.deleteLocation(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  locationDetails(id: number){
    this.router.navigate(['locationDetails', id]);
  }

  updateLocation(id: number) {
    this.router.navigate(['locationUpdate', id]);
  }
}