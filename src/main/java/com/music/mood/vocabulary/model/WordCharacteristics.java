package com.music.mood.vocabulary.model;

import com.music.mood.model.WordModel;

import java.util.DoubleSummaryStatistics;

/**
 * Created by Admin on 10-Apr-19.
 */
public class WordCharacteristics {
    protected Double valence;// max = 1.0 min = 0.0
    protected Double arousal; // max = 0.99 min = 0.046
    protected Double dominance; // max = 0.991 min = 0.045


    public WordCharacteristics() {
        this.arousal = 0.0;
        this.valence = 0.0;
        this.dominance = 0.0;
    }

    public WordCharacteristics(Double arousal, Double dominance, Double valence) {
        this.arousal = arousal;
        this.dominance = dominance;
        this.valence = valence;
    }

    public void setValence(Double valence) {
        this.valence = valence;
    }

    public void setArousal(Double arousal) {
        this.arousal = arousal;
    }

    public void setDominance(Double dominance) {
        this.dominance = dominance;
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

    public Double distanceTo(WordCharacteristics nrcLexiconModel) {
        return Math.sqrt(Math.pow(arousal - nrcLexiconModel.arousal, 2) + Math.pow(valence - nrcLexiconModel.valence, 2));
    }
}
