import { ApiService } from 'src/app/services/api.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthStatusService } from '../profile/authstatus.service';

@Component({
    selector: 'app-panel',
    templateUrl: './panel.component.html',
    styleUrls: ['./panel.component.css']
})
export class PanelComponent implements OnInit {

    receivedUsers: any;
    findedUser: any;
    userNumber: any;
    userOrder: any;

    prod: any;
    findedProd: any;

    receivedOrders: any = [];
    receivedAOrders: any = [];
    obj: any;

    product: any = {
        productArticle: null,
        productPrice: null,
        productQuantity: null,
        productTitle: null,
    };


    stat: any;


    receivedCategory: any;
    // files: any[];
    files: any;
    receivedProducts: any;
    // categ: any = {
    //     title: null,
    // };

    categ: any;

    disable: any = false;


    sendUser: any;
    roleUser: any;
    allUsers: any;
    sendProd: any;
    editOProd: any;
    edProd: any;
    plusProd: any;
    editedProd: any;
    plusCat: any;
    editCat: any;

    constructor(public api: ApiService, public http: HttpClient, public status: AuthStatusService) { }

    ngOnInit() {
    }

    delUnusedCarts() {
        this.api.delUnusedCarts().subscribe(res => {
            console.log(res);
        });
    }
    delUnusedWish() {
        this.api.delUnusedWish().subscribe(res => {
            console.log(res);
        });
    }

    listUsers() {
        this.receivedUsers = [];
        this.api.allUsers().subscribe(res => {
            this.receivedUsers = res.object;
        });
    }

    findUser(userid: any) {
        this.receivedOrders = [];
        const num = Number(userid);
        this.api.findUser(num).subscribe(res => {
            this.findedUser = res.object;
        });
    }

    findProd(prodid: any) {
        const num = Number(prodid);
        this.api.getProductId(num).subscribe(res => {
            console.log(res.object);
            this.findedProd = res.object;
        });
    }

    userOrders() {
        this.api.getOrders(this.findedUser.uuid).subscribe(res => {
            this.api.getAOrders(this.findedUser.uuid).subscribe(rez => {
                this.api.getProducts().subscribe(red => {
                    for (const order of res.object) {
                        this.receivedOrders.push(order);
                        for (const multi of rez.object) {
                            if (multi.orderId == order.orderId) {
                                for (const lol of red.object) {
                                    if (lol.productId == multi.productId) {
                                        const va = { productTitle: lol.productTitle, productImg: lol.productImg };
                                        this.obj = Object.assign(multi, va);
                                        this.receivedAOrders.push(this.obj);
                                    }
                                }
                            }
                        }
                    }
                });
            });
        });
    }


    block(userid: any) {
        this.status.isChanged = true;
        this.api.blockUser(userid).subscribe(res => {
            console.log(res);
            console.log('blocked!!!');
            this.listUsers();
            this.findUser(userid);
        });
    }

    unblock(userid: any) {
        this.api.unBlockUser(userid).subscribe(res => {
            console.log(res);
            console.log('unblocked!!!');
            this.listUsers();
            this.findUser(userid);
        });
    }

    getProducts() {
        this.receivedProducts = [];
        this.api.getProducts().subscribe(res => {
            console.log(res.object);
            this.receivedProducts = res.object;
        });
    }

    getCategory() {
        this.receivedCategory = [];
        this.api.getCategory().subscribe(res => {
            console.log(res.object);
            this.receivedCategory = res.object;
        });
    }

    editCateg(cat: any) {
        this.api.editCategory(cat.categoryId, this.files, this.categ).subscribe(res => {
            this.getCategory();
        });
    }

    editProd(prod: any) {
        this.api.editProd(prod.productId, this.files, this.product).subscribe(res => {
            console.log(res);
            this.product = null;
            this.getProducts();
            this.findProd(prod.productId);
        });
    }

    onFileChange(event) {
        this.files = '../../assets/' + event.target.files[0].name;
    }

    delUser(user: any) {
        this.api.delProfile(user.uuid).subscribe(res => {
            this.listUsers();
        });
    }

    delProd(prod: any) {
        this.api.delProd(prod.productId).subscribe(res => {
            this.getProducts();
        });
    }

    delCat(cat: any) {
        this.api.delCat(cat.categoryId).subscribe(res => {
            this.getCategory();
        });
    }

    addCat() {
        this.api.addCat(this.files, this.categ).subscribe(res => {
            this.getCategory();
        });
    }

    addProd() {
        this.api.addProd(this.stat.categoryId, this.files, this.product).subscribe(res => {
            this.getProducts();
        });
    }
}
