package com.music.mood.model;

import com.music.mood.clustering.tf.TFSimilarity;

/**
 * Created by Admin on 19-May-19.
 */
public class TFModel {
    private int cluster;
    private Double tf;
    private String word;

    public TFModel(int cluster, Double tf, String word) {
        this.cluster = cluster;
        this.tf = tf;
        this.word = word;
    }
}
