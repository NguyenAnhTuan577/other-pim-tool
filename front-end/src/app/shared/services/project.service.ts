import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Project } from './../models/project.model';
import { Injectable } from '@angular/core';

@Injectable()
export class ProjectService {
    private url = "http://localhost:8080/project";

    constructor(private http: HttpClient) { }

    getProjects(input: string, status: string) {
        let params = new HttpParams();
        if (input != null) {
            input = input.replace(/\s/g, '');
        }
        if (input) {
            params = params.append('input', input);
        }
        if (status) {
            params = params.append('status', status);
        }
        return this.http.get<Project[]>(this.url, {params: params});
    }

    deleteProject(project: Project) {
        const options = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            body: project
        }
        return this.http.delete(this.url, options);
    }

    updateProject(project: any) {
        return this.http.put<Project>(this.url, project);
    }

    saveProject(project: any) {
        return this.http.post(this.url, project);
    }
}