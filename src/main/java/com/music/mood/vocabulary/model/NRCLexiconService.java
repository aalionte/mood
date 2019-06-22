package com.music.mood.vocabulary.model;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

/**
 * Created by Admin on 01-Apr-19.
 */
@Service
public class NRCLexiconService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(NRCLexiconService.class);


    @Autowired
    private ResourceLoader loader;

    public Map<String, NRCLexiconModel> readDictionary() {
        Map<String, NRCLexiconModel> nrcDictionary = new HashMap<>();
        Resource nrcLexiconFile = new ClassPathResource("/static/resources/NRC-Sentiment-Emotion-Lexicons/NRC-VAD-Lexicon/NRC-VAD-Lexicon.txt");
        BufferedReader textReader;
        try {
            textReader = new BufferedReader(new FileReader(nrcLexiconFile.getFile()));
            String line;
            while ((line = textReader.readLine()) != null) {
                String[] data = line.split("\t");
                if (!data[1].endsWith("Valence"))
                    nrcDictionary.put(data[0], new NRCLexiconModel(data[0], parseDouble(data[1]), parseDouble(data[2]), parseDouble(data[3])));
            }
            logger.info("NRC Dictionary size: " + nrcDictionary.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return nrcDictionary;
        }
    }

    public Map<String, NRCCategory> readDictionaryForCategory() {
        Map<String, NRCCategory> nrcCategoryMap = new HashMap<>();
        Resource nrcLexiconFile = new ClassPathResource("/static/resources/NRC-Sentiment-Emotion-Lexicons/NRC-Affect-Intensity-Lexicon/NRC-AffectIntensity-Lexicon.txt");
        try {
            BufferedReader textReader = new BufferedReader(new FileReader(nrcLexiconFile.getFile()));
            String line;
            while ((line = textReader.readLine()) != null) {
                String[] data = line.split("\t");
                if (!data[2].endsWith("AffectDimension")) {
                    nrcCategoryMap.put(data[0], new NRCCategory(data[0], data[2]));
                }
            }
            logger.info("NRC Dictionary size: " + nrcCategoryMap.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return nrcCategoryMap;
        }
    }
}
