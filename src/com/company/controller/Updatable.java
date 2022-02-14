package com.company.controller;

public interface Updatable {
    void updateMeaning(String word, String meaning);
    void updateWordType(String word, String wordType);
    void updatePronunciation(String word, String pronunciation);
    void updateExample(String word, String example);
}
