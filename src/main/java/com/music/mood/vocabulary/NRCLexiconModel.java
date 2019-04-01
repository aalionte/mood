package com.music.mood.vocabulary;

/**
 * Created by Admin on 01-Apr-19.
 */
public class NRCLexiconModel {

    private String word;
    private Double valence;
    private Double arousal;
    private Double dominance;

    public NRCLexiconModel(String word, Double valence, Double arousal, Double dominance) {
        this.word = word;
        this.valence = valence;
        this.arousal = arousal;
        this.dominance = dominance;
    }

    public String getWord() {
        return word;
    }

    public Double getValence() {
        return valence;
    }

    public Double getArousal() {
        return arousal;
    }

    public Double getDominance() {
        return dominance;
    }
}
