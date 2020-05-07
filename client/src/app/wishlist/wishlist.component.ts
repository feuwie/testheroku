import { AuthStatusService } from './../profile/authstatus.service';
import { ApiService } from 'src/app/services/api.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';

@Component({
    selector: 'app-wishlist',
    templateUrl: './wishlist.component.html',
    styleUrls: ['./wishlist.component.css']
})
// export class WishlistComponent implements OnInit {


//     public auth: string;
//     receivedWishlist: any = [];
//     receivedCart: any;
//     receivedProducts: any = [];

//     constructor(public api: ApiService, public status: AuthStatusService) { }

//     ngOnInit() {
//         this.auth = this.status.setAuth();
//         this.getWishlist();
//         this.getCart();
//     }

//     delFromWishlist(prodId: any) {
//         let prod = this.receivedWishlist.find(({ productId }) => productId == prodId);
//         this.api.delFromWishlist(this.auth, prod.wishlistId).subscribe(res => {
//             this.receivedWishlist = []; // check
//             this.receivedProducts = []; // check
//             this.getWishlist();
//         });
//     }

//     // sortBy(val: string): void {
//     // if (val === 'Сначала (а-я)') {
//     //     this.receivedProducts.sort((a: Product, b: Product) => {
//     //         return a.productTitle[0].localeCompare(b.productTitle[0]);
//     //     });
//     // }
//     // if (val === 'Сначала (я-а)') {
//     //     this.receivedProducts.sort((a: Product, b: Product) => {
//     //         return b.productTitle[0].localeCompare(a.productTitle[0]);
//     //     });
//     // }
//     // if (val === 'Сначала дешевые') {
//     //     this.receivedProducts.sort((a: Product, b: Product) => {
//     //         return a.productPrice - b.productPrice;
//     //     });
//     // }
//     // if (val === 'Сначала дорогие') {
//     //     this.receivedProducts.sort((a: Product, b: Product) => {
//     //         return b.productPrice - a.productPrice;
//     //     });
//     // }
//     // }



//     // getCart() {
//     //     // if (this.api.isAuthenticated) {
//     //     // this.auth = this.api.getToken();
//     //     // this.api.getCart(this.auth).subscribe(res => {
//     //     //     this.receivedCart = res.object;
//     //     //     for (let i = 0; i < this.receivedCart.length; i++) {
//     //     //         this.testArrayTwo[i] = this.receivedCart[i].productArticle;
//     //     //     }
//     //     // }, error => console.log(error));
//     //     // }
//     //     // this.status.getCart(this.auth).subscribe(res => {
//     //     //     this.receivedCart = this.status.receivedCart;
//     //     //     this.testArrayTwo = this.status.testArrayTwo;
//     //     // });
//     //     this.status.getCart(this.auth).subscribe(res => {
//     //         this.receivedCart = res.receivedCart;
//     //         this.testArrayTwo = res.testArrayTwo;
//     //     });
//     // }

//     checkCart(prodId: any) {
//         if (this.receivedCart) {
//             if (this.receivedCart.find(({ productId }) => productId == prodId)) {
//                 return true;
//             }
//         }
//     }

//     getWishlist() {
//         this.status.getWishlist(this.auth).subscribe(res => {
//             this.receivedWishlist = res;
//             for (let rez of res) {
//                 this.status.getProductId(rez.productId).subscribe(red => {
//                     this.receivedProducts.push(red);
//                 });
//             }
//         });

//         // this.api.getWishlist(this.auth).subscribe(res => {
//         //     this.receivedWishlist = res.object;
//         //     for (let rez of res.object) {
//         //         this.api.getProductId(rez.productId).subscribe(red => {
//         //             this.receivedProducts.push(red.object);
//         //         });
//         //     }
//         // }, error => console.log(error));
//     }

//     getCart() {
//         this.status.getCart(this.auth).subscribe(res => {
//             this.receivedCart = res;
//         });
//         // this.api.getCart(this.auth).subscribe(res => {
//         //     this.receivedCart = res.object;
//         // }, error => console.log(error));
//     }

//     addToCart(prodId: any) {
//         this.api.addToCart(this.auth, prodId).subscribe((res) => {
//             this.getCart();
//         });
//     }

//     quan(prod: any) {
//         return this.receivedCart.find(({ productId }) => productId == prod).productQuantity;
//     }

//     change(num: any, product: any, index: any) {
//         // let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
//         // if (num.srcElement.value > 0 && num.srcElement.value < product.productQuantity) {
//         //     this.api.updateCart(this.auth, prod.cartId, num.srcElement.value).subscribe((res) => {
//         //         this.getCart();
//         //     });
//         // }
//         // if (num.srcElement.value > product.productQuantity) {
//         //     (document.getElementById(index)['value']) = prod.productQuantity;
//         //     console.log('Неверное значение!');
//         // }
//         // if (num.srcElement.value < 1) {
//         //     this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
//         //         this.getCart();
//         //     });
//         // }
//         let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
//         this.status.change(num, prod, product, index, this.auth).subscribe(res => {
//             console.log(res);
//             if (res.message == 'DEL_CART') {
//                 this.getCart();
//             } else if (res.message == 'Error') {
//                 (document.getElementById(index)['value']) = prod.productQuantity;
//             }
//         });
//     }

//     plus(product: any) {
//         this.status.plus(product, this.auth, this.receivedCart).subscribe(res => {
//             console.log(res);
//         });
//         // let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
//         // if (prod.productQuantity < product.productQuantity) {
//         //     prod.productQuantity++;
//         //     this.api.updateCart(this.auth, prod.cartId, prod.productQuantity).subscribe((res) => {
//         //         this.getCart();
//         //     });
//         // } else {
//         //     console.log('Нет необходимого кол-ва товаров!');
//         // }
//     }

//     minus(product: any) {
//         this.status.minus(product, this.auth, this.receivedCart).subscribe(res => {
//             this.getCart();
//         });
//         // let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
//         // if (prod.productQuantity > 1) {
//         //     prod.productQuantity--;
//         //     this.api.updateCart(this.auth, prod.cartId, prod.productQuantity).subscribe((res) => {
//         //         this.getCart();
//         //     });
//         // } else {
//         //     this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
//         //         this.getCart();
//         //     });
//         // }
//     }

// }

export class WishlistComponent implements OnInit {


    public auth: string;
    receivedProducts: any = [];
    wish: any;
    cart: any;
    component: any;

    ord: any;

    constructor(public api: ApiService, public status: AuthStatusService) { }

    ngOnInit() {
        this.auth = this.status.setAuth();
        this.getWishlist();
        this.component = 'wishlist';
    }

    getWishlist() {
        this.receivedProducts = [];
        this.status.getWishlist(this.auth).subscribe(res => {
            for (let rez of res) {
                this.status.getProductId(rez.productId).subscribe(red => {
                    this.receivedProducts.push(red);
                });
            }
        });
    }
}
