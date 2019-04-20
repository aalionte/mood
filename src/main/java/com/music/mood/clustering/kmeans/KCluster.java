package com.music.mood.clustering.kmeans;

import com.music.mood.clustering.diana.Cluster;

/**
 * Created by Admin on 20-Apr-19.
 */
public class KCluster extends Cluster {
    private Point centroid;

    public KCluster(Point centroid) {
        super();
        this.centroid = centroid;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }
}
