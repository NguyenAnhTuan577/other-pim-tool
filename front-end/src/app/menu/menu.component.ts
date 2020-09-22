import { Util } from './../shared/services/util.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public isOnSearch: boolean = true;

  constructor(private util: Util, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {});
  }

  onSearch() {
    this.isOnSearch = true;
    this.router.navigate(['/projects-list/search'], { queryParams: this.util.getParams() });
  }

  onNew() {
    this.isOnSearch = false;
    if (this.util.params.language === 'en') {
      this.router.navigate(['/new/project']);
    }
    else {
      this.router.navigate(['/new/project'], {queryParams: {'language': this.util.params.language}});
    }
  }

}
