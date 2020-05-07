import { ASortByPipe } from './asortby.pipe';
import { AuthStatusService } from './../profile/authstatus.service';
import { FormatPipe } from './../pipe/format.pipe';
import { ApiService } from 'src/app/services/api.service';
import { Component, OnInit, Input, Output, ViewChildren } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Product } from '../model/product';

import { RangeFilter } from '../vertical-filter-bar/range-filter/range-filter.model';
import { CheckboxFilter } from '../vertical-filter-bar/checkbox-filter/checkbox-filter.model';
import { FBFilter } from '../vertical-filter-bar/fb-filter.interface';
import { FilterChoice } from '../model/filter-choice.model';


@Component({
    selector: 'app-catalog',
    templateUrl: './catalog.component.html',
    styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

    receivedProducts: any;
    receivedCategory: any;
    receivedWishlist: any;
    receivedCart: any;

    public auth: string;
    config: any;
    addedToWishlist: any[] = [];
    testArray: any[] = [];
    testArrayTwo: any[] = [];

    ord: any;

    component: any;


    public manufacturers: string[] = ['Apple', 'Dell', 'Acer', 'HP', 'Lenovo', 'Asus', 'Alienware', 'Cooler Master', 'Fujitsu', 'IBM', 'LG Eletronics', 'MSI', 'Nvidia', 'Panasonic', 'Samsung'];
    // public conditions: string[] = ['New', 'Refurbished', 'Used'];
    // public displaySizes: string[] = ['17 Inches and Above', '16 to 16.9 Inches', '15 to 15.9 Inches', '14 to 14.9 Inches', '13 to 13.9 Inches', '12 to 12.9 Inches', '12 Inches and Under'];
    // public ramSize: string[] = ['32GB and Above', '16GB', '12GB', '8GB', '6GB', '4GB', '3GB', '2GB and Under'];

    verticalBarFilters: FBFilter[] = [
        new RangeFilter('price', 'Цена', 0, 100000, true),
        new CheckboxFilter('manufacturer', 'Manufacturer', this.manufacturers, 6),
        // new CheckboxFilter('condition', 'Condition', this.conditions),
        // new CheckboxFilter('display-size', 'Display Size', this.displaySizes),
        // new CheckboxFilter('ram-size', 'RAM Size', this.ramSize)
    ];


    public filterResults: any;
    public filterChoices: FilterChoice[];


    constructor(public router: Router, public route: ActivatedRoute, public api: ApiService, public status: AuthStatusService) {
        this.config = {
            currentPage: 1,
            itemsPerPage: 2
        };
    }

    public removeFilterChoice(choice: FilterChoice, value: string): void {
        let params = Object.assign({}, this.route.snapshot.queryParams);
        if (choice.values.length == 1) {
            params[choice.identifier] = undefined;
        } else {
            params[choice.identifier] = params[choice.identifier].slice();
            let index = params[choice.identifier].indexOf(value);
            params[choice.identifier].splice(index, 1);
            if (params[choice.identifier].length == 0) {
                params[choice.identifier] = undefined;
            }
        }
        params.page = "1";
        this.router.navigate([], { queryParams: params });
    }

    ngOnInit() {
        this.auth = this.status.setAuth();
        this.component = 'catalog';
        this.getProducts();
        this.getCategory();
        this.route.queryParamMap.subscribe((paramsMap: Params) => {
            this.filterResults = paramsMap.params;
            this.filterChoices = [];
            if (this.filterResults) {
                if (this.filterResults['price']) {
                    let priceRange: number[] = this.filterResults['price'];
                    this.filterChoices.push(new FilterChoice('price', 'Цена', ['от ' + new FormatPipe().transform(priceRange[0]) + ' ₽' + ' до ' + new FormatPipe().transform(priceRange[1]) + ' ₽']));
                }
                if (this.filterResults['manufacturer']) {
                    if (Array.isArray(this.filterResults['manufacturer'])) {
                        this.filterChoices.push(new FilterChoice('manufacturer', 'Manufacturer', this.filterResults['manufacturer']))
                    } else {
                        this.filterChoices.push(new FilterChoice('manufacturer', 'Manufacturer', new Array(this.filterResults['manufacturer'])))
                    }
                }
                if (this.filterResults['page']) {
                    this.config = {
                        currentPage: paramsMap.params.page ? paramsMap.params.page : 1,
                        itemsPerPage: 2
                    };
                }
                // if (this.filterResults['page']) {
                //     console.log(this.filterResults['page']);
                //     console.log(this.filterChoices);
                //     this.filterChoices.push(new FilterChoice('page', 'Page', new Array(this.filterResults['page'])));
                //     console.log(this.filterChoices);
                // }

                // if (this.filterResults['condition']) {
                //     if (Array.isArray(this.filterResults['condition'])) {
                //         this.filterChoices.push(new FilterChoice('condition', 'Condition', this.filterResults['condition']))
                //     } else {
                //         this.filterChoices.push(new FilterChoice('condition', 'Condition', new Array(this.filterResults['condition'])))
                //     }
                // }
                // if (this.filterResults['display-size']) {
                //     if (Array.isArray(this.filterResults['display-size'])) {
                //         this.filterChoices.push(new FilterChoice('display-size', 'Display Size', this.filterResults['display-size']))
                //     } else {
                //         this.filterChoices.push(new FilterChoice('display-size', 'Display Size', new Array(this.filterResults['display-size'])))
                //     }
                // }
                // if (this.filterResults['ram-size']) {
                //     if (Array.isArray(this.filterResults['ram-size'])) {
                //         this.filterChoices.push(new FilterChoice('ram-size', 'RAM Size', this.filterResults['ram-size']))
                //     } else {
                //         this.filterChoices.push(new FilterChoice('ram-size', 'RAM Size', new Array(this.filterResults['ram-size'])))
                //     }
                // }
            }
        });
    }

    getProducts() {
        this.api.getProductParent(this.route.snapshot.params.id).subscribe(res => {
            this.receivedProducts = res.object;
        }, error => console.log(error));
    }

    getCategory() {
        this.api.getCategoryRoute(this.route.snapshot.params.id).subscribe(res => {
            if (res.object == null) {
                this.router.navigate(['/404']);
            } else {
                this.receivedCategory = res.object;
            }
        }, error => console.log(error));
    }

    pageChange(newPage: number) {
        // this.router.navigate([], { relativeTo: this.route, queryParams: { page: newPage }, queryParamsHandling: 'merge', skipLocationChange: true });
        this.router.navigate([], { relativeTo: this.route, queryParams: { page: newPage }, queryParamsHandling: 'merge' });
    }
}
