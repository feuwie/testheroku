import { ASortByPipe } from './catalog/asortby.pipe';
import { MyBrandPipe } from './catalog/mybrand.pipe';
import { CartComponent } from './cart/cart.component';
import { ProductComponent } from './product/product.component';
import { SearchComponent } from './search/search.component';
import { CatalogComponent } from './catalog/catalog.component';
import { ProfileComponent } from './profile/profile.component';
import { CategoryComponent } from './category/category.component';
import { MainComponent } from './main/main.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ApiService } from './services/api.service';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { FormatPipe } from './pipe/format.pipe';
import { StorageServiceModule } from 'angular-webstorage-service';
import { WishlistComponent } from './wishlist/wishlist.component';
import { SuccessOrderComponent } from './success-order/success-order.component';
import { AuthComponent } from './auth/auth.component';
import { NgOtpInputModule } from 'ng-otp-input';
import { AuthStatusService } from './profile/authstatus.service';
import { CheckStatusComponent } from './check-status/check-status.component';
import { OrdersComponent } from './orders/orders.component';
import { PanelComponent } from './panel/panel.component';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SliderModule } from 'primeng/slider';
import { VerticalFilterBarComponent } from './vertical-filter-bar/vertical-filter-bar.component';
import { RangeFilterComponent } from './vertical-filter-bar/range-filter/range-filter.component';
import { ShowMoreModalComponent } from './vertical-filter-bar/checkbox-filter/show-more-modal/show-more-modal.component';
import { CheckboxFilterComponent } from './vertical-filter-bar/checkbox-filter/checkbox-filter.component';
import { MyFilterPipe } from './catalog/myfilter.pipe';
import { ProdComponent } from './prod/prod.component';
import { RtlService } from './rtl.service';
import { CardComponent } from './cart/card/card.component';
import { StarRatingComponent } from './star-rating/star-rating.component';



@NgModule({
    declarations: [
        AppComponent,
        FooterComponent,
        HeaderComponent,
        PagenotfoundComponent,
        MainComponent,
        CategoryComponent,
        FormatPipe,
        ProfileComponent,
        CatalogComponent,
        SearchComponent,
        ProductComponent,
        CartComponent,
        WishlistComponent,
        SuccessOrderComponent,
        AuthComponent,
        CheckStatusComponent,
        OrdersComponent,
        PanelComponent,
        VerticalFilterBarComponent,
        CheckboxFilterComponent,
        RangeFilterComponent,
        ShowMoreModalComponent,
        MyFilterPipe,
        MyBrandPipe,
        ASortByPipe,
        ProdComponent,
        CardComponent,
        StarRatingComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
        AppRoutingModule,
        RouterModule,
        NgxPaginationModule,
        StorageServiceModule,
        NgOtpInputModule,
        BrowserAnimationsModule,
        SliderModule,
    ],
    providers: [ApiService, AuthStatusService, RtlService],
    bootstrap: [AppComponent]
})
export class AppModule { }
