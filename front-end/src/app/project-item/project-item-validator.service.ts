import { ProjectService } from './../shared/services/project.service';
import { Injectable } from '@angular/core';
@Injectable()
export class ProjectItemValidatorService {

    constructor(private projectService: ProjectService) {}

    projectNumberValidator(number: number) {
    }
}