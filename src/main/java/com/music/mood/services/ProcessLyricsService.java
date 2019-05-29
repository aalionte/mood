package com.music.mood.services;

import com.google.common.collect.ImmutableList;
import com.music.mood.model.WordModel;
import com.music.mood.vocabulary.model.NRCLexiconModel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 01-Apr-19.
 */
@Service
public class ProcessLyricsService {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProcessLyricsService.class);

    ImmutableList<String> verbPOS = ImmutableList.of("VB", "VBD", "VBG", "VBN", "VBP", "VBZ", "MD");

    public List<WordModel> createDataForAnalysis(Annotation document, Map<String, NRCLexiconModel> dictionary) {
        List<WordModel> wordModels = new ArrayList<>();
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        boolean shouldBeNegate = false;
        //https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html
        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                //    logger.info("word :" + word + " lema: " + lemma);

                if (lemma.equals("not") || lemma.equals("no")) {
                    shouldBeNegate = true;
                    if (isLastItemVerb(wordModels)) {
                        addNegativeWord(dictionary, wordModels, word, pos, lemma);
                        shouldBeNegate = false;
                        continue;
                    }
                }

                if (shouldBeNegate) {
                    addNegativeWord(dictionary, wordModels, word, pos, lemma);
                    shouldBeNegate = false;
                } else {
                    addPositiveWord(dictionary, wordModels, word, pos, lemma);
                }

            }
        }
        return wordModels;
    }

    private boolean isLastItemVerb(List<WordModel> wordModels) {
        WordModel lastItem = wordModels.get(wordModels.size() - 1);
        return isVerb(lastItem.getPos());
    }

    private void addNegativeWord(Map<String, NRCLexiconModel> dictionary, List<WordModel> wordModels, String word, String pos, String lemma) {
        NRCLexiconModel inverseWord = new NRCLexiconModel();
        if (isLastItemVerb(wordModels)) {
            WordModel lastItem = wordModels.get(wordModels.size() - 1);
            wordModels.remove(lastItem);
            inverseWord = createInverseWord(dictionary.get(lastItem.getLemma()), true);
            wordModels.add(new WordModel(inverseWord, pos, lastItem.getLemma()));
        } else {

            if (dictionary.containsKey(word)) {
                inverseWord = createInverseWord(dictionary.get(word));
                logger.info("Negative word: " + inverseWord.getWord());
            } else {
                if (dictionary.containsKey(lemma)) {
                    inverseWord = createInverseWord(dictionary.get(lemma));
                    logger.info("Negative word lemma: " + inverseWord.getWord());
                }
            }
            wordModels.add(new WordModel(inverseWord, pos, lemma));
        }
    }

    private NRCLexiconModel createInverseWord(NRCLexiconModel nrcLexiconModel, boolean... isVerb) {
        String word = isVerb.length >= 1 ? nrcLexiconModel.getWord() + " not " : "not " + nrcLexiconModel.getWord();
        return new NRCLexiconModel(word, 1 - nrcLexiconModel.getValence(), 1 - nrcLexiconModel.getArousal(), 1 - nrcLexiconModel.getDominance());
    }

    private void addPositiveWord(Map<String, NRCLexiconModel> dictionary, List<WordModel> wordModels, String word, String pos, String lemma) {
        if (dictionary.containsKey(word)) {
            wordModels.add(new WordModel(dictionary.get(word), pos, lemma));
        } else if (dictionary.containsKey(lemma)) {
            wordModels.add(new WordModel(dictionary.get(lemma), pos, lemma));
        }
    }

    private boolean isVerb(String pos) {
        return verbPOS.contains(pos);
    }
}