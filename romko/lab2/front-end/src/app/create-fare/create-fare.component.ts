import { FareService } from '../fare.service';
import { Fare } from '../fare';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from "../user";
import { UserService } from "../user.service";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-create-fare',
  templateUrl: './create-fare.component.html',
  styleUrls: ['./create-fare.component.css']
})
export class CreateFareComponent implements OnInit {

  fare: Fare = new Fare();
  submitted = false;

  users: Observable<User[]>;

  selectedLevel: number = -1;
  fareInfo;

  constructor(private fareService: FareService,
    private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersList();
  }

  newFare(): void {
    this.submitted = false;
    this.fare = new Fare();
  }

  async save() {
    this.fareInfo = await this.fareService.createFare(this.fare).toPromise();

    var fareId = JSON.parse(JSON.stringify(this.fareInfo))["fareId"];

    if (this.selectedLevel != -1 && fareId != null) {
      this.fareService.addFareUser(this.selectedLevel, fareId)
      .subscribe(data => {
        console.log(data)
      }, error => console.log(error));
    }

    this.fare = new Fare();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/fares']);
  }
}