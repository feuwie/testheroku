import { RtlService } from './../rtl.service';
import { Component, OnInit, DoCheck, ChangeDetectionStrategy, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';
import { Product } from '../model/product'; import { AuthStatusService } from '../profile/authstatus.service';
import { BehaviorSubject, Subject } from 'rxjs';
import { map } from 'rxjs/operators';


declare let EventSource: any;


@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    auth: any;
    product: Product = new Product();

    private loggedType: any;

    fontSize: number = 15;

    // public data: any;
    myData: any;

    public mySubj: any = new Subject<any>();

    cssUrl: any;


    flagPass: boolean = false;

    constructor(private api: ApiService, private router: Router, private status: AuthStatusService, private rtl: RtlService) {
        if (localStorage.getItem('auth_type') == undefined) {
            this.api.loginAnon().subscribe(res => {
                localStorage.setItem('uuid', res.object);
                localStorage.setItem('auth_type', 'guest');
                this.loggedType = 'guest';
            });
        }
        if (localStorage.getItem('auth_type') == 'guest') {
            this.loggedType = 'guest';
        } else {
            if (localStorage.getItem('auth_type') == 'customer') {
                this.loggedType = 'customer';
            } else if (localStorage.getItem('auth_type') == 'admin') {
                this.loggedType = 'admin';
            }
        }
        this.api.getEmitter().subscribe((type) => {
            this.loggedType = type;
        });
        this.connect();
    }

    connect(): void {
        let source = new EventSource('http://localhost:8080/checkchange');
        source.addEventListener('message', message => {
            if (!this.status.isChanged) {
                this.api.checkToken(localStorage.getItem('auth_token')).subscribe(res => {
                    if (res.status == '500') {
                        this.loggedType = 'guest';
                        this.logout();
                    }
                }, error => console.log(error));
            } else {
                this.status.isChanged = false;
            }
        });
    }

    ngOnInit() {
        this.auth = this.status.setAuth();
        this.api.getBlind(this.auth).subscribe(res => {
            if (res.object != null) {
                this.rtl.fontSize = res.object.fontsize;
                this.rtl.lineHeight = res.object.lineheight;
                this.rtl.colorSite = res.object.colorsite;
                this.rtl.backSite = res.object.backsite;
            }
        });
    }

    logout() {
        this.api.loginAnon().subscribe(res => {
            localStorage.clear();
            localStorage.setItem('uuid', res.object);
            localStorage.setItem('auth_type', 'guest');
            this.loggedType = 'guest';
        });
        this.router.navigate(['']);
    }

    search() {
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() =>
            this.router.navigate(['search'], { queryParams: { text: this.product.productTitle } })).then(() => this.product.productTitle = '');
    }

    changeFont(a: any) {
        if (a == '+') {
            this.rtl.fontSize++;
        } else {
            this.rtl.fontSize--;
        }
        // this.rtl.bigPicView = !this.rtl.bigPicView;
        // this.fontSize++;
        // this.api.setBlind(this.fontSize).subscribe(res => {
        //     console.log(res.object[0].fontsize);
        //     document.body.style.fontSize = res.object[0].fontsize + 'px';
        // });
    }

    changeHeight(a: any) {
        if (a == '+') {
            this.rtl.lineHeight = this.rtl.lineHeight + 0.1;
        } else {
            this.rtl.lineHeight = this.rtl.lineHeight - 0.1;
        }
    }

    changeColor(a: any) {
        if (a == 'wb') {
            this.rtl.colorSite = 'black';
            this.rtl.backSite = 'white';
        } else if (a == 'bw') {
            this.rtl.colorSite = 'white';
            this.rtl.backSite = 'black';
        } else {
            this.rtl.colorSite = 'yellow';
            this.rtl.backSite = 'blue';
        }
    }

    changeImg(a: any) {
        if (a == 'wb') {
            this.rtl.imgBw = 100;
        } else if (a == 'dis') {
            this.rtl.imgDis = 'hidden';
        } else {
            this.rtl.imgDis = 'visible';
            this.rtl.imgBw = 0;
        }
    }



    lol() {
        this.api.setBlind(this.auth, this.rtl.fontSize, this.rtl.lineHeight, this.rtl.colorSite, this.rtl.backSite).subscribe(res => {
            console.log(res);
            // document.body.style.fontSize = res.object[0].fontsize + 'px';
        });
    }
}
