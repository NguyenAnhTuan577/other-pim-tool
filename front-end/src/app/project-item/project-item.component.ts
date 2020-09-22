import { Util } from './../shared/services/util.service';
import { ProjectService } from './../shared/services/project.service';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { GroupService } from './../shared/services/group.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Group } from '../shared/models/group.model';
import { FormBuilder } from '@angular/forms'

@Component({
  selector: 'app-project-item',
  templateUrl: './project-item.component.html',
  styleUrls: ['./project-item.component.css']
})
export class ProjectItemComponent implements OnInit {
  public saveForm: FormGroup;
  public project: {};
  public groups: Group[] = [];
  public statuses: Object[] = [['new', 'New'], ['pla', 'Planned'], ['inp', 'In progress'], ['fin', 'Finished']];
  public isEdit: boolean;

  constructor(private router: Router, private groupService: GroupService, private projectService: ProjectService, private util: Util, private formBuilder: FormBuilder) {
    this.project = this.router.getCurrentNavigation().extras.state;
    this.isEdit = this.project ? true : false;
    this.groupService.getGroups().subscribe(groups => {
      this.groups = groups;
    });
  }

  ngOnInit(): void {
    this.saveForm = this.formBuilder.group({
      projectNumber: [null, Validators.required],
      name: [null, Validators.required],
      customer: [null, Validators.required],
      group: [null, Validators.required],
      members: [null, Validators.required],
      status: [null, Validators.required],
      startDate: [null, Validators.required],
      endDate: [null]
    });
    if (this.project) {
      this.saveForm.controls['projectNumber'].setValue(this.project['projectNumber']);
      this.saveForm.controls['name'].setValue(this.project['name']);
      this.saveForm.controls['customer'].setValue(this.project['customer']);
      this.saveForm.controls['group'].setValue(this.project['groupDto'].groupLeader.visa);
      let members = "";
      for (let e of this.project['employeeDtos']) {
        members += e.visa + ", ";
      }
      members = members.slice(0, members.lastIndexOf(','));
      this.saveForm.controls['members'].setValue(members);
      this.saveForm.controls['status'].setValue(this.project['status']);
      this.saveForm.controls['startDate'].setValue(this.project['startDate'].split('.').reverse().join('-'));
      if (this.project['endDate']) {
        this.saveForm.controls['endDate'].setValue(this.project['endDate'].split('.').reverse().join('-'));
      }
    }
    else {
      this.saveForm.controls['status'].setValue('new');
    }
  }

  onSubmit() {
    if (this.saveForm.valid) {
      this.project = this.project ? this.project : {};
      this.project['projectNumber'] = this.saveForm.controls['projectNumber'].value;
      this.project['name'] = this.saveForm.controls['name'].value;
      this.project['customer'] = this.saveForm.controls['customer'].value;
      this.project['status'] = this.saveForm.controls['status'].value;
      this.project['startDate'] = this.saveForm.controls['startDate'].value.split('-').reverse().join('.');
      if (this.saveForm.controls['endDate'].value) {
        this.project['endDate'] = this.saveForm.controls['endDate'].value.split('-').reverse().join('.');
      }
      for (let i = 0; i < this.groups.length; i++) {
        if (this.groups[i]['groupLeader']['visa'] === this.saveForm.controls['group'].value) {
          this.project['groupDto'] = this.groups[i];
          break;
        }
      }
      this.project['employeeDtos'] = this.saveForm.controls['members'].value.split(', ').map((e) => {return {'visa': e}});
      if (this.project['id']) {
        this.projectService.updateProject(this.project).subscribe((project) => {
          this.router.navigate(['/projects-list/search'], {queryParams: this.util.getParams()});
        });
      }
      else {
        this.projectService.saveProject(this.project).subscribe((project) => {
          this.router.navigate(['/projects-list/search'], {queryParams: this.util.getParams()});
        });
      }
    }
  }
}
