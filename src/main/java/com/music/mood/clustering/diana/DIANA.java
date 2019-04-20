package com.music.mood.clustering.diana;

import com.music.mood.model.WordModel;
import com.music.mood.vocabulary.model.NRCLexiconModel;
import com.music.mood.vocabulary.model.WordCharacteristics;
import com.music.mood.vocabulary.service.WordCharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 01-Apr-19.
 */
@Service
public class DIANA {

    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DIANA.class);
    @Autowired
    private WordCharacteristicsService wordCharacteristicsService;

    //http://www.inf.unibz.it/dis/teaching/DWDM/slides2010/lesson9-Clustering.pdf
    //todo: find condition for existing while
    public void getClusters(List<WordModel> wordsList, double distance) {
        HashMap<Integer, Cluster> currentCluster = new HashMap<>();
        currentCluster.putIfAbsent(0, new Cluster(wordsList));
        Cluster cluster1 = new Cluster(wordsList);
        Cluster cluster2 = new Cluster(new ArrayList<>());
        logger.info("Entering DIANA");
        int index = 0;
        while (index != -1) {
            logger.info("DIANA level: " + index);
            split(cluster1, cluster2);
            currentCluster.remove(index);
            currentCluster.put(index, cluster1);
            currentCluster.put(index + 1, cluster2);
            index++;
            if (index == 20) {
                index = -1;
            }
        }
    }

    private Double averageDistanceWithinCluster(WordModel currentElement, Cluster cluster) {
        Double distance = Double.valueOf(0);
        for (WordModel wordModel : cluster.getWordModelList()) {
            distance += currentElement.getNrcLexiconModel().distanceTo(wordModel.getNrcLexiconModel());
        }

        if (cluster.getWordModelList().size() > 1) {
            int clusterSize = cluster.getWordModelList().size();
            return distance / clusterSize;
        }
        return distance;
    }

    private Double averageAcrossCluster(WordModel currentElement, Cluster cluster) {
        Double distance = Double.valueOf(0);
        if (cluster.getWordModelList().size() == 0) {
            return distance;
        }
        for (WordModel wordModel : cluster.getWordModelList()) {
            distance += (currentElement.getNrcLexiconModel().distanceTo(wordModel.getNrcLexiconModel()));
        }

        return distance / cluster.getWordModelList().size();
    }

    private WordModel splinter(Cluster cluster1, Cluster cluster2) {
        WordModel pivot = new WordModel(new NRCLexiconModel(""), "", "");
        for (WordModel wordCluster1 : cluster1.getWordModelList()) {
            Double x = averageAcrossCluster(wordCluster1, cluster1);
            Double y = averageDistanceWithinCluster(wordCluster1, cluster2);
            if (x - y > 0) {
                pivot = wordCluster1;
            }
        }
        return pivot;
    }

    private void split(Cluster mainList, Cluster splinterCluster) {
        WordModel pivot = splinter(mainList, splinterCluster);
        while (!pivot.getNrcLexiconModel().getWord().equals("")) {
            List<WordModel> newWordList = mainList.getWordModelList();
            newWordList.remove(pivot);
            mainList.setWordModelList(newWordList);
            splinterCluster.getWordModelList().add(pivot);
            pivot = splinter(mainList, splinterCluster);
        }
    }

    //http://www.mind.disco.unimib.it/public/opere/139.pdf
    //http://www.mind.disco.unimib.it/public/opere/139.pdf

    private void getWordCharacteristicsAverage(WordCharacteristics wordCharacteristics, int clusterSize) {
        wordCharacteristics.setValence(wordCharacteristics.getValence() / clusterSize);
        wordCharacteristics.setArousal(wordCharacteristics.getArousal() / clusterSize);
        wordCharacteristics.setDominance(wordCharacteristics.getDominance() / clusterSize);
    }
}