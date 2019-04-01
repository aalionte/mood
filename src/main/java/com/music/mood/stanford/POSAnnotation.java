package com.music.mood.stanford;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Admin on 01-Apr-19.
 */
@Service
public class POSAnnotation {
    public Annotation getPOS(String text) {
//        https://stanfordnlp.github.io/CoreNLP/annotators.html
        //https://stanfordnlp.github.io/CoreNLP/memory-time.html#parse
        StanfordCoreNLP pipeline = new StanfordCoreNLP(
                PropertiesUtils.asProperties(
                        "annotators", "tokenize,ssplit,pos,lemma,parse",
                        "ssplit.isOneSentence", "true",
                       // "parse.model", "edu/stanford/nlp/models/srparser/englishSR.ser.gz"        ,
                        "tokenize.language", "en"));

        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        return document;
    }
}
