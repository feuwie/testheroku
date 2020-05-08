import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

    data: any = {};


    constructor(public http: HttpClient) {
        this.lol();
        this.loler();
    }



    ngOnInit() {
    }


    lol() {
        console.log('gere');
        // this.http.get('https://firsthsedipl.herokuapp.com/api/test').subscribe(res => {
        //     console.log('lol');
        //     console.log(res);
        // });
        return this.http.get('https://firsthsedipl.herokuapp.com/api/test').pipe(map((res: Response) => res.json()));
    }

    loler() {
        this.lol().subscribe(data => {
            console.log(data);
            this.data = data;
        });
    }
}
