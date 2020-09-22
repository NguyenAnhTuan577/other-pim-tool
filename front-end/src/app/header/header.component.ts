import { Util } from './../shared/services/util.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() language = new EventEmitter<string>();
  public languageStyle = 'en';

  constructor(private util: Util, private activatedRoute: ActivatedRoute, private route: Router) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params.language) {
        this.util.params['language'] = params.language;
        this.language.emit(params.language);
        this.languageStyle = params.language;
      }
    });
  }

  chooseLanguage(language: string) {
    this.util.params['language'] = language;
    this.languageStyle = language;    
    this.route.navigate([this.route.url.split('?')[0]], {queryParams: this.util.getParams()});
    this.language.emit(language);
  }

}
