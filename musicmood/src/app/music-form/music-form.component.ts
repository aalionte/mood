import {Component, OnInit} from '@angular/core';
import {Artist} from '../artist';
import {LyricsSearchService} from '../lyrics-search.service';
import {get} from 'lodash';
import {ClusterService} from '../cluster.service';


@Component({
  selector: 'app-music-form',
  templateUrl: './music-form.component.html',
  styleUrls: ['./music-form.component.css']
})
export class MusicFormComponent implements OnInit {
  artistModel = new Artist('', '');
  lyrics: any;

  constructor(private searchService: LyricsSearchService,
              private clusterService: ClusterService) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.searchService.getLyrics(this.artistModel)
      .subscribe(response => {
        this.lyrics = get(response, 'lyrics');
        this.clusterService.setCluster(this.lyrics);
      });
  }
}
