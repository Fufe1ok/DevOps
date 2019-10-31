import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'https://lab2-256619.appspot.com';

  constructor(private http: HttpClient) { }

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/users/${id}`);
  }

  getUserFaresList(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/users/${id}/fares`);
  }

  getUserCarsList(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/users/${id}/cars`);
  }

  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/users`, user);
  }

  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/users/${id}`, value);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/users/${id}`, { responseType: 'text' });
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/users`);
  }

  assignUserToRole(roleId: number, userId: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/roles/${roleId}/users/${userId}`, null);
  }

  addUserRole(roleId: number, userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/roles/${roleId}/users/${userId}`, null);
  }

  findUsersByFirstName(firstName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search/users/${firstName}`);
  }
}
