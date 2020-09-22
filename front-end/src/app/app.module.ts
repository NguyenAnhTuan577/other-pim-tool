import { ProjectItemValidatorService } from './project-item/project-item-validator.service';
import { EmployeeService } from './shared/services/employee.service';
import { GroupService } from './shared/services/group.service';
import { Project } from './shared/models/project.model';
import { ProjectService } from './shared/services/project.service';
import { BrowserModule } from '@angular/platform-browser';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader' ;
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './menu/menu.component';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { ProjectItemComponent } from './project-item/project-item.component';
import { Routes, RouterModule } from '@angular/router';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

const appRoutes: Routes = [
  {path: '', redirectTo: 'projects-list', pathMatch: 'full'},
  {path: 'projects-list', component: ProjectsListComponent},
  {path: 'projects-list/search', component: ProjectsListComponent},
  {path: 'projects-list/edit/:projectNumber', component: ProjectItemComponent, data: Project},
  {path: 'new/project', component: ProjectItemComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    ProjectsListComponent,
    ProjectItemComponent
  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    NoopAnimationsModule
  ],
  providers: [
    ProjectService, 
    GroupService, 
    EmployeeService,
    ProjectItemValidatorService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }