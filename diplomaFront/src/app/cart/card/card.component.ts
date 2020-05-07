import { Component, OnInit, ViewChild, ElementRef, ChangeDetectorRef, Input, Output, EventEmitter } from '@angular/core';
import { NgForm, FormBuilder } from '@angular/forms';
import { ApiService } from 'src/app/services/api.service';
import { Router } from '@angular/router';
import { AuthStatusService } from 'src/app/profile/authstatus.service';
import { RtlService } from 'src/app/rtl.service';

@Component({
    selector: 'app-card',
    templateUrl: './card.component.html',
    styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
    @Input() addressForm: any;
    @Input() auth: any;
    @Input() cartPromo: any;
    @Input() receivedCart: any;
    @Output() receivedProduct: EventEmitter<any> = new EventEmitter<any>();
    @Output() pay: EventEmitter<any> = new EventEmitter<any>();

    @ViewChild('cardInfo', { static: false }) cardInfo: ElementRef;
    card: any;
    cardHandler = this.onChange.bind(this);
    error: string;

    receivedProducts: any;

    constructor(public api: ApiService, public router: Router, public status: AuthStatusService, public formBuilder: FormBuilder, public cd: ChangeDetectorRef, public rtl: RtlService) { }

    ngOnInit() {
    }

    ngAfterViewInit() {
        this.card = elements.create('card');
        this.card.mount(this.cardInfo.nativeElement);
        this.card.addEventListener('change', this.cardHandler);
    }

    ngOnDestroy() {
        this.card.removeEventListener('change', this.cardHandler);
        this.card.destroy();
    }

    onChange({ error }) {
        if (error) {
            this.error = error.message;
        } else {
            this.error = null;
        }
        this.cd.detectChanges();
    }

    async onSubmit(form: NgForm) {
        let count = 0;
        const { token, error } = await stripe.createToken(this.card);

        if (error) {
            console.log('Something is wrong:', error);
        } else {
            console.log('Success!', token);
            this.api.getProducts().subscribe(res => {
                for (let ob of this.receivedCart[0]) {
                    for (let obj of res.object) {
                        if (ob.productId == obj.productId) {
                            if (obj.productQuantity - ob.productQuantity < 0) {
                                console.log('Нерправильное количество товаров в корзине!');
                                this.api.deleteCart(this.auth, ob.cartId).subscribe(res => {
                                    console.log(res);
                                });
                                this.receivedProduct.emit();
                                this.pay.emit();
                                count++;
                            }
                        }
                    }
                }
                if (count == 0) {
                    this.api.chargeCard(token, this.cartPromo, this.auth, this.addressForm[0].value.phone).subscribe(res => {
                        if (res.status == '200') {
                            this.router.navigate(['success']);
                            this.api.makeOrder(this.auth, this.addressForm[0].value, res.object).subscribe(rez => {
                                this.api.bioChange(this.auth, this.addressForm[0].value, rez.object).subscribe(red => {
                                    console.log(rez);
                                });
                            });
                        }
                    });
                }
            });
        }
    }

}
