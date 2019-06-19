import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {Cluster} from './cluster';
import {get} from 'lodash';

import {TreeviewItem} from 'ngx-treeview';

@Injectable({
  providedIn: 'root'
})
export class ClusterService {
  clusterUrl = 'http://localhost:8080/cluster';
  private clusterList = new Subject();
  mapTreeCategories = ['happy', 'calm', 'sad', 'angry'];


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

  mapTree(clusterItems) {
    const treeMap = [];
    let key = 0;
    for (const cluster of clusterItems) {
      const childrenTree = [];
      const treeItem = {
        id: key,
        size: cluster.wordModelList.length,
        name: this.mapTreeCategories[key],
        children: childrenTree
      };
      cluster.wordModelList.forEach(
        value => {
          const child = {
            id: value.lemma,
            name: value.lemma
          };
          childrenTree.push(child);
        }
      );
      key++;
      treeMap.push(treeItem);
    }
    return treeMap;

  }
}
