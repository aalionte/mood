import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MusicFormComponent } from './music-form/music-form.component';
import { ClustersComponent } from './clusters/clusters.component';

@NgModule({
  declarations: [
    AppComponent,
    MusicFormComponent,
    ClustersComponent
  ],
  imports: [
  FormsModule ,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
