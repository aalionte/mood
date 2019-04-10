package com.music.mood.controllers;

import com.music.mood.api.WikiaLyricsAPIService;
import com.music.mood.clustering.DIANA;
import com.music.mood.model.WordModel;
import com.music.mood.services.ProcessLyricsService;
import com.music.mood.stanford.POSAnnotation;
import com.music.mood.vocabulary.model.NRCLexiconModel;
import com.music.mood.vocabulary.model.NRCLexiconService;
import edu.stanford.nlp.pipeline.Annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 01-Apr-19.
 */
@Controller
public class SearchController {
    /*    @Autowired
    private MusicoveryAPI musicoveryAPI;*/
    @Autowired
    private WikiaLyricsAPIService wikiaLyricsAPIService;
    @Autowired
    private NRCLexiconService nrcLexiconService;
    @Autowired
    private POSAnnotation posAnnotation;
    @Autowired
    private ProcessLyricsService processLyricsService;
    @Autowired
    private DIANA diana;

    @RequestMapping("/")
    public String home() {
        Map<String, NRCLexiconModel> dictionary = nrcLexiconService.readDictionary();
        String lyrics = wikiaLyricsAPIService.getLyricsForArtist("Sunlounger", "In and out");
        Annotation document = posAnnotation.getPOS(lyrics);
        List<WordModel> wordModels = processLyricsService.createDataForAnalysis(document, dictionary);
        diana.getClusters(wordModels, 0);
        return document.toString();
    }

}
