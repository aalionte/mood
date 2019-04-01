package com.music.mood.controllers;

import com.music.mood.api.WikiaLyricsAPIService;
import com.music.mood.services.ProcessLyricsService;
import com.music.mood.stanford.POSAnnotation;
import com.music.mood.vocabulary.NRCLexiconModel;
import com.music.mood.vocabulary.NRCLexiconService;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/")
    public String home() {
        Map<String, NRCLexiconModel> dictionary = nrcLexiconService.readDictionary();
        String lyrics = wikiaLyricsAPIService.getLyricsForArtist("Sting", "Low Life");
        Annotation document = posAnnotation.getPOS(lyrics);
        processLyricsService.createDataForAnalysis(document, dictionary);
        return document.toString();
    }

}
