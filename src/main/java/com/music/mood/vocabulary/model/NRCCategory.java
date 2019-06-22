package com.music.mood.vocabulary.model;

public class NRCCategory {

    private String category;
    private String word;

    public NRCCategory(String word, String category) {
        this.category = category;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
