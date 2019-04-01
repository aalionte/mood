package com.music.mood.services;

import com.music.mood.model.WordModel;
import com.music.mood.vocabulary.NRCLexiconModel;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;
import edu.stanford.nlp.pipeline.Annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 01-Apr-19.
 */
@Service
public class ProcessLyricsService {

    public List<WordModel> createDataForAnalysis(Annotation document, Map<String, NRCLexiconModel> dictionary) {
        List<WordModel> wordModels = new ArrayList<>();
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                if (dictionary.containsKey(word)) {
                    wordModels.add(new WordModel(dictionary.get(word), pos, lemma));
                } else {
                    wordModels.add(new WordModel(new NRCLexiconModel(word, 0.0, 0.0, 0.0), pos, lemma));
                }
            }
        }
        return wordModels;
    }
}
