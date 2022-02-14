package com.company.controller;

import com.company.model.Dictionary;

public class DictionaryForUser extends Dictionary implements Usable{

    @Override
    public String searchPhrase(String phrase) {
        String[] wordsArray = phrase.split(" ");
        String result = "";
        for (int i = 0; i < wordsArray.length; i++) {
            if(super.isExitedWord(wordsArray[i])){
                wordsArray[i] = super.getDictionary().get(wordsArray[i]).getMeaning();
            } else {
                wordsArray[i] = "(" + wordsArray[i] + ")";
            }
            result += wordsArray[i] + " ";
        }
        return result;
    }

}
