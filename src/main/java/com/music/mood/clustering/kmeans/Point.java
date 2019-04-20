package com.music.mood.clustering.kmeans;

/**
 * Created by Admin on 20-Apr-19.
 */
public class Point {

    private Double arousal;
    private Double valence;

    public Point(Double arousal, Double valence) {
        this.arousal = arousal;
        this.valence = valence;
    }

    public Double getValence() {
        return valence;
    }

    public void setValence(Double valence) {
        this.valence = valence;
    }

    public Double getArousal() {
        return arousal;
    }

    public void setArousal(Double arousal) {
        this.arousal = arousal;
    }
}
