import { RtlService } from './../rtl.service';
import { Component, OnInit, Input, EventEmitter, Output, ViewChild, ElementRef, ViewChildren, QueryList } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AuthStatusService } from '../profile/authstatus.service';

@Component({
    selector: 'app-prod',
    templateUrl: './prod.component.html',
    styleUrls: ['./prod.component.css']
})
export class ProdComponent implements OnInit {

    @ViewChild('pRef', { static: false }) pRef: ElementRef;
    // @Input() wish: any;
    @Input() cart: any;
    @Input() auth: any;
    @Input() product: any;
    @Output() receivedWish: EventEmitter<any> = new EventEmitter<any>();
    @Output() receivedCart: EventEmitter<any> = new EventEmitter<any>();
    @Output() calcTotal: EventEmitter<any> = new EventEmitter<any>();

    prodCart: any;
    prodWish: any;


    val: any;

    @Input() component: any;


    constructor(private api: ApiService, private status: AuthStatusService, private rtl: RtlService) { }

    ngOnInit() {
        this.getCart();
        this.getWish();
        this.product = this.product[0];
    }

    cartQty(prodId: any) {
        if (this.prodCart) {
            return this.prodCart.find(({ productId }) => productId == prodId).productQuantity;
        }
    }

    getWish() {
        this.api.getWishlist(this.auth).subscribe(res => {
            this.prodWish = res.object;
        }, error => console.log(error));
    }

    getCart() {
        this.status.getCart(this.auth).subscribe(res => {
            this.prodCart = res;
        }, error => console.log(error));
    }

    delFromWishlist(prodId: any) {
        const prod = this.prodWish.find(({ productId }) => productId == prodId);
        this.api.delFromWishlist(this.auth, prod.wishlistId).subscribe(res => {
            this.getWish();
            this.receivedWish.emit(res);
        });
    }

    addToWishlist(prodId: any) {
        this.api.addToWishlist(this.auth, prodId).subscribe((res) => {
            this.getWish();
            // this.receivedWish.emit(res);
        });
    }

    checkWish(prodId: any) {
        if (this.prodWish) {
            if (this.prodWish.find(({ productId }) => productId == prodId)) {
                return true;
            }
        }
    }

    checkCart(prodId: any) {
        if (this.prodCart) {
            if (this.prodCart.find(({ productId }) => productId == prodId)) {
                return true;
            }
        }
    }

    addToCart(prodId: any) {
        this.api.addToCart(this.auth, prodId).subscribe((res) => {
            this.getCart();
        });
    }

    delFromCart(prodId: any) {
        const prod = this.prodCart.find(({ productId }) => productId == prodId);
        this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
            this.getCart();
            this.receivedCart.emit(res);
        });
    }

    quan(prod: AudioNode, changer?: any) {
        return this.prodCart.find(({ productId }) => productId == prod).productQuantity;
    }

    change(num: any, product: any, index: any) {
        const prod = this.prodCart.find(({ productId }) => productId == product.productId);
        this.status.change(num, prod, product, index, this.auth).subscribe(res => {
            if (res.message == 'DEL_CART') {
                this.getCart();
                this.receivedCart.emit(res);
            } else if (res.message == 'UPD_CART') {
                this.receivedCart.emit(res);
            } else if (res.message == 'Error') {
                this.pRef.nativeElement.value = prod.productQuantity;
            }
        });
    }

    plus(product: any) {
        this.status.plus(product, this.auth, this.prodCart).subscribe(res => {
            this.receivedCart.emit(res);
        });
    }

    minus(product: any) {
        this.status.minus(product, this.auth, this.prodCart).subscribe(res => {
            this.getCart();
            this.receivedCart.emit(res);
        });
    }

}
