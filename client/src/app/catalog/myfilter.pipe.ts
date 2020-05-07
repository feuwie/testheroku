import { Pipe, PipeTransform } from '@angular/core';
import { CatalogComponent } from './catalog.component';

@Pipe({
    name: 'myfilter',
    pure: false
})
export class MyFilterPipe implements PipeTransform {
    constructor(public catalog: CatalogComponent) { }
    transform(items: any[], filter: any): any {
        // console.log(filter);
        // console.log(filter2);
        // if (!items || !filter) {
        //     return items;
        // }
        if (filter == undefined) {
            return items;
        }
        // this.catalog.config.currentPage = 1;
        return items.filter(item => (item.productPrice >= filter[0] && item.productPrice <= filter[1]));
    }
}
