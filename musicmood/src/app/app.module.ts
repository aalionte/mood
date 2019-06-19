import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ChartsModule} from 'ng2-charts';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MusicFormComponent} from './music-form/music-form.component';
import {ClustersComponent} from './clusters/clusters.component';
import {TreeviewModule} from 'ngx-treeview';
import {SharedModule} from '@progress/kendo-angular-treeview';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TreeModule} from 'angular-tree-component';


@NgModule({
  declarations: [
    AppComponent,
    MusicFormComponent,
    ClustersComponent
  ],
  imports: [
    FormsModule,
    TreeModule.forRoot(),
    TreeviewModule.forRoot(),
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ChartsModule,
    SharedModule,
    TreeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
