import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../model/product';

@Pipe({
    name: 'asortby',
    pure: false
})
export class ASortByPipe implements PipeTransform {
    constructor() { }
    transform(value: any, val2: any, ord: any) {
        if (ord == 'Сначала дешевые') {
            value.sort((a: Product, b: Product) => {
                return a.productPrice - b.productPrice;
            });
        }
        if (ord == 'Сначала дорогие') {
            value.sort((a: Product, b: Product) => {
                return b.productPrice - a.productPrice;
            });
        }
        if (ord == 'Сначала (а-я)') {
            value.sort((a: Product, b: Product) => {
                return a.productTitle[0].localeCompare(b.productTitle[0]);
            });
        }
        if (ord == 'Сначала (я-а)') {
            value.sort((a: Product, b: Product) => {
                return b.productTitle[0].localeCompare(a.productTitle[0]);
            });
        }
        // if (this.catalog.val == 'Сначала дешевые') {
        //     value.sort((a: Product, b: Product) => {
        //         return a.productPrice - b.productPrice;
        //     });
        // }
        // if (this.catalog.val == 'Сначала дорогие') {
        //     value.sort((a: Product, b: Product) => {
        //         return b.productPrice - a.productPrice;
        //     });
        // }
        // if (this.catalog.val == 'Сначала (а-я)') {
        //     value.sort((a: Product, b: Product) => {
        //         return a.productTitle[0].localeCompare(b.productTitle[0]);
        //     });
        // }
        // if (this.catalog.val == 'Сначала (я-а)') {
        //     value.sort((a: Product, b: Product) => {
        //         return b.productTitle[0].localeCompare(a.productTitle[0]);
        //     });
        // }
        return value;
    }
}
