import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ViewChildren, QueryList, ElementRef, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { FBFilter } from './fb-filter.interface';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
    selector: 'app-vertical-filter-bar',
    templateUrl: './vertical-filter-bar.component.html',
    styleUrls: ['./vertical-filter-bar.component.css']
})
export class VerticalFilterBarComponent implements OnInit {


    @Input() public filters: FBFilter[];

    public filterResults: any = new Object();

    @ViewChildren('filterPanel') public filterPanels: QueryList<ElementRef>;

    @Output() public filterResultsChange: EventEmitter<any> = new EventEmitter();

    constructor(public changeDetector: ChangeDetectorRef, public route: ActivatedRoute, public router: Router) { }

    ngOnInit() {
        this.route.queryParamMap.subscribe((paramsMap: Params) => {
            this.filters.forEach(filterModel => {
                const paramFilter = paramsMap.params[filterModel.identifier];
                if (paramFilter) {
                    if (Array.isArray(filterModel.filterValue)) {
                        if (!Array.isArray(paramFilter)) {
                            filterModel.filterValue = new Array();
                            filterModel.filterValue.push(paramFilter);
                        } else {
                            filterModel.filterValue = paramFilter.slice();
                        }
                    } else {
                        filterModel.filterValue = paramFilter;
                    }
                    this.filterResults[filterModel.identifier] = filterModel.filterValue;
                } else {
                    filterModel.resetFilterValue();
                    this.filterResults[filterModel.identifier] = undefined;
                }
            });
        });
    }

    onFilterChanged(identifier: string, filterValue: any): void {
        if (filterValue != undefined && filterValue.length == 0) {
            filterValue = undefined;
        }
        this.filterResults[identifier] = filterValue;
        this.router.navigate([], { relativeTo: this.route, queryParams: this.filterResults, queryParamsHandling: 'merge' });
        this.filterResultsChange.emit(this.filterResults);
    }
}
