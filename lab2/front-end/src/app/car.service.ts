import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private baseUrl = 'https://lab2-backend.appspot.com';

  constructor(private http: HttpClient) { }

  getCar(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/cars/${id}`);
  }

  createCar(car: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/cars`, car);
  }

  updateCar(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/cars/${id}`, value);
  }

  deleteCar(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/cars/${id}`, { responseType: 'text' });
  }

  getCarsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/cars`);
  }

  assignCarToUser(userId: number, carId: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/users/${userId}/cars/${carId}`, null);
  }

  dissociateCarFromUser(carId: number): Observable<any> {
    return this.http.patch(`${this.baseUrl}/cars/${carId}`, null);
  }

  addCarUser(userId: number, carId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/${userId}/cars/${carId}`, null);
  }

  findCarsByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search/cars/${name}`);
  }

  assignCarToLocation(locationId: number, carId: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/locations/${locationId}/cars/${carId}`, null);
  }

  dissociateCarFromLocation(carId: number): Observable<any> {
    return this.http.patch(`${this.baseUrl}/cars/${carId}`, null);
  }

  addCarLocation(locationId: number, carId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/locations/${locationId}/cars/${carId}`, null);
  }
}
