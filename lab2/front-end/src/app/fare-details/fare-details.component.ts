import { Fare } from '../fare';
import { Component, OnInit, Input } from '@angular/core';
import { FareService } from '../fare.service';
import { FareListComponent } from '../fare-list/fare-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-fare-details',
  templateUrl: './fare-details.component.html',
  styleUrls: ['./fare-details.component.css']
})
export class FareDetailsComponent implements OnInit {

  id: number;
  fare: Fare;

  constructor(private route: ActivatedRoute,private router: Router,
    private fareService: FareService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    
    this.fareService.getFare(this.id)
      .subscribe(data => {
        console.log(data)
        this.fare = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['fares']);
  }
}