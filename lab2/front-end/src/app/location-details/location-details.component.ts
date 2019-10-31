import { Location } from '../location';
import { Component, OnInit, Input } from '@angular/core';
import { LocationService } from '../location.service';
import { LocationListComponent } from '../location-list/location-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-location-details',
  templateUrl: './location-details.component.html',
  styleUrls: ['./location-details.component.css']
})
export class LocationDetailsComponent implements OnInit {

  id: number;
  location: Location;

  constructor(private route: ActivatedRoute,private router: Router,
    private locationService: LocationService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    
    this.locationService.getLocation(this.id)
      .subscribe(data => {
        console.log(data)
        this.location = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['locations']);
  }
}