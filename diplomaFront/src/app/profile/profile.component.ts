import { SESSION_STORAGE } from 'angular-webstorage-service';
import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';
import { FormBuilder } from '@angular/forms';
import { formatDate } from '@angular/common';
import { User } from '../model/user';
import { AuthStatusService } from './authstatus.service';
import { map } from 'rxjs/operators';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {


    receivedOrders: any = [];
    receivedAOrders: any = [];
    receivedOrdersFinal: any = [];
    receivedAOrdersFinal: any = [];

    isOrder: any = false;
    isInfo: any = true;

    auth: string;
    receivedUser: any;
    receivedBio: any;

    bio: any;


    phones: boolean;

    // change: any = {
    //     fullname: null,
    //     dob: null,
    //     gender: this.receivedUser.gender
    // };


    timeLeft: number = 59;
    interval;
    vis: any = false;


    val: any = false;
    ork: any;

    tester: any;
    lover: any;

    otp: string;

    config = {
        allowNumbersOnly: false,
        length: 4,
        isPasswordInput: false,
        disableAutoFocus: false,
        placeholder: '',
        inputStyles: {
            'width': '50px',
            'height': '50px'
        }
    };
    change = {};


    arr: any;
    groups: any;

    countere: any = 0;
    elover = [];

    confirm: any;
    confotp: any;

    body = {};

    confirmPass: any;
    confirmPassOtp: any;
    newPass: any;

    confirmEmail: any;
    emailtester: any;

    private changePhone: any;
    private changePass: any;
    private changeEmail: any;

    constructor(private api: ApiService, private router: Router, private formBuilder: FormBuilder, private status: AuthStatusService, private cdr: ChangeDetectorRef) { }

    ngOnInit() {
        this.auth = this.status.setAuth();
        this.getProfile();
        // this.getBio();
        this.getOrders();
        this.createForm();
    }

    obj: any;

    getOrders() {
        this.receivedOrders = [];
        this.receivedAOrders = [];
        this.receivedOrdersFinal = [];
        this.receivedAOrdersFinal = [];
        this.api.getOrders(this.auth).subscribe(res => {
            this.api.getAOrders(this.auth).subscribe(rez => {
                this.api.getProducts().subscribe(red => {
                    console.log(red);
                    for (let order of res.object) {
                        if (order.orderStatus == 'Доставлен') {
                            this.receivedOrdersFinal.push(order);
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
                        } else {
                            this.receivedOrders.push(order);
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
                        }
                    }
                    // for (let order of res.object) {
                    //     if (order.orderStatus == 'Доставлен') {
                    //         this.receivedOrdersFinal.push(order);
                    //         for (let multi of rez.object) {
                    //             if (multi.orderId == order.orderId) {
                    //                 this.receivedAOrdersFinal.push(multi);
                    //             }
                    //         }
                    //     } else {
                    //         this.receivedOrders.push(order);
                    //         for (let multi of rez.object) {
                    //             if (multi.orderId == order.orderId) {
                    //                 this.receivedAOrders.push(multi);
                    //             }
                    //         }
                    //     }
                    // }
                });
            });
        });
    }

    getProfile() {
        this.api.getProfile(this.auth).subscribe(res => {
            this.receivedUser = res.object;
            this.change = {
                fullname: (res.object.fullname != null) ? res.object.fullname : '',
                dob: (res.object.dob != null) ? formatDate(res.object.dob, 'yyyy-MM-dd', 'en_US') : '',
                gender: (res.object.gender != null) ? res.object.gender : '',
            };
        }, err => {
            console.log(err);
        });
    }

    // getBio() {
    //     this.api.getBio(this.auth).subscribe(res => {
    //         this.receivedBio = res.object;
    //         if (res.object != null) {
    //             this.change = {
    //                 fullname: this.receivedBio.fullname,
    //                 dob: formatDate(this.receivedBio.dob, 'yyyy-MM-dd', 'en_US'),
    //                 gender: this.receivedBio.gender,
    //                 address: this.receivedBio.address,
    //                 door: this.receivedBio.door,
    //                 doorphone: this.receivedBio.doorphone,
    //                 flat: this.receivedBio.flat,
    //                 floor: this.receivedBio.floor,
    //                 ind: this.receivedBio.ind,
    //             }
    //         }
    //     });
    // }

    createForm() {
        this.changePhone = this.formBuilder.group({
            phone: '',
            usertype: 'customer'
        });
        this.changePass = this.formBuilder.group({
            password: '',
            reppassword: '',
        });
        this.changeEmail = this.formBuilder.group({
            email: '',
        });
    }

    onOtpChange(otp) {
        this.otp = otp;
        if (otp.length == 4) {
            this.send();
        }
    }
    confOnOtpChange(otp) {
        this.otp = otp;
        if (otp.length == 4) {
            this.sendConfirm();
        }
    }
    confPassOnOtpChange(otp) {
        this.otp = otp;
        if (otp.length == 4) {
            this.sendConfirmPass();
        }
    }

    confEmailOnOtpChange(otp) {
        this.otp = otp;
        if (otp.length == 4) {
            this.sendConfirmEmail();
        }
    }

    send() {
        this.api.phoneCheck(this.lover, this.otp, this.changePhone.value.phone).subscribe(res => {
            if (res.object == 'approved') {
                this.api.updPhone(this.auth, this.changePhone.value).subscribe(auth => {
                    localStorage.clear();
                    this.api.storeToken(auth.auth_TOKEN, "customer");
                    this.router.navigate(['']);
                });
            } else {
                console.log('Details cannot be empty');
            }
        });
    }

    sendConfirm() {
        this.api.phoneCheck(this.lover, this.otp, this.receivedUser.phone).subscribe(res => {
            if (res.object == 'approved') {
                this.phones = true;
                this.confotp = false;
            } else {
                console.log('Details cannot be empty');
            }
        });
    }

    sendConfirmPass() {
        this.api.phoneCheck(this.lover, this.otp, this.receivedUser.phone).subscribe(res => {
            if (res.object == 'approved') {
                this.confirmPassOtp = false;
                this.newPass = true;
            } else {
                console.log('Details cannot be empty');
            }
        });
    }

    sendConfirmEmail() {
        this.api.emailCheck(this.otp, this.changeEmail.value.email).subscribe(res => {
            if (res.object == 'approved') {
                this.api.addEmail(this.auth, this.changeEmail.value.email).subscribe(res => {
                    this.getProfile();
                });
                this.emailtester = false;
            } else {
                console.log('Details cannot be empty');
            }
        });
    }

    lol() {
        console.log(this.receivedOrdersFinal);
        console.log(this.receivedAOrdersFinal);
    }

    lole(lol: any) {
        console.log(this.reviewed);
    }


    review: any;
    reviewed: any = {
        rating: null,
        reviewText: null,
    };
    prodId: any;
    prodTitle: any;
    aord: any;

    makeReview(aorder: any) {
        this.aord = aorder;
        this.prodTitle = aorder.productTitle;
        this.review = true;
    }

    sendReview() {
        this.api.sendReview(this.auth, this.aord, this.reviewed).subscribe(res => {
            this.getOrders();
        });
    }

    changePasses() {
        this.status.isChanged = true;
        this.api.updPass(this.auth, this.changePass.value).subscribe(res => {
            localStorage.clear();
            this.api.storeToken(res.auth_TOKEN, "customer");
            this.router.navigate(['']);
            // this.confirmPass = false;
            // this.newPass = false;
            // this.getProfile();
        });
    }

    changePhones() {
        this.timer();
        this.phones = false;
        if (this.changePhone.value.phone != this.receivedUser.phone) {
            this.tester = true;
            this.api.phoneReg(this.changePhone.value).subscribe(auth => {
                if (auth.status == '200') {
                    this.lover = auth.object;
                }
            });
        } else {
            this.changePhone.reset();
            console.log('error');
        }
    }

    changeEmails() {
        this.timer();
        this.confirmEmail = false;
        if (this.changeEmail.value.email != this.receivedUser.email) {
            this.emailtester = true;
            this.api.emailReg(this.changeEmail.value.email).subscribe(auth => {
                if (auth.status == '200') {
                    this.lover = auth.object;
                }
            });
        } else {
            this.changeEmail.reset();
            console.log('error');
        }
    }

    confirmPhone() {
        this.timer();
        this.confirm = false;
        this.confotp = true;
        this.body = { phone: this.receivedUser.phone, usertype: 'customer' };
        this.api.phoneReg(this.body).subscribe(auth => {
            if (auth.status == '200') {
                this.lover = auth.object;
            }
        });
    }

    confirmPassword() {
        this.timer();
        this.confirmPass = false;
        this.confirmPassOtp = true;
        this.body = { phone: this.receivedUser.phone, usertype: 'customer' };
        this.api.phoneReg(this.body).subscribe(auth => {
            if (auth.status == '200') {
                this.lover = auth.object;
            }
        });
    }

    timer() {
        this.interval = setInterval(() => {
            if (this.timeLeft > 0) {
                this.timeLeft--;
            } else {
                this.timeLeft = 59;
                this.vis = true;
            }
        }, 1000);
    }

    stopTimer() {
        this.timeLeft = 59;
        clearInterval(this.interval);
    }

    add() {
        this.api.updProfilePersonal(this.auth, this.change).subscribe(res => {
            // this.router.navigate(['']);
            // this.getBio();
            this.getProfile();
            this.bio = false;
        }, err => {
            console.log(err);
        });
    }


    canc() {
        this.bio = false;
        this.getProfile();
    }

    delete() {
        this.api.delProfile(this.auth).subscribe(res => {
            if (res.status == '200') {
                localStorage.clear();
                this.router.navigate(['']);
            }
        }, err => {
            console.log(err);
        });
    }



    cancOrd(order: any) {
        this.api.cancelOrder(this.auth, order).subscribe(res => {
            if (res.message == 'SUCCESS') {
                this.getOrders();
            } else if (res.message == 'ERROR_STATUS') {
                this.val = true;
                this.ork = order.orderId;
            }
        });
    }

    sendRequest() {
        this.api.requestCancel(this.auth, this.ork).subscribe(res => {
            console.log('Ваш запрос отправлен!');
            this.getOrders();
        });
    }

    confirmDecide(order: any) {
        this.api.confDecide(this.auth, order.orderId).subscribe(res => {
            this.getOrders();
        });
    }

}
