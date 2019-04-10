package com.music.mood.vocabulary.service;

import com.music.mood.vocabulary.model.WordCharacteristics;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 10-Apr-19.
 */
@Service
public class WordCharacteristicsService {

    //todo: rewrite this
    public WordCharacteristics difference(WordCharacteristics wordCharacteristics1, WordCharacteristics wordCharacteristics2) {
        WordCharacteristics wordCharacteristics = new WordCharacteristics();
        wordCharacteristics.setValence(wordCharacteristics1.getValence() - wordCharacteristics2.getValence());
        wordCharacteristics.setArousal(wordCharacteristics1.getArousal() - wordCharacteristics2.getArousal());
        wordCharacteristics.setDominance(wordCharacteristics1.getDominance() - wordCharacteristics2.getDominance());
        return wordCharacteristics;
    }

}
