package com.music.mood.model;

import com.music.mood.vocabulary.model.NRCLexiconModel;

/**
 * Created by Admin on 01-Apr-19.
 */
public class WordModel {

    private NRCLexiconModel nrcLexiconModel;
    private String pos;
    private String lemma;

    public WordModel(NRCLexiconModel nrcLexiconModel, String pos, String lemma) {
        this.nrcLexiconModel = nrcLexiconModel;
        this.pos = pos;
        this.lemma = lemma;
    }

    public NRCLexiconModel getNrcLexiconModel() {
        return nrcLexiconModel;
    }

    public void setNrcLexiconModel(NRCLexiconModel nrcLexiconModel) {
        this.nrcLexiconModel = nrcLexiconModel;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }
}
