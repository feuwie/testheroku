import { Resp } from './../model/resp';
import { ApiService } from 'src/app/services/api.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-check-status',
    templateUrl: './check-status.component.html',
    styleUrls: ['./check-status.component.css']
})
export class CheckStatusComponent implements OnInit {

    order: any;
    receivedOrder: any;
    check: any = false;

    constructor(public api: ApiService) { }

    ngOnInit() {
    }

    checkStatus() {
        this.check = true;
        this.api.orderStatus(this.order).subscribe((res: Resp) => {
            this.receivedOrder = res.object;
        });
    }

}
