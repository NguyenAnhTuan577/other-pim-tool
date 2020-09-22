import { Employee } from './../models/employee.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class EmployeeService {
    private url = "http://localhost:8080/project";

    constructor(private http: HttpClient) { }

    getEmployees() {
        return this.http.get<Employee[]>(this.url);
    }
}