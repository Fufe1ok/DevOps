import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FareService {

  private baseUrl = 'https://lab2-256619.appspot.com';

  constructor(private http: HttpClient) { }

  getFare(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/fares/${id}`);
  }

  createFare(fare: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/fares`, fare);
  }

  updateFare(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/fares/${id}`, value);
  }

  deleteFare(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/fares/${id}`, { responseType: 'text' });
  }

  getFaresList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/fares`);
  }

  assignFareToUser(userId: number, fareId: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/users/${userId}/fares/${fareId}`, null);
  }

  dissociateFareFromUser(fareId: number): Observable<any> {
    return this.http.patch(`${this.baseUrl}/fares/${fareId}`, null);
  }

  addFareUser(userId: number, fareId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/${userId}/fares/${fareId}`, null);
  }

  findFaresByUuid(uuid: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search/fares/${uuid}`);
  }
}