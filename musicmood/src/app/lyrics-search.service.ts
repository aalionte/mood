import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Artist} from './artist';

@Injectable({
  providedIn: 'root'
})
export class LyricsSearchService {
  searchUrl = 'http://localhost:8080/search';

  constructor(@Inject(HttpClient) private http: HttpClient) {
  }

  getLyrics(searchDetails) {
    return this.http.get(this.searchUrl, {
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        },
        params: {
          artist: searchDetails.artist,
          song: searchDetails.song
        }
      }
    );
  }
}
