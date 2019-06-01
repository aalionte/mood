export class Word {

  constructor(
    public lemma: string,
    public nrcLexiconModel:
      {
        public; word: string;
        valence: number;
        dominance: number;
        arousal: number
      },
    public pos: string
  ) {
  }
}
