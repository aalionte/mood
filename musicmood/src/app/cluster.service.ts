import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClusterService {
  clusterUrl = 'http://localhost:8080/cluster';

  constructor(@Inject(HttpClient) private http: HttpClient) {
  }

  getCluster(request: any) {
    return this.http.get(this.clusterUrl, {
      headers: {
        'Content-Type': 'application/json; charset=utf-8'
      },
      params: {
        lyrics: request
      }
    });
  }
}
