package com.company.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Explanation implements Serializable {
    private String meaning;
    private String pronunciation;
    private String wordType;
    private String exampleInUse;

    public Explanation() {
    }

    public Explanation(String meaning, String pronunciation, String wordType, String exampleInUse) {
        this.meaning = meaning;
        this.pronunciation = pronunciation;
        this.wordType = wordType;
        this.exampleInUse = exampleInUse;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getExampleInUse() {
        return exampleInUse;
    }

    public void setExampleInUse(String exampleInUse) {
        this.exampleInUse = exampleInUse;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    @Override
    public String toString() {
        return "-  Ý nghĩa: " + meaning + "\n" +
                "-  Từ loại: " + wordType + "\n" +
                "-  Phát âm: " + pronunciation + "\n" +
                "-  Ví dụ: " + exampleInUse;
    }
}

