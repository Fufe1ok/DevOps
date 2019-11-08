import { UserDetailsComponent } from './user-details/user-details.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UserListComponent } from './user-list/user-list.component';
import { UpdateUserComponent } from './update-user/update-user.component';

import { LocationDetailsComponent } from './location-details/location-details.component';
import { CreateLocationComponent } from './create-location/create-location.component';
import { LocationListComponent } from './location-list/location-list.component';
import { UpdateLocationComponent } from './update-location/update-location.component';

import { CarDetailsComponent } from './car-details/car-details.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { CarListComponent } from './car-list/car-list.component';
import { UpdateCarComponent } from './update-car/update-car.component';

import { FareDetailsComponent } from './fare-details/fare-details.component';
import { CreateFareComponent } from './create-fare/create-fare.component';
import { FareListComponent } from './fare-list/fare-list.component';
import { UpdateFareComponent } from './update-fare/update-fare.component';

import { RoleDetailsComponent } from './role-details/role-details.component';
import { CreateRoleComponent } from './create-role/create-role.component';
import { RoleListComponent } from './role-list/role-list.component';
import { UpdateRoleComponent } from './update-role/update-role.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },

  { path: 'users', component: UserListComponent },
  { path: 'addUser', component: CreateUserComponent },
  { path: 'userDetails/:id', component: UserDetailsComponent },
  { path: 'userUpdate/:id', component: UpdateUserComponent },

  { path: 'locations', component: LocationListComponent },
  { path: 'addLocation', component: CreateLocationComponent },
  { path: 'locationDetails/:id', component: LocationDetailsComponent },
  { path: 'locationUpdate/:id', component: UpdateLocationComponent },

  { path: 'cars', component: CarListComponent },
  { path: 'addCar', component: CreateCarComponent },
  { path: 'carDetails/:id', component: CarDetailsComponent },
  { path: 'carUpdate/:id', component: UpdateCarComponent },

  { path: 'fares', component: FareListComponent },
  { path: 'addFare', component: CreateFareComponent },
  { path: 'fareDetails/:id', component: FareDetailsComponent },
  { path: 'fareUpdate/:id', component: UpdateFareComponent },

  { path: 'roles', component: RoleListComponent },
  { path: 'addRole', component: CreateRoleComponent },
  { path: 'roleDetails/:id', component: RoleDetailsComponent },
  { path: 'roleUpdate/:id', component: UpdateRoleComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
