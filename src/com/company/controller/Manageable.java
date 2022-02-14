package com.company.controller;

import com.company.model.Explanation;

public interface Manageable<T> {
    T addWord(String word, Explanation explanation);
    T deleteWord(String word);
    void deleteAllWords();
}
