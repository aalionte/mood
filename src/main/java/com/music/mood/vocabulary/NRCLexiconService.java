package com.music.mood.vocabulary;

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

    @Autowired
    private ResourceLoader loader;

    public Map<String, NRCLexiconModel> readDictionary() {
        Map<String, NRCLexiconModel> nrcDictionary = new HashMap<>();
        Resource nrcLexiconFile = new ClassPathResource("/static/NRC-Sentiment-Emotion-Lexicons/NRC-VAD-Lexicon/NRC-VAD-Lexicon.txt");
        BufferedReader textReader;
        try {
            textReader = new BufferedReader(new FileReader(nrcLexiconFile.getFile()));
            String line;
            while ((line = textReader.readLine()) != null) {
                String[] data = line.split("\t");
                if (!data[1].toString().endsWith("Valence"))
                    nrcDictionary.put(data[0], new NRCLexiconModel(data[0], parseDouble(data[1]), parseDouble(data[2]), parseDouble(data[3])));
            }
            return nrcDictionary;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return nrcDictionary;
        }
    }
}
