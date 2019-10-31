import { Car } from '../car';
import { Component, OnInit, Input } from '@angular/core';
import { CarService } from '../car.service';
import { CarListComponent } from '../car-list/car-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {

  id: number;
  car: Car;

  constructor(private route: ActivatedRoute,private router: Router,
    private carService: CarService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    
    this.carService.getCar(this.id)
      .subscribe(data => {
        console.log(data)
        this.car = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['cars']);
  }
}