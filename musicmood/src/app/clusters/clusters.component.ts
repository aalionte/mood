import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-clusters',
  templateUrl: './clusters.component.html',
  styleUrls: ['./clusters.component.css']
})
export class ClustersComponent implements OnInit {
  @Input()
  cluster: any;

  constructor() {
  }

  ngOnInit() {
  }

}
