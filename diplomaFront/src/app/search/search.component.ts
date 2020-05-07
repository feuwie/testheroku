import { AuthStatusService } from './../profile/authstatus.service';
import { ApiService } from 'src/app/services/api.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
    receivedProducts: any;
    component: any;
    auth: any;

    constructor(private route: ActivatedRoute, private api: ApiService, private status: AuthStatusService) {
    }

    ngOnInit() {
        this.auth = this.status.setAuth();
        this.component = 'search';
        this.api.getProductTitle(this.route.snapshot.queryParams.text).subscribe(res => {
            this.receivedProducts = res.object;
        }, error => console.log(error));
    }
}
