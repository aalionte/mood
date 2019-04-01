package com.music.mood.controllers;

import com.music.mood.api.WikiaLyricsAPIService;
import com.music.mood.stanford.POSAnnotation;
import com.music.mood.vocabulary.NRCLexiconModel;
import com.music.mood.vocabulary.NRCLexiconService;
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

    @RequestMapping("/")
    public String home() {
        Map<String, NRCLexiconModel> dictionary = nrcLexiconService.readDictionary();
        String lyrics = wikiaLyricsAPIService.getLyricsForArtist("Sting", "Low Life");
        CoreDocument document = posAnnotation.getPOS(lyrics);
        return document.toString();
    }

}
