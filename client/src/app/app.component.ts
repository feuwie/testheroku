import { Component } from '@angular/core';
import { RtlService } from './rtl.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'diplomaFront';
    constructor(public rtl: RtlService) { }
}
