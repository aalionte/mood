package com.music.mood.controllers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.music.mood.api.WikiaLyricsAPIService;
import com.music.mood.clustering.CategoryService;
import com.music.mood.clustering.diana.Cluster;
import com.music.mood.clustering.diana.DIANA;
import com.music.mood.clustering.kmeans.KCluster;
import com.music.mood.clustering.kmeans.KMeans;
import com.music.mood.clustering.kmeans.Point;
import com.music.mood.clustering.tf.TFSimilarity;
import com.music.mood.model.Category;
import com.music.mood.model.TFModel;
import com.music.mood.model.WordModel;
import com.music.mood.services.ProcessLyricsService;
import com.music.mood.stanford.POSAnnotation;
import com.music.mood.vocabulary.model.NRCCategory;
import com.music.mood.vocabulary.model.NRCLexiconModel;
import com.music.mood.vocabulary.model.NRCLexiconService;
import edu.stanford.nlp.pipeline.Annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 01-Apr-19.
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
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
    @Autowired
    private KMeans kMeans;
    @Autowired
    private TFSimilarity tfSimilarityService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/")
    public String home() {
        Map<String, NRCLexiconModel> dictionary = nrcLexiconService.readDictionary();
        String lyrics = wikiaLyricsAPIService.getLyricsForArtist("The Beach Boys", "Good Vibrations");
        Annotation document = posAnnotation.getPOS(lyrics);
        List<WordModel> wordModels = processLyricsService.createDataForAnalysis(document, dictionary);
        List<KCluster> kClusters = kMeans.kmeans(wordModels, ImmutableList.of(new Point(0.75, 0.75), new Point(0.75, 0.25), new Point(0.25, 0.25), new Point(0.25, 0.75)));
        List<TFModel> tfModels = new ArrayList<>();
        for (Cluster cluster : kClusters) {
            List<WordModel> wordsList = cluster.getWordModelList();
            HashMap<String, Double> tfSimilarity = tfSimilarityService.getTF(wordsList);
            tfSimilarity.entrySet().stream().forEach(entity -> {
                tfModels.add(new TFModel(kClusters.indexOf(cluster), entity.getValue(), entity.getKey()));
            });
        }
        return document.toString();
    }

    @GetMapping(value = "/search")
    public Map<String, String> search(@RequestParam(value = "artist") String artist, @RequestParam(value = "song") String song) {
        return ImmutableMap.of("lyrics", wikiaLyricsAPIService.getLyricsForArtist(artist, song));
    }

    @PostMapping(value = "/cluster")
    public ImmutableMap<String, List<KCluster>> cluster(@RequestBody String lyrics) {
        Map<String, NRCLexiconModel> dictionary = nrcLexiconService.readDictionary();
        Annotation document = posAnnotation.getPOS(lyrics);
        List<WordModel> wordModels = processLyricsService.createDataForAnalysis(document, dictionary);
        List<KCluster> kClusters = kMeans.kmeans(wordModels, ImmutableList.of(new Point(0.75, 0.75), new Point(0.75, 0.25), new Point(0.25, 0.25), new Point(0.25, 0.75)));
        return ImmutableMap.of("clusters", kClusters);
    }

    @GetMapping(value = "/searchFor")
    public Map<String, Category> searchByCategory(@RequestParam(value = "artist") String artist, @RequestParam(value = "song") String song) {
        Map<String, NRCCategory> dictionary = nrcLexiconService.readDictionaryForCategory();
        String lyrics = wikiaLyricsAPIService.getLyricsForArtist(artist, song);
        Annotation document = posAnnotation.getPOS(lyrics);
        Map<String, NRCCategory> wordModels = processLyricsService.createDataForAnalysisCategory(document, dictionary);
        return categoryService.getCategories(wordModels);
    }
}
