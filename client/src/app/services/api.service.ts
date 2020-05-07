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
        return this.http.post('http://localhost:8080/verifyemail', email);
    }

    emailCheck(code: String, email: any): Observable<any> {
        // email = email.replace('+', '%2B');
        return this.http.get<any>('http://localhost:8080/checkemail' + '?code=' + code + '&email=' + email);
    }

    phoneReg(user: User): Observable<any> {
        return this.http.post('http://localhost:8080/verifyphone', user);
    }
    // phoneCheck(str: String, code: String, phone: any): Observable<any> {
    //     const body = { str: str, code: code, phone: phone };
    //     return this.http.post('http://localhost:8080/checkphone', body);
    // }
    phoneCheck(str: String, code: String, phone: any): Observable<any> {
        phone = phone.replace('+', '%2B');
        return this.http.get<any>('http://localhost:8080/checkphone' + '?str=' + str + '&code=' + code + '&phone=' + phone);
    }

    // auth

    // check(user: User): Observable<any> {
    //     return this.http.post('http://localhost:8080/user/check', user);
    // }

    register(user: User, pass: any): Observable<any> {
        user.password = pass.password;
        return this.http.post('http://localhost:8080/user/registration', user);
    }

    userLogin(user: User): Observable<any> {
        return this.http.post('http://localhost:8080/user/login', user);
    }

    adminLogin(user: User): Observable<any> {
        return this.http.post('http://localhost:8080/admin/login', user);
    }

    userPassLogin(user: User): Observable<any> {
        return this.http.post('http://localhost:8080/user/loginpass', user);
    }

    adminPassLogin(user: User): Observable<any> {
        return this.http.post('http://localhost:8080/admin/loginpass', user);
    }

    // category

    getCategoryRoute(route: any): Observable<any> {
        this.number = Number(route);
        return this.http.post<any>('http://localhost:8080/categoryroute', this.number);
    }

    getCategory(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/category');
    }

    // product

    getProductParent(id: string): Observable<any> {
        this.number = Number(id);
        const body = { parentId: this.number };
        return this.http.post<any>('http://localhost:8080/{parent}', body);
    }

    getProductTitle(title: string): Observable<any> {
        const body = { productTitle: title };
        return this.http.post<any>('http://localhost:8080/{title}', body);
    }

    getProductArticle(article: string): Observable<any> {
        this.number = Number(article);
        const body = { productArticle: this.number };
        return this.http.post<any>('http://localhost:8080/{article}', body);
    }

    getProductId(productId: number): Observable<any> {
        return this.http.post<any>('http://localhost:8080/getproductid', productId);
    }

    getProducts(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/getproducts');
    }

    // profile

    getProfile(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/profile', null, { headers: myheader }); // change
    }

    getOrders(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/getorders', { headers: myheader });
    }
    getAOrders(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/getaorders', { headers: myheader });
    }

    updProfilePersonal(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/updprofilepinfo', change, { headers: myheader });
    }

    updPhone(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/updphone', change, { headers: myheader });
    }

    updPass(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/updpass', change, { headers: myheader });
    }

    delProfile(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/delprofile', { headers: myheader });
    }


    // cart

    getCart(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/cart', { headers: myheader });
    }

    addToCart(auth: string, prodId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/addcart', prodId, { headers: myheader });
    }

    updateCart(auth: string, cartid: number, qty: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/updcart' + '?cartid=' + cartid + '&quantity=' + qty, { headers: myheader });
    }

    deleteCart(auth: string, cartid: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/delcart', cartid, { headers: myheader });
    }

    getCartId(auth: string, cartid: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/cartid', cartid, { headers: myheader });
    }

    // wishlist

    getWishlist(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/wishlist', { headers: myheader });
    }

    addToWishlist(auth: string, productId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/addwishlist', productId, { headers: myheader });
    }

    delFromWishlist(auth: string, wishlistId: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/delwishlist', wishlistId, { headers: myheader });
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
        return this.http.get<any>('http://localhost:8080/checktoken', { headers: myheader });
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
    //     return this.http.get<any>('http://localhost:8080/getpromo');
    // }
    getPromoId(promoId: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/getpromoid', promoId);
    }

    getPromo(title: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/getpromo', title);
    }

    addPromo(auth: any, promoId: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/addpromo', promoId, { headers: myheader });
    }
    delPromo(auth: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/delpromo', { headers: myheader });
    }


    // payment

    // chargeCard(token?: any, amount?: any, email?: any) {
    //     const body = { userEmail: email, userAmount: amount, token };
    //     return this.http.post<any>('http://localhost:8080/payment', body);
    // }
    chargeCard(token: any, amount: any, uuid: any, phone: any) {
        const body = { uuid: uuid[0], userAmount: amount[0], token, phone: phone };
        return this.http.post<any>('http://localhost:8080/payment', body);
    }

    // order

    // makeOrder(auth: any, comment: any) {
    //     const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
    //     return this.http.post<any>('http://localhost:8080/makeorder', comment, { headers: myheader });
    // }
    makeOrder(auth: any, address: any, paymentId: any) {
        const body = { paymentId: paymentId };
        address = Object.assign(address, body);
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const phone = address.phone.replace('+', '%2B');
        return this.http.post<any>('http://localhost:8080/makeorder' + '?phone=' + phone, address, { headers: myheader });
    }



    // test
    loginAnon(): Observable<any> {
        return this.http.get('http://localhost:8080/user/loginanon');
    }



    // order
    orderStatus(order: any) {
        order = Number(order);
        return this.http.post('http://localhost:8080/orderstatus', order);
    }

    // panel
    getAllOrders(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/getallorders');
    }
    getAllAOrders(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/getallaorders');
    }

    deleteOrder(orderId: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/deleteorder', orderId);
    }

    delUnusedCarts(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/deletecarts');
    }

    delUnusedWish(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/deletewish');
    }

    updStatus(orderId: any, status: any, phone: any): Observable<any> {
        const body = { orderId: orderId, orderStatus: status };
        console.log(phone);
        const phones = phone.replace('+', '%2B');
        console.log(phones);
        return this.http.post<any>('http://localhost:8080/updstatus' + '?phone=' + phones, body);
    }



    // tests

    setBlind(auth: any, blindFont: any, lineHeight: any, colorSite: any, backSite: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const body = { fontsize: blindFont, lineheight: lineHeight, colorsite: colorSite, backsite: backSite };
        return this.http.post<any>('http://localhost:8080/blindfont', body, { headers: myheader });
    }
    getBlind(auth: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('http://localhost:8080/getblind', { headers: myheader });
    }



    getBio(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/getbio');
    }

    bioChange(auth: any, address: any, order: any): Observable<any> {
        console.log(auth);
        console.log(address);
        console.log(order);
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        let ordere = { orderId: order };
        let obj = Object.assign(address, ordere);
        return this.http.post<any>('http://localhost:8080/biochange', obj, { headers: myheader });
    }


    cancelOrder(auth: any, order: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/cancelord', order, { headers: myheader });
    }

    requestCancel(auth: any, orderId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/reqorder', orderId, { headers: myheader });
    }

    // getCancOrders(): Observable<any> {
    //     return this.http.get<any>('http://localhost:8080/getcancorders');
    // }



    addEmail(auth: any, email: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/addemail', email, { headers: myheader });
    }

    confDecide(auth: any, orderId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('http://localhost:8080/confdecide', orderId, { headers: myheader });
    }

    rejectionRequest(orderId: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/rejectionreq', orderId);
    }

    confirmRequest(order: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/confirmreq', order);
    }


    allUsers(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/arrusers');
    }

    findUser(userid: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/finduser', userid);
    }

    blockUser(userid: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/blockuser', userid);
    }

    unBlockUser(userid: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/unblockuser', userid);
    }

    editCategory(catId: any, str: any, title: any): Observable<any> {
        const body = { categoryId: catId, categoryTitle: title, categoryImg: str };
        return this.http.post<any>('http://localhost:8080/editcat', body);
    }

    editProd(prodId: any, str: any, prod: any): Observable<any> {
        const body = { productId: prodId, productImg: str, productArticle: prod.productArticle, productPrice: prod.productPrice, productQuantity: prod.productQuantity, productTitle: prod.productTitle };
        return this.http.post<any>('http://localhost:8080/editprod', body);
    }

    delProd(prodId: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/delprod', prodId);
    }

    delCat(catId: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/delcat', catId);
    }
    addCat(str: any, title: any): Observable<any> {
        const body = { categoryTitle: title, categoryImg: str };
        return this.http.post<any>('http://localhost:8080/addcat', body);
    }
    addProd(catId: any, str: any, prod: any): Observable<any> {
        const body = { parentId: catId, productQuantity: prod.productQuantity, productImg: str, productArticle: prod.productArticle, productPrice: prod.productPrice, productTitle: prod.productTitle };
        return this.http.post<any>('http://localhost:8080/addprod', body);
    }

    getReviews(prodId: any): Observable<any> {
        return this.http.post<any>('http://localhost:8080/getreviews', prodId);
    }

    sendReview(auth: any, ord: any, review: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const body = { productId: Number(ord.productId), reviewText: review.reviewText, rating: Number(review.rating) };
        return this.http.post<any>('http://localhost:8080/sendreview' + '?orderid=' + ord.orderId, body, { headers: myheader });
    }
}

