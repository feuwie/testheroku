import { ApiService } from 'src/app/services/api.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {


    receivedOrders: any = [];
    receivedOrdersFinal: any = [];
    receivedAOrders: any = [];
    receivedAOrdersFinal: any = [];

    receivedInfo: any = [];

    addressForm: any;

    Status: any = ['Получен', 'Сборка', 'В пути', 'Доставлен'];

    stat = {
        now: null
    };


    status: any;



    constructor(public api: ApiService) {
    }

    ngOnInit() {
        this.getOrders();
    }

    obj: any;
    obje: any;

    getOrders() {
        this.receivedOrdersFinal = [];
        this.receivedOrdersFinal = [];
        this.receivedOrders = [];
        this.receivedAOrders = [];
        this.api.getAllOrders().subscribe(res => {
            this.api.getAllAOrders().subscribe(rez => {
                this.api.getProducts().subscribe(red => {
                    this.api.getBio().subscribe(ref => {
                        for (let order of res.object) {
                            if (order.orderStatus == 'Доставлен') {
                                for (let or of ref.object) {
                                    if (or.orderId == order.orderId) {
                                        let address = or.address + ', кв. ' + or.flat + ', подъезд ' + or.door + ', этаж ' + or.floor + ', домофон ' + or.doorphone + ', индекс ' + or.ind;
                                        let va = { address: address, phone: or.phone, email: or.email, fullname: or.fullname };
                                        this.obje = Object.assign(order, va);
                                        this.receivedOrdersFinal.push(this.obje);
                                    }
                                }
                                for (let multi of rez.object) {
                                    if (multi.orderId == order.orderId) {
                                        for (let lol of red.object) {
                                            if (lol.productId == multi.productId) {
                                                let va = { productTitle: lol.productTitle, productImg: lol.productImg };
                                                this.obj = Object.assign(multi, va);
                                                this.receivedAOrdersFinal.push(this.obj);
                                            }
                                        }
                                    }
                                }
                                // this.receivedAOrdersFinal.push(rez.object.find(({ orderId }) => orderId == order.orderId));
                            } else {
                                for (let or of ref.object) {
                                    if (or.orderId == order.orderId) {
                                        let address = or.address + ', кв. ' + or.flat + ', подъезд ' + or.door + ', этаж ' + or.floor + ', домофон ' + or.doorphone + ', индекс ' + or.ind;
                                        let va = { address: address, phone: or.phone, email: or.email, fullname: or.fullname };
                                        this.obje = Object.assign(order, va);
                                        this.receivedOrders.push(this.obje);
                                    }
                                }
                                for (let multi of rez.object) {
                                    if (multi.orderId == order.orderId) {
                                        for (let lol of red.object) {
                                            if (lol.productId == multi.productId) {
                                                let va = { productTitle: lol.productTitle, productImg: lol.productImg };
                                                this.obj = Object.assign(multi, va);
                                                this.receivedAOrders.push(this.obj);
                                            }
                                        }
                                    }
                                }
                                // for (let ob of rez.object) {
                                //     // console.log(red.object.find(({ productId }) => productId == ob.productId));
                                //     if ()
                                //     let va = { productImg: red.object.find(({ productId }) => productId == ob.productId).productImg };
                                //     // console.log(va);
                                //     console.log(Object.assign(rez.object.find(({ orderId }) => orderId == order.orderId), va));
                                //     this.receivedAOrders.push(Object.assign(rez.object.find(({ orderId }) => orderId == order.orderId), va));
                                // }
                            }
                        }
                    });
                });
            });
        });
    }



    decideYes(order: any) {
        this.api.confirmRequest(order).subscribe(res => {
            this.getOrders();
        });
    }

    decideNo(order: any) {
        this.api.rejectionRequest(order.orderId).subscribe(res => {
            this.getOrders();
        });
    }

    del(orderId: any) {
        this.api.deleteOrder(orderId).subscribe(res => {
            this.receivedOrdersFinal = this.receivedOrdersFinal.filter(orderId => orderId !== orderId);
            this.receivedAOrdersFinal = this.receivedAOrdersFinal.filter(orderId => orderId !== orderId);
        });
    }

    confStatus(order: any, phone: any) {
        if (order.orderStatus == this.stat.now) {
            console.log('Невозможно применить действие!');
            return order.isActive = false;
        } else {
            this.api.updStatus(order.orderId, this.stat.now, phone).subscribe(res => {
                this.stat.now = null;
                this.getOrders();
                return order.isActive = false;
            });
        }
    }

}
