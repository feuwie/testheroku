import { BehaviorSubject } from 'rxjs';
// import { ApiService } from 'src/app/services/api.service';
// import { Component, OnInit, Output, ViewChild, ElementRef, ChangeDetectorRef } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { AuthStatusService } from '../profile/authstatus.service';
// import { FormBuilder, NgForm } from '@angular/forms';

// @Component({
//     selector: 'app-cart',
//     templateUrl: './cart.component.html',
//     styleUrls: ['./cart.component.css']
// })
// export class CartComponent implements OnInit {

//     @ViewChild('cardInfo', { static: false }) cardInfo: ElementRef;

//     card: any;
//     // cardHandler = this.onChange.bind(this);
//     error: string;

//     name: string = "Tom";
//     age: number = 24;

//     public auth: string;
//     receivedCart: any = [];
//     totalSum: any = 0;

//     testArray: any[] = [];
//     receivedWishlist: any;
//     receivedProducts: any = [];
//     testArrayTwo: any[] = [];

//     prodQty: any;

//     promo: any[] = [];

//     discount = 0;

//     cartPromo = 0;
//     receivedPromos: any = null;
//     cartQty: any;

//     cartTotal = 0;

//     receivedArr: any;

//     promoCode: any;

//     public bioForm: any;


//     constructor(public api: ApiService, public router: Router, public status: AuthStatusService, public formBuilder: FormBuilder, public cd: ChangeDetectorRef) {
//     }

//     ngOnInit() {
//         this.auth = this.status.setAuth();
//         this.getCart();
//         this.getWish();
//         // this.bioForm = this.formBuilder.group({
//         //     address: '',
//         //     comment: '',
//         //     dob: '',
//         //     door: '',
//         //     flat: '',
//         //     floor: '',
//         //     fullname: '',
//         //     gender: '',
//         // });
//     }

//     addPromo(title?: string) {
//         this.api.getPromo(title).subscribe(res => {
//             if (res.status == '200') {
//                 this.api.addPromo(this.auth, res.object.promoId).subscribe(res => {
//                     this.getCart();
//                 });
//             }
//         });
//     }

//     delPromo() {
//         this.api.delPromo(this.auth).subscribe(res => {
//             this.getCart();
//         });
//     }

//     calcQty() {
//         this.cartQty = 0;
//         this.receivedCart.forEach(res => {
//             this.cartQty += Number(res.productQuantity);
//         });
//     }

//     getCart() {
//         this.receivedProducts = [];
//         this.receivedCart = [];
//         this.api.getCart(this.auth).subscribe(res => {
//             // if (res.object.length > 0) {
//             this.receivedCart = res.object;
//             for (let rez of res.object) {
//                 this.api.getProductId(rez.productId).subscribe(red => {
//                     this.receivedProducts.push(red.object);
//                 });
//             }
//             if (res.object[0].promoId != 0) {
//                 this.api.getPromoId(res.object[0].promoId).subscribe(res => {
//                     this.promoCode = res.object;
//                     this.discountValue();
//                 });
//             } else {
//                 this.promoCode = null;
//                 this.discount = 0;
//             }
//             // this.calcCartTotal();
//             // this.calcQty();
//             // }
//         }, error => console.log(error));
//     }

//     discountValue() {
//         if (this.promoCode.promoType == 'nominal') {
//             this.discount = this.promoCode.promoValue;
//             this.cartPromo = this.cartTotal - this.discount;
//         } else {
//             this.discount = Math.round(this.cartTotal / 100 * this.promoCode.promoValue);
//             this.cartPromo = this.cartTotal - this.discount;
//         }
//     }

//     calcCartTotal() {
//         this.cartTotal = 0;
//         this.receivedCart.forEach(item => {
//             this.cartTotal += (item.productQuantity * item.productPrice);
//         });
//         this.cartPromo = this.cartTotal;
//     }

//     quan(prodId: any) {
//         return this.receivedCart.find(({ productId }) => productId == prodId).productQuantity;
//     }

//     change(num: any, product: any, index: any) {
//         let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
//         if (num.srcElement.value > 0 && num.srcElement.value < product.productQuantity) {
//             this.api.updateCart(this.auth, prod.cartId, num.srcElement.value).subscribe((res) => {
//                 // this.getCart();
//             });
//         }
//         if (num.srcElement.value > product.productQuantity) {
//             (document.getElementById(index)['value']) = prod.productQuantity;
//             console.log('Неверное значение!');
//         }
//         if (num.srcElement.value < 1) {
//             this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
//                 this.getCart();
//             });
//         }
//     }

//     plus(product: any) {
//         this.status.plus(product, this.auth, this.receivedCart).subscribe(res => {
//             console.log(res);
//         });
//     }

//     minus(product: any) {
//         this.status.minus(product, this.auth, this.receivedCart).subscribe(res => {
//             this.getCart();
//         });
//         // let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
//         // if (prod.productQuantity > 1) {
//         //     prod.productQuantity--;
//         //     this.api.updateCart(this.auth, prod.cartId, prod.productQuantity).subscribe((res) => {
//         //         console.log(res);
//         //     });
//         // } else {
//         //     this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
//         //         this.getCart();
//         //     });
//         // }
//     }

//     delFromCart(prodId: any) {
//         let prod = this.receivedCart.find(({ productId }) => productId == prodId);
//         this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
//             this.getCart();
//         });
//     }

//     getWish() {
//         this.api.getWishlist(this.auth).subscribe(res => {
//             this.receivedWishlist = res.object;
//         }, error => console.log(error));
//     }

//     addToWishlist(prodId: any) {
//         this.api.addToWishlist(this.auth, prodId).subscribe((res) => {
//             this.getWish();
//         });
//     }

//     delFromWishlist(prodId: any) {
//         let prod = this.receivedWishlist.find(({ productId }) => productId == prodId);
//         this.api.delFromWishlist(this.auth, prod.wishlistId).subscribe(res => {
//             this.getWish();
//         });
//     }

//     checkWish(prodId: any) {
//         if (this.receivedWishlist) {
//             if (this.receivedWishlist.find(({ productId }) => productId == prodId)) {
//                 return true;
//             }
//         }
//     }



//     // ngAfterViewInit() {
//     //     this.card = elements.create('card');
//     //     this.card.mount(this.cardInfo.nativeElement);
//     //     this.card.addEventListener('change', this.cardHandler);
//     // }

//     // ngOnDestroy() {
//     //     this.card.removeEventListener('change', this.cardHandler);
//     //     this.card.destroy();
//     // }

//     // onChange({ error }) {
//     //     if (error) {
//     //         this.error = error.message;
//     //     } else {
//     //         this.error = null;
//     //     }
//     //     this.cd.detectChanges();
//     // }

//     // async onSubmit(form: NgForm) {
//     //     const { token, error } = await stripe.createToken(this.card);

//     //     if (error) {
//     //         console.log('Something is wrong:', error);
//     //     } else {
//     //         console.log('Success!', token);
//     //         // this.api.chargeCard(token, this.lover, this.uuid).subscribe(res => {
//     //         //     localStorage.clear();
//     //         //     this.router.navigate(['success']);
//     //         //     this.api.makeOrder(this.auth).subscribe(rez => {
//     //         //         console.log(rez);
//     //         //     });
//     //         // });
//     //     }
//     // }
// }
import { ApiService } from 'src/app/services/api.service';
import { Component, OnInit, Output, ViewChild, ElementRef, ChangeDetectorRef, Input, ViewChildren, QueryList } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthStatusService } from '../profile/authstatus.service';
import { FormBuilder, NgForm } from '@angular/forms';
import { map, tap } from 'rxjs/operators';
import { RtlService } from '../rtl.service';
import { formatDate } from '@angular/common';

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

    @ViewChild('cardInfo', { static: false }) cardInfo: ElementRef;
    card: any;
    // cardHandler = this.onChange.bind(this);
    error: string;


    data: any;
    pay: any;
    fchange: any;
    fphone: any;
    femail: any;

    name = 'Tom';
    age = 24;

    public auth: string;
    receivedCart: any = [];
    totalSum: any = 0;

    testArray: any[] = [];
    receivedWishlist: any;
    receivedProducts: any = [];
    testArrayTwo: any[] = [];

    prodQty: any;

    promo: any[] = [];

    discount = 0;

    cartPromo = 0;
    receivedPromos: any = null;
    cartQty: any;

    cartTotal = 0;

    receivedArr: any;

    promoCode: any;


    public bioForm: any;


    lover: BehaviorSubject<any> = new BehaviorSubject<any>(0);


    arrobj: any = [];


    component: any;

    addressForm: any;

    receivedUser: any;


    datar: any;

    constructor(public api: ApiService, public router: Router, public status: AuthStatusService, public formBuilder: FormBuilder, public cd: ChangeDetectorRef, public rtl: RtlService) {
    }

    ngOnInit() {
        this.auth = this.status.setAuth();
        this.getCart();
        this.getProfile();
        // this.getBio();
        this.component = 'cart';
    }

    // getBio() {
    //     this.api.getBio(this.auth).subscribe(res => {
    //         this.api.getProfile(this.auth).subscribe(rez => {
    //             this.addressForm = this.formBuilder.group({
    //                 address: (res.object.address != null) ? res.object.address : '',
    //                 flat: (res.object.flat != null) ? res.object.flat : '',
    //                 ind: (res.object.ind != null) ? res.object.ind : '',
    //                 door: (res.object.door != null) ? res.object.door : '',
    //                 floor: (res.object.floor != null) ? res.object.floor : '',
    //                 doorphone: (res.object.doorphone != null) ? res.object.doorphone : '',
    //                 comment: '',
    //                 fullname: (res.object.fullname != null) ? res.object.fullname : '',
    //                 dob: (res.object.dob != null) ? formatDate(res.object.dob, 'yyyy-MM-dd', 'en_US') : '',
    //                 gender: (res.object.gender != null) ? res.object.gender : '',
    //                 phone: (rez.object.phone != null) ? rez.object.phone : '',
    //                 email: (rez.object.email != null) ? rez.object.email : '',
    //             });
    //         });
    //     }, err => {
    //         console.log(err);
    //     });
    // }

    getProfile() {
        this.api.getProfile(this.auth).subscribe(res => {
            this.receivedUser = res.object;
            this.addressForm = this.formBuilder.group({
                address: '',
                flat: '',
                ind: '',
                door: '',
                floor: '',
                doorphone: '',
                comment: '',
                fullname: (res.object.fullname != null) ? res.object.fullname : '',
                phone: (res.object.phone != null) ? res.object.phone : '',
                email: (res.object.email != null) ? res.object.email : '',
            });
        }, err => {
            console.log(err);
        });
    }

    getCart() {
        this.receivedProducts = [];
        this.arrobj = [];
        this.api.getCart(this.auth).subscribe(res => {
            this.receivedCart = res.object;
            for (const obj of res.object) {
                this.arrobj.push(obj.cartId);
            }
            this.api.getCartId(this.auth, this.arrobj).subscribe(ref => {
                this.receivedProducts = ref.object;
                this.calcTotal(res.object, this.receivedProducts);
                if (res.object.length > 0) {
                    if (res.object[0].promoId != 0) {
                        this.api.getPromoId(res.object[0].promoId).subscribe(res => {
                            if (res.status == '200') {
                                this.promoCode = res.object;
                                this.discountValue();
                            }
                        });
                    } else {
                        this.promoCode = null;
                        this.discount = 0;
                        this.cartPromo = this.cartTotal;
                    }
                }
            });
        }, error => console.log(error));
    }

    discountValue() {
        if (this.promoCode.promoType == 'nominal') {
            if (this.cartTotal - this.promoCode.promoValue > 0) {
                this.discount = this.promoCode.promoValue;
                this.cartPromo = this.cartTotal - this.discount;
            } else {
                console.log('Ошибка промокода!');
            }
        } else {
            console.log('here');
            console.log(this.cartTotal);
            this.discount = Math.round(this.cartTotal / 100 * this.promoCode.promoValue);
            this.cartPromo = this.cartTotal - this.discount;
        }
    }

    calcTotal(cart: any, prod: any) {
        this.cartTotal = 0;
        this.cartQty = 0;
        cart.forEach(item => {
            this.cartQty += item.productQuantity;
            prod.forEach(one => {
                if (item.productId == one.productId) {
                    this.cartTotal += (item.productQuantity * one.productPrice);
                }
            });
        });
    }

    addPromo(title?: string) {
        this.api.getPromo(title).subscribe(res => {
            if (res.status == '200') {
                this.api.addPromo(this.auth, res.object.promoId).subscribe(res => {
                    this.getCart();
                });
            }
        });
    }

    delPromo() {
        this.api.delPromo(this.auth).subscribe(res => {
            this.getCart();
        });
    }
}
