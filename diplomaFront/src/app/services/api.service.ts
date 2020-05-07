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

    constructor(private http: HttpClient) { }


    emailReg(email: any): Observable<any> {
        return this.http.post('/verifyemail', email);
    }

    emailCheck(code: String, email: any): Observable<any> {
        // email = email.replace('+', '%2B');
        return this.http.get<any>('/checkemail' + '?code=' + code + '&email=' + email);
    }

    phoneReg(user: User): Observable<any> {
        return this.http.post('/verifyphone', user);
    }
    // phoneCheck(str: String, code: String, phone: any): Observable<any> {
    //     const body = { str: str, code: code, phone: phone };
    //     return this.http.post('/checkphone', body);
    // }
    phoneCheck(str: String, code: String, phone: any): Observable<any> {
        phone = phone.replace('+', '%2B');
        return this.http.get<any>('/checkphone' + '?str=' + str + '&code=' + code + '&phone=' + phone);
    }

    // auth

    // check(user: User): Observable<any> {
    //     return this.http.post('/user/check', user);
    // }

    register(user: User, pass: any): Observable<any> {
        user.password = pass.password;
        return this.http.post('/user/registration', user);
    }

    userLogin(user: User): Observable<any> {
        return this.http.post('/user/login', user);
    }

    adminLogin(user: User): Observable<any> {
        return this.http.post('/admin/login', user);
    }

    userPassLogin(user: User): Observable<any> {
        return this.http.post('/user/loginpass', user);
    }

    adminPassLogin(user: User): Observable<any> {
        return this.http.post('/admin/loginpass', user);
    }

    // category

    getCategoryRoute(route: any): Observable<any> {
        this.number = Number(route);
        return this.http.post<any>('/categoryroute', this.number);
    }

    getCategory(): Observable<any> {
        return this.http.get<any>('/category');
    }

    // product

    getProductParent(id: string): Observable<any> {
        this.number = Number(id);
        const body = { parentId: this.number };
        return this.http.post<any>('/{parent}', body);
    }

    getProductTitle(title: string): Observable<any> {
        const body = { productTitle: title };
        return this.http.post<any>('/{title}', body);
    }

    getProductArticle(article: string): Observable<any> {
        this.number = Number(article);
        const body = { productArticle: this.number };
        return this.http.post<any>('/{article}', body);
    }

    getProductId(productId: number): Observable<any> {
        return this.http.post<any>('/getproductid', productId);
    }

    getProducts(): Observable<any> {
        return this.http.get<any>('/getproducts');
    }

    // profile

    getProfile(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/profile', null, { headers: myheader }); // change
    }

    getOrders(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/getorders', { headers: myheader });
    }
    getAOrders(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/getaorders', { headers: myheader });
    }

    updProfilePersonal(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/updprofilepinfo', change, { headers: myheader });
    }

    updPhone(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/updphone', change, { headers: myheader });
    }

    updPass(auth: string, change: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/updpass', change, { headers: myheader });
    }

    delProfile(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/delprofile', { headers: myheader });
    }


    // cart

    getCart(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/cart', { headers: myheader });
    }

    addToCart(auth: string, prodId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/addcart', prodId, { headers: myheader });
    }

    updateCart(auth: string, cartid: number, qty: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/updcart' + '?cartid=' + cartid + '&quantity=' + qty, { headers: myheader });
    }

    deleteCart(auth: string, cartid: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/delcart', cartid, { headers: myheader });
    }

    getCartId(auth: string, cartid: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/cartid', cartid, { headers: myheader });
    }

    // wishlist

    getWishlist(auth: string): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/wishlist', { headers: myheader });
    }

    addToWishlist(auth: string, productId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/addwishlist', productId, { headers: myheader });
    }

    delFromWishlist(auth: string, wishlistId: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/delwishlist', wishlistId, { headers: myheader });
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
        return this.http.get<any>('/checktoken', { headers: myheader });
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
    //     return this.http.get<any>('/getpromo');
    // }
    getPromoId(promoId: any): Observable<any> {
        return this.http.post<any>('/getpromoid', promoId);
    }

    getPromo(title: any): Observable<any> {
        return this.http.post<any>('/getpromo', title);
    }

    addPromo(auth: any, promoId: number): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/addpromo', promoId, { headers: myheader });
    }
    delPromo(auth: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/delpromo', { headers: myheader });
    }


    // payment

    // chargeCard(token?: any, amount?: any, email?: any) {
    //     const body = { userEmail: email, userAmount: amount, token };
    //     return this.http.post<any>('/payment', body);
    // }
    chargeCard(token: any, amount: any, uuid: any, phone: any) {
        const body = { uuid: uuid[0], userAmount: amount[0], token, phone: phone };
        return this.http.post<any>('/payment', body);
    }

    // order

    // makeOrder(auth: any, comment: any) {
    //     const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
    //     return this.http.post<any>('/makeorder', comment, { headers: myheader });
    // }
    makeOrder(auth: any, address: any, paymentId: any) {
        const body = { paymentId: paymentId };
        address = Object.assign(address, body);
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const phone = address.phone.replace('+', '%2B');
        return this.http.post<any>('/makeorder' + '?phone=' + phone, address, { headers: myheader });
    }



    // test
    loginAnon(): Observable<any> {
        return this.http.get('/user/loginanon');
    }



    // order
    orderStatus(order: any) {
        order = Number(order);
        return this.http.post('/orderstatus', order);
    }

    // panel
    getAllOrders(): Observable<any> {
        return this.http.get<any>('/getallorders');
    }
    getAllAOrders(): Observable<any> {
        return this.http.get<any>('/getallaorders');
    }

    deleteOrder(orderId: any): Observable<any> {
        return this.http.post<any>('/deleteorder', orderId);
    }

    delUnusedCarts(): Observable<any> {
        return this.http.get<any>('/deletecarts');
    }

    delUnusedWish(): Observable<any> {
        return this.http.get<any>('/deletewish');
    }

    updStatus(orderId: any, status: any, phone: any): Observable<any> {
        const body = { orderId: orderId, orderStatus: status };
        console.log(phone);
        const phones = phone.replace('+', '%2B');
        console.log(phones);
        return this.http.post<any>('/updstatus' + '?phone=' + phones, body);
    }



    // tests

    setBlind(auth: any, blindFont: any, lineHeight: any, colorSite: any, backSite: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const body = { fontsize: blindFont, lineheight: lineHeight, colorsite: colorSite, backsite: backSite };
        return this.http.post<any>('/blindfont', body, { headers: myheader });
    }
    getBlind(auth: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.get<any>('/getblind', { headers: myheader });
    }



    getBio(): Observable<any> {
        return this.http.get<any>('/getbio');
    }

    bioChange(auth: any, address: any, order: any): Observable<any> {
        console.log(auth);
        console.log(address);
        console.log(order);
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        let ordere = { orderId: order };
        let obj = Object.assign(address, ordere);
        return this.http.post<any>('/biochange', obj, { headers: myheader });
    }


    cancelOrder(auth: any, order: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/cancelord', order, { headers: myheader });
    }

    requestCancel(auth: any, orderId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/reqorder', orderId, { headers: myheader });
    }

    // getCancOrders(): Observable<any> {
    //     return this.http.get<any>('/getcancorders');
    // }



    addEmail(auth: any, email: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/addemail', email, { headers: myheader });
    }

    confDecide(auth: any, orderId: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        return this.http.post<any>('/confdecide', orderId, { headers: myheader });
    }

    rejectionRequest(orderId: any): Observable<any> {
        return this.http.post<any>('/rejectionreq', orderId);
    }

    confirmRequest(order: any): Observable<any> {
        return this.http.post<any>('/confirmreq', order);
    }


    allUsers(): Observable<any> {
        return this.http.get<any>('/arrusers');
    }

    findUser(userid: any): Observable<any> {
        return this.http.post<any>('/finduser', userid);
    }

    blockUser(userid: any): Observable<any> {
        return this.http.post<any>('/blockuser', userid);
    }

    unBlockUser(userid: any): Observable<any> {
        return this.http.post<any>('/unblockuser', userid);
    }

    editCategory(catId: any, str: any, title: any): Observable<any> {
        const body = { categoryId: catId, categoryTitle: title, categoryImg: str };
        return this.http.post<any>('/editcat', body);
    }

    editProd(prodId: any, str: any, prod: any): Observable<any> {
        const body = { productId: prodId, productImg: str, productArticle: prod.productArticle, productPrice: prod.productPrice, productQuantity: prod.productQuantity, productTitle: prod.productTitle };
        return this.http.post<any>('/editprod', body);
    }

    delProd(prodId: any): Observable<any> {
        return this.http.post<any>('/delprod', prodId);
    }

    delCat(catId: any): Observable<any> {
        return this.http.post<any>('/delcat', catId);
    }
    addCat(str: any, title: any): Observable<any> {
        const body = { categoryTitle: title, categoryImg: str };
        return this.http.post<any>('/addcat', body);
    }
    addProd(catId: any, str: any, prod: any): Observable<any> {
        const body = { parentId: catId, productQuantity: prod.productQuantity, productImg: str, productArticle: prod.productArticle, productPrice: prod.productPrice, productTitle: prod.productTitle };
        return this.http.post<any>('/addprod', body);
    }

    getReviews(prodId: any): Observable<any> {
        return this.http.post<any>('/getreviews', prodId);
    }

    sendReview(auth: any, ord: any, review: any): Observable<any> {
        const myheader = new HttpHeaders().set('AUTH_TOKEN', auth);
        const body = { productId: Number(ord.productId), reviewText: review.reviewText, rating: Number(review.rating) };
        return this.http.post<any>('/sendreview' + '?orderid=' + ord.orderId, body, { headers: myheader });
    }
}

