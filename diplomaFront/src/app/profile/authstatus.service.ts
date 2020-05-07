import { ApiService } from 'src/app/services/api.service';
import { Injectable, Inject, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject, BehaviorSubject, of } from 'rxjs';
import { SESSION_STORAGE, StorageService } from 'angular-webstorage-service';
import { User } from '../model/user';
import { tap, map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthStatusService {
    constructor(private api: ApiService) { }
    isChanged: any = false;

    // getCart(auth: any) {
    //     return this.api.getCart(auth).pipe(map(res => {
    //         const response = {
    //             receivedCart: res.object,
    //             testArrayTwo: []
    //         };
    //         for (let i = 0; i < response.receivedCart.length; i++) {
    //             response.testArrayTwo[i] = response.receivedCart[i].productArticle;
    //         }
    //         return response;
    //     }, error => console.log(error)));
    // }

    change(num: any, prod: any, product: any, index: any, auth: any) {
        if (num.srcElement.value > 0 && num.srcElement.value <= product.productQuantity) {
            return this.api.updateCart(auth, prod.cartId, num.srcElement.value);
        }
        if (num.srcElement.value > product.productQuantity) {
            // (document.getElementById(index)['value']) = prod.productQuantity;
            // console.log('Неверное значение!');
            return of({ message: 'Error' });
        }
        if (num.srcElement.value < 1) {
            return this.api.deleteCart(auth, prod.cartId);
        }
    }

    minus(product: any, auth: any, cart: any) {
        let prod = cart.find(({ productId }) => productId == product.productId);
        if (prod.productQuantity > 1) {
            prod.productQuantity--;
            return this.api.updateCart(auth, prod.cartId, prod.productQuantity);
        } else {
            return this.api.deleteCart(auth, prod.cartId);
        }
    }

    plus(product: any, auth: any, cart: any) {
        let prod = cart.find(({ productId }) => productId == product.productId);
        if (prod.productQuantity < product.productQuantity) {
            prod.productQuantity++;
            return this.api.updateCart(auth, prod.cartId, prod.productQuantity);
        } else {
            console.log('Нет необходимого кол-ва товаров!');
        }
    }

    getWishlist(auth: any) {
        return this.api.getWishlist(auth).pipe(map(res => {
            const response = res.object;
            return response;
        }, error => console.log(error)));
    }
    getProductId(prodId: any) {
        return this.api.getProductId(prodId).pipe(map(res => {
            const response = res.object;
            return response;
        }, error => console.log(error)));
    }

    getCart(auth: any) {
        return this.api.getCart(auth).pipe(map(res => {
            const response = res.object;
            return response;
        }, error => console.log(error)));
    }

    setAuth() {
        if (localStorage.getItem('auth_type') == ('customer' || 'admin')) {
            return localStorage.getItem('auth_token');
        } else {
            return localStorage.getItem('uuid');
        }
    }
}
