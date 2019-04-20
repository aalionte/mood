package com.music.mood.clustering.diana;

import com.music.mood.model.WordModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Admin on 01-Apr-19.
 */
public class Cluster {

    private List<WordModel> wordModelList;

    public Cluster() {
        wordModelList = new ArrayList<>();
    }

    public Cluster(List<WordModel> wordModels) {
        wordModelList = wordModels;
    }

    public List<WordModel> getWordModelList() {
        return wordModelList;
    }

    public void setWordModelList(List<WordModel> wordModelList) {
        this.wordModelList = wordModelList;
    }
}
