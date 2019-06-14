import {DeserializableModel} from './deserializable.model';

export class Word implements DeserializableModel {

  public lemma: string;
  public nrcLexiconModel:
    {
      public; word: string;
      valence: number;
      dominance: number;
      arousal: number
    };
  public pos: string;

  constructor() {
  }

  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }
}
