import { Util } from './../shared/services/util.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from './../shared/models/project.model';
import { ProjectService } from './../shared/services/project.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.css']
})
export class ProjectsListComponent implements OnInit {
  public projects: Project[];
  public checkedNumber: number = 0;
  public deletedProjects: Project = new Project();
  public faTrashAlt = faTrashAlt;

  public searchForm: FormGroup;
  
  constructor(private util: Util, private activatedRoute: ActivatedRoute, private router: Router, private projectService: ProjectService) { 
    this.searchForm = new FormGroup({
      input: new FormControl(this.util.params.input ? this.util.params.input: ''),
      status: new FormControl(this.util.params.status ? this.util.params.status : '')
    });
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.util.params.input = params.input;
      this.util.params.status = params.status;
      this.projectService
      .getProjects(this.searchForm.value.input, this.searchForm.value.status)
      .subscribe(projects => {
        this.projects = projects;
      });
    });
  }

  navigate() {
    const input = this.searchForm.value.input.replace(/\s/g, '');
    const status = this.searchForm.value.status;
    this.util.params.input = input;
    this.util.params.status = status;
    this.router.navigate(['/projects-list/search'], { queryParams: this.util.getParams() });
  }

  onSearch() {
    this.navigate();
    this.checkedNumber = 0;
    this.deletedProjects.deletedIds = [];
    this.projectService
      .getProjects(this.searchForm.value.input, this.searchForm.value.status)
      .subscribe(projects => {
        this.projects = projects;
      });
  }

  onReset() {
    this.navigate();
    this.checkedNumber = 0;
    this.deletedProjects.deletedIds = [];
    this.projectService
      .getProjects('', '')
      .subscribe(projects => {
        this.projects = projects;
      });
  }

  isChecked(id: number) {
    return this.deletedProjects.deletedIds.indexOf(id);
  }

  checked(event: any, project: Project) {
    if (event.target.checked === true) {
      this.checkedNumber++;
      this.deletedProjects.deletedIds.push(project.id);
    }
    else {
      this.checkedNumber--;
      const index = this.deletedProjects.deletedIds.indexOf(project.id);
      this.deletedProjects.deletedIds.splice(index, 1);
    }
  }

  onDeleteProjects(project: Project) {
    if (project.id !== undefined) {
      const index = this.deletedProjects.deletedIds.indexOf(project.id);
      if (index !== -1) {
        this.checkedNumber--;
        this.deletedProjects.deletedIds.splice(index, 1);
      }
      project.deletedIds = [project.id];
    }
    this.projectService
      .deleteProject(project)
      .subscribe(() => {
        this.projectService
          .getProjects(this.searchForm.value.input, this.searchForm.value.status)
          .subscribe(projects => {
            this.projects = projects;
            if (project.id === undefined) {
              this.checkedNumber = 0;
              this.deletedProjects.deletedIds = [];
            }
          });
      });
  }

  onEdit(project: Project) {
    if (this.util.params.language === 'en') {
      this.router.navigate(['/projects-list/edit/' + project.projectNumber], {state: project});
    }
    else {
      this.router.navigate(['/projects-list/edit/' + project.projectNumber], {state: project, queryParams: {'language': this.util.params.language}});
    }
  }
}