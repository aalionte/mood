import {Component, Input, OnInit} from '@angular/core';
import {Cluster} from '../cluster';
import {ClusterService} from '../cluster.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-clusters',
  templateUrl: './clusters.component.html',
  styleUrls: ['./clusters.component.css']
})
export class ClustersComponent implements OnInit {
  @Input()
  cluster: any;
  public pieChartLabels = ['Happy', 'Calm', 'Sad', 'Angry'];
  public pieChartType = 'pie';
  subscription: Subscription;

  constructor(private clusterService: ClusterService) {
    this.subscription = this.clusterService.getCluster().subscribe(clusterList => {
      if (clusterList) {
        this.cluster = clusterList;
      } else {
        this.cluster = [];
      }
    });
  }

  ngOnInit() {
  }

  computeWords() {
    return this.cluster.map(el => {
      return el.wordList.length;
    });
  }
}
