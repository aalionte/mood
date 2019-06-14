import {Word} from './word';
import {DeserializableModel} from './deserializable.model';

export class Cluster implements DeserializableModel {
  private word: any;
  public cluster: string;
  public wordList: Word[];

  constructor() {
  }

  deserialize(input: any): this {
    Object.assign(this, input);
    this.wordList = input.wordModelList.map((word) => new Word().deserialize(word));
    return this;
  }


}
