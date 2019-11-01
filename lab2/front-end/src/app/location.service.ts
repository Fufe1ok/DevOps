import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private baseUrl = 'https://lab2-backend.appspot.com';

  constructor(private http: HttpClient) { }

  getLocation(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/locations/${id}`);
  }

  getLocationCarsList(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/locations/${id}/cars`);
  }

  createLocation(location: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/locations`, location);
  }

  updateLocation(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/locations/${id}`, value);
  }

  deleteLocation(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/locations/${id}`, { responseType: 'text' });
  }

  getLocationsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/locations`);
  }

  findLocationsByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search/locations/${name}`);
  }
}
