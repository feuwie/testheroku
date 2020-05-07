import { Pipe, PipeTransform } from '@angular/core';
import { CatalogComponent } from './catalog.component';
import { Product } from '../model/product';

@Pipe({
    name: 'mybrand',
    pure: false
})
export class MyBrandPipe implements PipeTransform {
    constructor(private catalog: CatalogComponent) { }
    transform(items: any[], filter: any): any {
        let arr = [];
        let mapped = [];
        if (filter == undefined) {
            return items;
        }
        if (typeof (filter) == 'string') {
            mapped = Array(filter);
        } else {
            mapped = filter;
        }
        for (let filt of mapped) {
            // for (let item of items.filter(item => (item.productAbout == filt))) {
            //     arr.push(item);
            // }
        }
        return arr;
    }
}
