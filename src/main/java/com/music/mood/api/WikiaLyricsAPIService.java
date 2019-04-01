package com.music.mood.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Admin on 01-Apr-19.
 */
@Service
public class WikiaLyricsAPIService {
    private final static String WIKIA_URL = "http://lyrics.wikia.com/wiki/";

    public String getLyricsForArtist(String artist, String song) {
        artist = artist.replace(" ", "_");
        song = song.replace(" ", "_");
        String response = "";
        try {

            Document document = Jsoup.connect(WIKIA_URL + artist + ":" + song).get();
            Elements lyrics = document.getElementsByClass("lyricbox");
            if (lyrics.hasText()) {
                String lyricsText = "";
                for (Node el : lyrics.get(0).childNodes()) {
                    if (el instanceof TextNode) {
                        if (!el.hasSameValue("<br>")) {
                            lyricsText = lyricsText.concat(((TextNode) el).getWholeText() + "\n");
                        }
                    }
                }
                return lyricsText;
            }
            return null;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return response;
    }

}
