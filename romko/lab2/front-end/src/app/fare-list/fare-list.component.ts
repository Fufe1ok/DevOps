import { FareDetailsComponent } from '../fare-details/fare-details.component';
import { Observable } from "rxjs";
import { FareService } from "../fare.service";
import { Fare } from "../fare";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-fare-list",
  templateUrl: "./fare-list.component.html",
  styleUrls: ["./fare-list.component.css"]
})
export class FareListComponent implements OnInit {
  fares: Observable<Fare[]>;

  foundFares: Observable<Fare[]>;

  searchText: string;

  constructor(private fareService: FareService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.fares = this.fareService.getFaresList();
  }

  search(){
    this.fareService.findFaresByUuid(this.searchText).subscribe(data => {
      console.log(data)
      this.foundFares = data;
    }, error => console.log(error));
  }

  deleteFare(id: number) {
    this.fareService.deleteFare(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  updateFare(id: number) {
    this.router.navigate(['fareUpdate', id]);
  }


  fareDetails(id: number){
    this.router.navigate(['fareDetails', id]);
  }
}