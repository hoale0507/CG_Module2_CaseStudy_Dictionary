package com.company.controller;

import com.company.model.Dictionary;
import com.company.model.Explanation;

public class DictionaryForAdmin extends Dictionary implements Manageable<Explanation>, Updatable{
    @Override
    public Explanation addWord(String word, Explanation explanation) {
        return super.getDictionary().put(word, explanation);
    }

    @Override
    public Explanation deleteWord(String word) {
        return super.getDictionary().remove(word);
    }

    @Override
    public void deleteAllWords() {
        super.getDictionary().clear();
    }

    @Override
    public void updateMeaning(String word, String meaning) {
        super.getDictionary().get(word).setMeaning(meaning);
    }

    @Override
    public void updateWordType(String word, String wordType) {
        super.getDictionary().get(word).setWordType(wordType);
    }

    @Override
    public void updatePronunciation(String word, String pronunciation) {
        super.getDictionary().get(word).setPronunciation(pronunciation);
    }

    @Override
    public void updateExample(String word, String example) {
        super.getDictionary().get(word).setExampleInUse(example);
    }


//    public Explanation addWord(String word, Explanation explanation) {
//        return super.getDictionary().put(word, explanation);
//    }
//
//    public Explanation deleteWord(String word) {
//        return super.getDictionary().remove(word);
//    }
}
