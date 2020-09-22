import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Group } from '../models/group.model';

@Injectable()
export class GroupService {
    private url = "http://localhost:8080/group";

    constructor(private http: HttpClient) { }

    getGroups() {
        return this.http.get<Group[]>(this.url);
    }
}