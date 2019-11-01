import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private baseUrl = 'https://lab2-backend.appspot.com/roles';

  constructor(private http: HttpClient) { }

  getRole(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createRole(role: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, role);
  }

  updateRole(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteRole(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getRolesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
