package com.company.controller;

import com.company.model.Dictionary;

import java.util.ArrayList;
import java.util.Set;

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

    @Override
    public ArrayList<String> randomWordList() {
        Set<String> words = super.getDictionary().keySet();
        ArrayList<String> wordsArray = new ArrayList<>(words);
        ArrayList<String> wordsOverview = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
                double randomDouble = Math.random();
                randomDouble = randomDouble * wordsArray.size() + 1;
                int randomInt = (int) randomDouble;
//                System.out.println("Random number is : " + randomInt);
            wordsOverview.add(wordsArray.get(randomInt-1));
        }
        return wordsOverview;
    }

    public String meaningFoundByWord(String word){
        for (int i = 0; i < super.getDictionary().size(); i++) {
            if(super.isExitedWord(word)){
                return super.getDictionary().get(word).getMeaning();
            }
        }
        return null;
    }
}
