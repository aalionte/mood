package com.music.mood.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {
    String category;
    ArrayList words;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList getWords() {
        return words;
    }

    public void setWords(ArrayList words) {
        this.words = words;
    }

    public Category(String word, String category) {
        if (words == null) {
            words = new ArrayList();
        }
        words.add(word);
        this.category = category;
    }

    Category() {
        words = new ArrayList();
    }

    public void addWord(String word) {
        words.add(word);
    }
}
