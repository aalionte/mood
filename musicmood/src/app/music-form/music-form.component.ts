import { Component, OnInit } from '@angular/core';
import {Artist} from '../artist';
@Component({
  selector: 'app-music-form',
  templateUrl: './music-form.component.html',
  styleUrls: ['./music-form.component.css']
})
export class MusicFormComponent implements OnInit {
  artistModel = new Artist("", "");

  constructor() { }

  ngOnInit() {
  }

onSubmit(){
console.log(this.artistModel);
}
}
