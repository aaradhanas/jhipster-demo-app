import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/user/user.service";
import {ActivatedRoute} from "@angular/router";
//import {DomSanitizationService, SafeUrl} from '@angular/platform-browser';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styles: []
})
export class DashboardComponent implements OnInit {

    public url: string;
    private dashboardId: string;
    //public SafeUrl: safeUrl;
  constructor(
      private userService : UserService,
      private route: ActivatedRoute
      //, private sanitizer: DomSanitizationService
  ) { }

  ngOnInit() {
      this.url = "http://localhost:8080/test/";
      this.getDashboardURL();
  }

  getDashboardURL(){
      this.userService.getDashboard('admin').subscribe( (id) => {
          this.dashboardId = id;
          this.url += this.dashboardId;
          //this.safeUrl = this.sanitizer.bypassSecurityTrustUrl(this.url);
      });
  }

}
