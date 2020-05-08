import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
    constructor(public http: HttpClient) {
    }

    ngOnInit() {
    }


    lol() {
        console.log('gere');
        this.http.get('https://firsthsedipl.herokuapp.com/test').subscribe(res => {
            console.log(res);
        });
    }
}
