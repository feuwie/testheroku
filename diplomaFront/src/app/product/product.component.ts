import { AuthStatusService } from './../profile/authstatus.service';
import { Product } from './../model/product';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../services/api.service';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
    @ViewChild('pRef', { static: false }) pRef: ElementRef;
    isAbout = true;
    isSpec = false;
    receivedProduct: Product;
    receivedCart: any;
    receivedWishlist: any;
    receivedReviews: any = [];
    testArray: any[] = [];
    testArrayTwo: any[] = [];
    receivedCategory: any;
    private auth: string;

    obje: any;

    show: any = 4;

    // rating: any;

    constructor(private api: ApiService, private route: ActivatedRoute, private router: Router, private status: AuthStatusService) { }

    ngOnInit() {
        this.auth = this.status.setAuth();
        this.getProduct();
        this.getWish();
        this.getCart();
    }

    getProduct() {
        this.api.getProductArticle(this.route.params['_value'].article).subscribe(res => {
            if (res.object == null) {
                this.router.navigate(['/404']);
            } else {
                this.receivedProduct = res.object;
                // this.rating = Number(res.object.rating);
                this.api.getCategoryRoute(this.receivedProduct.parentId).subscribe(rez => {
                    this.receivedCategory = rez.object;
                }, error => console.log(error));
                this.api.getReviews(res.object.productId).subscribe(rez => {
                    // this.receivedReviews = rez.object;
                    this.api.allUsers().subscribe(ref => {
                        for (let obj of rez.object) {
                            for (let ob of ref.object) {
                                if (ob.userid == obj.userId) {
                                    let va = { userid: ob.userid, fullname: ob.fullname };
                                    this.receivedReviews.push(Object.assign(obj, va));
                                }
                            }
                        }
                    });
                });
            }
        }, error => console.log(error));
    }

    getCart() {
        this.api.getCart(this.auth).subscribe(res => {
            this.receivedCart = res.object;
        }, error => console.log(error));
    }

    quan(prod: any) {
        return this.receivedCart.find(({ productId }) => productId == prod).productQuantity;
    }

    change(num: any, product: any) {
        let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
        if (num.srcElement.value > 0 && num.srcElement.value < product.productQuantity) {
            this.api.updateCart(this.auth, prod.cartId, num.srcElement.value).subscribe((res) => {
                this.getCart();
            });
        }
        if (num.srcElement.value > product.productQuantity) {
            // (document.getElementById('qty')['value']) = prod.productQuantity;
            this.pRef.nativeElement.value = prod.productQuantity;
            console.log('Неверное значение!');
        }

        if (num.srcElement.value < 1) {
            this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
                this.getCart();
            });
        }
    }

    plus(product: any) {
        let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
        if (prod.productQuantity < product.productQuantity) {
            prod.productQuantity++;
            this.api.updateCart(this.auth, prod.cartId, prod.productQuantity).subscribe((res) => {
                this.getCart();
            });
        } else {
            console.log('Нет необходимого кол-ва товаров!');
        }
    }

    minus(product: any) {
        let prod = this.receivedCart.find(({ productId }) => productId == product.productId);
        if (prod.productQuantity > 1) {
            prod.productQuantity--;
            this.api.updateCart(this.auth, prod.cartId, prod.productQuantity).subscribe((res) => {
                this.getCart();
            });
        } else {
            this.api.deleteCart(this.auth, prod.cartId).subscribe((res) => {
                this.getCart();
            });
        }
    }

    addToCart(prodId: any) {
        this.api.addToCart(this.auth, prodId).subscribe((res) => {
            this.getCart();
        });
    }

    getWish() {
        this.api.getWishlist(this.auth).subscribe(res => {
            this.receivedWishlist = res.object;
        }, error => console.log(error));
    }

    checkWish(prodId: any) {
        if (this.receivedWishlist) {
            if (this.receivedWishlist.find(({ productId }) => productId == prodId)) {
                return true;
            }
        }
    }
    checkCart(prodId: any) {
        if (this.receivedCart) {
            if (this.receivedCart.find(({ productId }) => productId == prodId)) {
                return true;
            }
        }
    }

    addToWishlist(productId: any) {
        this.api.addToWishlist(this.auth, productId).subscribe((res) => {
            this.getWish();
        });
    }

    delFromWishlist(prodId: any) {
        let prod = this.receivedWishlist.find(({ productId }) => productId == prodId);
        this.api.delFromWishlist(this.auth, prod.wishlistId).subscribe(res => {
            this.getWish();
        });
    }
}
