import { Injectable, Inject, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SESSION_STORAGE, StorageService } from 'angular-webstorage-service';
import { User } from '../model/user';

@Injectable({
    providedIn: 'root'
})
export class ApiService {

    @Output() loggedIn: EventEmitter<any> = new EventEmitter<any>();
    // @Output() changeAction: EventEmitter<any> = new EventEmitter<any>();

    number: any;

    constructor(public http: HttpClient) { }


    emailReg(email: any): Observable<any> {
        return this.http.post('https://firsthsedipl.herokuapp.com/verifyemail', email);
    }

    emailCheck(code: String, email: any): Observable<any> {
        // email = email.replace('+', '%2B');
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/checkemail' + '?code=' + code + '&email=' + email);
    }

    phoneReg(user: User): Observable<any> {
        return this.http.post('https://firsthsedipl.herokuapp.com/verifyphone', user);
    }
    // phoneCheck(str: String, code: String, phone: any): Observable<any> {
    //     const body = { str: str, code: code, phone: phone };
    //     return this.http.post('https://firsthsedipl.herokuapp.com/checkphone', body);
    // }
    phoneCheck(str: String, code: String, phone: any): Observable<any> {
        phone = phone.replace('+', '%2B');
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/checkphone' + '?str=' + str + '&code=' + code + '&phone=' + phone);
    }

    // auth

    // check(user: User): Observable<any> {
    //     return this.http.post('https://firsthsedipl.herokuapp.com/user/check', user);
    // }

    register(user: User, pass: any): Observable<any> {
        user.password = pass.password;
        return this.http.post('https://firsthsedipl.herokuapp.com/user/registration', user);
    }

    userLogin(user: User): Observable<any> {
        return this.http.post('https://firsthsedipl.herokuapp.com/user/login', user);
    }

    adminLogin(user: User): Observable<any> {
        return this.http.post('https://firsthsedipl.herokuapp.com/admin/login', user);
    }

    userPassLogin(user: User): Observable<any> {
        return this.http.post('https://firsthsedipl.herokuapp.com/user/loginpass', user);
    }

    adminPassLogin(user: User): Observable<any> {
        return this.http.post('https://firsthsedipl.herokuapp.com/admin/loginpass', user);
    }

    // category

    getCategoryRoute(route: any): Observable<any> {
        this.number = Number(route);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/categoryroute', this.number);
    }

    getCategory(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/category');
    }

    // product

    getProductParent(id: string): Observable<any> {
        this.number = Number(id);
        const body = { parentId: this.number };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/{parent}', body);
    }

    getProductTitle(title: string): Observable<any> {
        const body = { productTitle: title };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/{title}', body);
    }

    getProductArticle(article: string): Observable<any> {
        this.number = Number(article);
        const body = { productArticle: this.number };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/{article}', body);
    }

    getProductId(productId: number): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/getproductid', productId);
    }

    getProducts(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/getproducts');
    }

    // profile

    getProfile(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/profile', null, { headers: myheader }); // change
    }

    getOrders(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/getorders', { headers: myheader });
    }
    getAOrders(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/getaorders', { headers: myheader });
    }

    updProfilePersonal(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/updprofilepinfo', change, { headers: myheader });
    }

    updPhone(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/updphone', change, { headers: myheader });
    }

    updPass(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/updpass', change, { headers: myheader });
    }

    delProfile(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/delprofile', { headers: myheader });
    }


    // cart

    getCart(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/cart', { headers: myheader });
    }

    addToCart(auth: string, prodId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/addcart', prodId, { headers: myheader });
    }

    updateCart(auth: string, cartid: number, qty: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/updcart' + '?cartid=' + cartid + '&quantity=' + qty, { headers: myheader });
    }

    deleteCart(auth: string, cartid: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/delcart', cartid, { headers: myheader });
    }

    getCartId(auth: string, cartid: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/cartid', cartid, { headers: myheader });
    }

    // wishlist

    getWishlist(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/wishlist', { headers: myheader });
    }

    addToWishlist(auth: string, productId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/addwishlist', productId, { headers: myheader });
    }

    delFromWishlist(auth: string, wishlistId: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/delwishlist', wishlistId, { headers: myheader });
    }


    // auth

    getEmitter() {
        return this.loggedIn;
    }

    // public isAuthenticated(): boolean {
    //     return this.getToken() !== null;
    // }

    storeToken(token: string, auth_type: string) {
        localStorage.setItem('auth_token', token);
        localStorage.setItem('auth_type', auth_type);
        this.loggedIn.emit(auth_type);
    }

    // testEmit(auth_type: string) {
    //     this.loggedIn.emit(auth_type);
    // }

    // getToken() {
    //     return this.storage.get('auth_token');
    // }

    checkToken(auth: string) {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/checktoken', { headers: myheader });
    }

    // getAuthType(): string {
    //     if (this.storage.get('auth_type') !== null) {
    //         return this.storage.get('auth_type');
    //     }
    //     return null;
    // }

    // removeToken() {
    //     this.storage.remove("auth_type");
    //     return this.storage.remove("auth_token");
    // }


    // promo

    // getPromo(): Observable<any> {
    //     return this.http.get<any>('https://firsthsedipl.herokuapp.com/getpromo');
    // }
    getPromoId(promoId: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/getpromoid', promoId);
    }

    getPromo(title: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/getpromo', title);
    }

    addPromo(auth: any, promoId: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/addpromo', promoId, { headers: myheader });
    }
    delPromo(auth: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/delpromo', { headers: myheader });
    }


    // payment

    // chargeCard(token?: any, amount?: any, email?: any) {
    //     const body = { userEmail: email, userAmount: amount, token };
    //     return this.http.post<any>('https://firsthsedipl.herokuapp.com/payment', body);
    // }
    chargeCard(token: any, amount: any, uuid: any, phone: any) {
        const body = { uuid: uuid[0], userAmount: amount[0], token, phone: phone };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/payment', body);
    }

    // order

    // makeOrder(auth: any, comment: any) {
    //     const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
    //     return this.http.post<any>('https://firsthsedipl.herokuapp.com/makeorder', comment, { headers: myheader });
    // }
    makeOrder(auth: any, address: any, paymentId: any) {
        const body = { paymentId: paymentId };
        address = Object.assign(address, body);
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const phone = address.phone.replace('+', '%2B');
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/makeorder' + '?phone=' + phone, address, { headers: myheader });
    }



    // test
    loginAnon(): Observable<any> {
        return this.http.get('https://firsthsedipl.herokuapp.com/user/loginanon');
    }



    // order
    orderStatus(order: any) {
        order = Number(order);
        return this.http.post('https://firsthsedipl.herokuapp.com/orderstatus', order);
    }

    // panel
    getAllOrders(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/getallorders');
    }
    getAllAOrders(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/getallaorders');
    }

    deleteOrder(orderId: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/deleteorder', orderId);
    }

    delUnusedCarts(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/deletecarts');
    }

    delUnusedWish(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/deletewish');
    }

    updStatus(orderId: any, status: any, phone: any): Observable<any> {
        const body = { orderId: orderId, orderStatus: status };
        console.log(phone);
        const phones = phone.replace('+', '%2B');
        console.log(phones);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/updstatus' + '?phone=' + phones, body);
    }



    // tests

    setBlind(auth: any, blindFont: any, lineHeight: any, colorSite: any, backSite: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const body = { fontsize: blindFont, lineheight: lineHeight, colorsite: colorSite, backsite: backSite };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/blindfont', body, { headers: myheader });
    }
    getBlind(auth: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/getblind', { headers: myheader });
    }



    getBio(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/getbio');
    }

    bioChange(auth: any, address: any, order: any): Observable<any> {
        console.log(auth);
        console.log(address);
        console.log(order);
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        let ordere = { orderId: order };
        let obj = Object.assign(address, ordere);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/biochange', obj, { headers: myheader });
    }


    cancelOrder(auth: any, order: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/cancelord', order, { headers: myheader });
    }

    requestCancel(auth: any, orderId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/reqorder', orderId, { headers: myheader });
    }

    // getCancOrders(): Observable<any> {
    //     return this.http.get<any>('https://firsthsedipl.herokuapp.com/getcancorders');
    // }



    addEmail(auth: any, email: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/addemail', email, { headers: myheader });
    }

    confDecide(auth: any, orderId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/confdecide', orderId, { headers: myheader });
    }

    rejectionRequest(orderId: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/rejectionreq', orderId);
    }

    confirmRequest(order: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/confirmreq', order);
    }


    allUsers(): Observable<any> {
        return this.http.get<any>('https://firsthsedipl.herokuapp.com/arrusers');
    }

    findUser(userid: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/finduser', userid);
    }

    blockUser(userid: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/blockuser', userid);
    }

    unBlockUser(userid: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/unblockuser', userid);
    }

    editCategory(catId: any, str: any, title: any): Observable<any> {
        const body = { categoryId: catId, categoryTitle: title, categoryImg: str };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/editcat', body);
    }

    editProd(prodId: any, str: any, prod: any): Observable<any> {
        const body = { productId: prodId, productImg: str, productArticle: prod.productArticle, productPrice: prod.productPrice, productQuantity: prod.productQuantity, productTitle: prod.productTitle };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/editprod', body);
    }

    delProd(prodId: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/delprod', prodId);
    }

    delCat(catId: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/delcat', catId);
    }
    addCat(str: any, title: any): Observable<any> {
        const body = { categoryTitle: title, categoryImg: str };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/addcat', body);
    }
    addProd(catId: any, str: any, prod: any): Observable<any> {
        const body = { parentId: catId, productQuantity: prod.productQuantity, productImg: str, productArticle: prod.productArticle, productPrice: prod.productPrice, productTitle: prod.productTitle };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/addprod', body);
    }

    getReviews(prodId: any): Observable<any> {
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/getreviews', prodId);
    }

    sendReview(auth: any, ord: any, review: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const body = { productId: Number(ord.productId), reviewText: review.reviewText, rating: Number(review.rating) };
        return this.http.post<any>('https://firsthsedipl.herokuapp.com/sendreview' + '?orderid=' + ord.orderId, body, { headers: myheader });
    }
}

