import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateLocationComponent } from './create-location/create-location.component';
import { LocationDetailsComponent } from './location-details/location-details.component';
import { LocationListComponent } from './location-list/location-list.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { CarDetailsComponent } from './car-details/car-details.component';
import { CarListComponent } from './car-list/car-list.component';
import { CreateFareComponent } from './create-fare/create-fare.component';
import { FareDetailsComponent } from './fare-details/fare-details.component';
import { FareListComponent } from './fare-list/fare-list.component';
import { CreateRoleComponent } from './create-role/create-role.component';
import { RoleDetailsComponent } from './role-details/role-details.component';
import { RoleListComponent } from './role-list/role-list.component';
import { UpdateRoleComponent } from './update-role/update-role.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UpdateLocationComponent } from './update-location/update-location.component';
import { UpdateCarComponent } from './update-car/update-car.component';
import { UpdateFareComponent } from './update-fare/update-fare.component';
import { FilterPipe } from './filter.pipe';
@NgModule({
  declarations: [
    AppComponent,
    CreateUserComponent,
    UserDetailsComponent,
    UserListComponent,
    CreateLocationComponent,
    LocationDetailsComponent,
    LocationListComponent,
    CreateCarComponent,
    CarDetailsComponent,
    CarListComponent,
    CreateFareComponent,
    FareDetailsComponent,
    FareListComponent,
    CreateRoleComponent,
    RoleDetailsComponent,
    RoleListComponent,
    UpdateRoleComponent,
    UpdateUserComponent,
    UpdateLocationComponent,
    UpdateCarComponent,
    UpdateFareComponent,
    FilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
