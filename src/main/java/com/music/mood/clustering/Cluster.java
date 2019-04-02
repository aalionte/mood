package com.music.mood.clustering;

import com.music.mood.model.WordModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 01-Apr-19.
 */
public class Cluster {

    private List<WordModel> wordModelList;

    public HashMap<String, WordDistance> getWordDistanceHashMap() {
        return wordDistanceHashMap;
    }

    public void setWordDistanceHashMap(HashMap<String, WordDistance> wordDistanceHashMap) {
        this.wordDistanceHashMap = wordDistanceHashMap;
    }

    private HashMap<String, WordDistance> wordDistanceHashMap;

    public Cluster(List<WordModel> wordModels) {
        wordModelList = wordModels;
        wordDistanceHashMap = new HashMap<>();
    }

    public List<WordModel> getWordModelList() {
        return wordModelList;
    }

    public void addWordDistance(String word, double valence, double arousal, double dominance) {
        wordDistanceHashMap.putIfAbsent(word, new WordDistance(valence, arousal, dominance));
    }

    private class WordDistance {
        private double valence;
        private double arousal;
        private double dominance;

        WordDistance(double valence, double arousal, double dominance) {
            this.valence = valence;
            this.arousal = arousal;
            this.dominance = dominance;
        }
    }

}
