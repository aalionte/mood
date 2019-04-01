package com.music.mood.stanford;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by Admin on 01-Apr-19.
 */
@Service
public class POSAnnotation {
    public CoreDocument getPOS(String text) {
        Properties properties = new Properties();
//        https://stanfordnlp.github.io/CoreNLP/annotators.html
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        return document;
    }
}
