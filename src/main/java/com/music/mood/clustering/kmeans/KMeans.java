package com.music.mood.clustering.kmeans;

import com.music.mood.model.WordModel;
import com.music.mood.vocabulary.model.WordCharacteristics;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Admin on 20-Apr-19.
 */
@Service
public class KMeans {

    public List<KCluster> kmeans(List<WordModel> wordModelList, List<Point> centroids) {
        List<KCluster> clusters = initCluster(wordModelList, centroids);
        sortWordsByX(wordModelList);
        HashMap<WordModel, Integer> wordListWithLabels = addLabels(wordModelList, centroids);
        formClusters(wordListWithLabels, clusters);
        return clusters;
    }


    private List<KCluster> initCluster(List<WordModel> wordModelList, List<Point> centroids) {
        List<KCluster> clusters = centroids.stream().map(KCluster::new).collect(Collectors.toList());
        return clusters;
    }
//https://www.researchgate.net/publication/235361517_A_Circumplex_Model_of_Affect
    private void sortWordsByX(List<WordModel> wordModelList) {
        Collections.sort(wordModelList, (lhs, rhs) -> lhs.getNrcLexiconModel().getValence() > rhs.getNrcLexiconModel().
                getValence() ? -1 : (lhs.getNrcLexiconModel().getValence() < rhs.getNrcLexiconModel().getValence()) ? 1 : 0);
    }

    private HashMap<WordModel, Integer> addLabels(List<WordModel> wordModelList, List<Point> centroids) {
        HashMap<WordModel, Integer> wordListWithLabels = new HashMap<>();
        for (WordModel word : wordModelList) {
            wordListWithLabels.put(word, getNearestNeighbour(word, centroids));
        }
        return wordListWithLabels;
    }

    private Integer getNearestNeighbour(WordModel word, List<Point> centroids) {
        Point wordPoint = new Point(word.getNrcLexiconModel().getArousal(), word.getNrcLexiconModel().getValence());
        Double minDistance = Double.MAX_VALUE;
        Integer bestCluster = 0;
        for (Point centroid : centroids) {
            Double actualDistance = distance(wordPoint, centroid);
            if (actualDistance < minDistance) {
                minDistance = actualDistance;
                bestCluster = centroids.indexOf(centroid);
            }
        }
        return bestCluster;
    }

    private Double distance(Point wordPoint, Point centroid) {
        return Math.sqrt(Math.pow(wordPoint.getArousal() - centroid.getArousal(), 2) + Math.pow(wordPoint.getValence() - centroid.getValence(), 2));
    }

    private void formClusters(HashMap<WordModel, Integer> wordListWithLabels, List<KCluster> clusters) {
        for (Map.Entry<WordModel, Integer> word : wordListWithLabels.entrySet()) {
            KCluster cluster = clusters.get(word.getValue());
            List<WordModel> wordList = cluster.getWordModelList();
            wordList.add(word.getKey());
            cluster.setWordModelList(wordList);
        }
    }
}
