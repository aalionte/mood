package com.music.mood.clustering;

import com.music.mood.model.WordModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 01-Apr-19.
 */
public class DIANA {
    //http://www.inf.unibz.it/dis/teaching/DWDM/slides2010/lesson9-Clustering.pdf
    public void getClusters(List<WordModel> wordsList, double entropy, double distance) {
        HashMap<Integer, Cluster> currentCluster = new HashMap<>();
        currentCluster.putIfAbsent(0, new Cluster(wordsList));
        Cluster cluster1 = new Cluster(wordsList);
        Cluster cluster2 = new Cluster(new ArrayList<>());
        double index = 0;
        while (index != -1) {

            split(cluster1, cluster2);
        }
    }

    //http://www.mind.disco.unimib.it/public/opere/139.pdf
    //http://www.mind.disco.unimib.it/public/opere/139.pdf
    private void split(Cluster cluster1, Cluster cluster2) {
        double averageArousal = 0.0;
        double averageDominance = 0.0;
        double averageValence = 0.0;
        List<WordModel> wordsList = cluster1.getWordModelList();
        for (WordModel wordsListElement : wordsList) {
            for (WordModel wordListElement2 : wordsList) {
                if (!wordsListElement.equals(wordListElement2)) {
                    averageArousal += wordsListElement.getNrcLexiconModel().getArousal();
                    averageDominance += wordsListElement.getNrcLexiconModel().getDominance();
                    averageValence += wordsListElement.getNrcLexiconModel().getValence();
                }
            }
            averageArousal = averageArousal / wordsList.size();
            averageDominance = averageDominance / wordsList.size();
            averageValence = averageValence / wordsList.size();
            cluster1.addWordDistance(wordsListElement.getNrcLexiconModel().getWord(), averageValence, averageDominance, averageValence);
        }
    }
}