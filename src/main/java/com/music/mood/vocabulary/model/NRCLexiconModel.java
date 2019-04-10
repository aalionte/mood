package com.music.mood.vocabulary.model;

/**
 * Created by Admin on 01-Apr-19.
 */
public class NRCLexiconModel extends WordCharacteristics {

    private String word;

    public NRCLexiconModel(String word, Double valence, Double arousal, Double dominance) {
        super(arousal, dominance, valence);
        this.word = word;
    }

    public NRCLexiconModel(String word) {
        super();
        this.word = word;
    }

    public String getWord() {
        return word;
    }

}
