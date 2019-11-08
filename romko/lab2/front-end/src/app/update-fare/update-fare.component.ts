import { Fare } from '../fare';
import { Component, OnInit, Input } from '@angular/core';
import { FareService } from '../fare.service';
import { FareListComponent } from '../fare-list/fare-list.component';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from "../user";
import { UserService } from "../user.service";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-update-fare',
  templateUrl: './update-fare.component.html',
  styleUrls: ['./update-fare.component.css']
})
export class UpdateFareComponent implements OnInit {

  id: number;
  fare: Fare;
  updatedFare: Fare;
  submitted = false;

  users: Observable<User[]>;

  selectedLevel: number;

  constructor(private route: ActivatedRoute,private router: Router,
    private fareService: FareService, private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
    this.fare = new Fare();
    this.updatedFare = new Fare();

    this.id = this.route.snapshot.params['id'];
    
    this.fareService.getFare(this.id)
      .subscribe(data => {
        console.log(data)
        this.fare = data;
      }, error => console.log(error));
  }

  reloadData() {
    this.users = this.userService.getUsersList();
  }

  onSubmitAssign() {
    this.submitted = true;
    this.fareService.assignFareToUser(this.selectedLevel, this.id)
    .subscribe(data => {
      console.log(data)
    }, error => console.log(error));
  }

  updateFare() {
    this.fareService.updateFare(this.id, this.updatedFare)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
  }

  dissociateRoom() {
    this.submitted = true;
    this.fareService.dissociateFareFromUser(this.id)
    .subscribe(data => console.log(data), error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  save() {
    this.fareService.updateFare(this.id, this.updatedFare)
      .subscribe(data => console.log(data), error => console.log(error));
    this.fare = new Fare();
  }

  list(){
    this.router.navigate(['fares']);
  }
}
