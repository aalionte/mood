import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {Cluster} from './cluster';
import {get} from 'lodash';

@Injectable({
  providedIn: 'root'
})
export class ClusterService {
  clusterUrl = 'http://localhost:8080/cluster';
  private clusterList = new Subject();

  constructor(@Inject(HttpClient) private http: HttpClient) {
  }

  setCluster(request: any) {
    this.clusterList.next();
    return this.http.post(this.clusterUrl, request, {
      headers: {
        'Content-Type': 'application/json; charset=utf-8'
      }
    }).subscribe(data => {
        const tempData = get(data, 'clusters');
        this.clusterList.next(tempData.map(cl => new Cluster().deserialize(cl)));
      },
      error => {
        alert(error.message);
      });
  }

  getCluster(): Observable<any> {
    return this.clusterList.asObservable();
  }
}
