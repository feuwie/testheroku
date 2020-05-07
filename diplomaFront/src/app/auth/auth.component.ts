import { RtlService } from './../rtl.service';
import { AuthStatusService } from './../profile/authstatus.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ApiService } from '../services/api.service';

@Component({
    selector: 'app-auth',
    templateUrl: './auth.component.html',
    styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
    playerName: string;
    tester: any;
    lover: any;
    resend: any;


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
    onOtpChange(otp) {
        this.otp = otp;
        if (otp.length == 4) {
            this.send();
        }
    }

    mail: any = false;

    sms: any;
    pass: any;

    confNewPass: any;


    timeLeft: number = 59;
    interval;
    vis: any = false;

    resp: any;

    buttonsOn: any;
    newPass: any;

    public registerForm: any;
    public passForm: any;
    public changePass: any;
    constructor(public api: ApiService,
        public router: Router,
        public formBuilder: FormBuilder,
        public status: AuthStatusService,
        public rtl: RtlService) {
        this.createForm();
    }

    ngOnInit() {
    }
    createForm() {
        this.registerForm = this.formBuilder.group({
            phone: '',
            usertype: 'customer'
        });
        this.passForm = this.formBuilder.group({
            phone: '',
            password: '',
            usertype: 'customer'
        });
        this.changePass = this.formBuilder.group({
            password: '',
            reppassword: '',
        });
    }

    changePasses() {
        this.api.register(this.registerForm.value, this.changePass.value).subscribe(res => {
            this.api.userLogin(this.registerForm.value).subscribe(auths => {
                localStorage.clear();
                this.api.storeToken(auths.auth_TOKEN, this.registerForm.value.usertype);
                this.router.navigate(['']);
            });
        });
    }

    send() {
        this.tester = false;
        this.api.phoneCheck(this.lover, this.otp, this.registerForm.value.phone).subscribe(res => {
            if (res.object == 'approved') {
                this.api.userLogin(this.registerForm.value).subscribe(auth => {
                    if (auth.status == '200') {
                        localStorage.clear();
                        this.api.storeToken(auth.auth_TOKEN, "customer");
                        this.router.navigate(['']);
                    } else if (auth.message == 'ERROR') {
                        this.api.adminLogin(this.registerForm.value).subscribe(rez => {
                            if (rez.status == "200") {
                                localStorage.clear();
                                this.api.storeToken(rez.auth_TOKEN, "admin");
                                this.router.navigate(['']);
                            } else {
                                this.confNewPass = true;
                                this.tester = false;
                                this.sms = false;
                                this.pass = false;
                            }
                        });
                    } else {
                        this.resp = auth.message;
                    }
                });
            } else {
                console.log('Details cannot be empty');
            }
        });
    }

    register(): void {
        this.timer();
        this.tester = true;
        // this.sms = false;
        // this.confNewPass = true;
        this.api.phoneReg(this.registerForm.value).subscribe(auth => {
            console.log(auth);
            if (auth.status == '200') {
                this.lover = auth.object;
            }
        });
    }


    logPass() {
        this.passForm.value.phone = this.registerForm.value.phone;
        this.api.userPassLogin(this.passForm.value).subscribe(auth => {
            if (auth.status == '200') {
                localStorage.clear(); // check
                this.api.storeToken(auth.auth_TOKEN, "customer");
                this.api.getBlind(auth.auth_TOKEN).subscribe(res => {
                    if (res.object != null) {
                        this.rtl.fontSize = res.object.fontsize;
                    }
                });
                this.router.navigate(['']);
            } else if (auth.message == 'ERROR') {
                this.api.adminPassLogin(this.passForm.value).subscribe(rez => {
                    if (rez.status == "200") {
                        this.api.storeToken(rez.auth_TOKEN, "admin");
                        this.router.navigate(['']);
                    } else {
                        this.router.navigate(['']);
                    }
                });
            } else {
                this.resp = auth.message;
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
}
