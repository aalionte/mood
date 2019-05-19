package com.music.mood.clustering.tf;

import com.music.mood.model.WordModel;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 01-May-19.
 */
@Service
public class TFSimilarity {

    public HashMap<String, Double> getTF(List<WordModel> words) {
        HashMap<String, Double> tfCalculation = new HashMap<>();
        int listSize = words.size();
        long occurrence = 0;
        for (WordModel word : words) {
            occurrence = words.stream()
                    .filter(currentWord -> currentWord.getNrcLexiconModel().getWord().equals(word.getNrcLexiconModel().getWord()))
                    .count();
            tfCalculation.putIfAbsent(word.getNrcLexiconModel().getWord(), Double.valueOf(Double.valueOf(occurrence) / listSize));
        }
        return tfCalculation;
    }
}
