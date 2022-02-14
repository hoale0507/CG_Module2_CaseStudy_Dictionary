package com.company.model;

import com.company.controller.ReadFile;
import com.company.controller.WriteToFile;

import java.io.*;
import java.util.*;

public class Dictionary implements Serializable, WriteToFile, ReadFile {
    private Map <String, Explanation> dictionary = new HashMap<String, Explanation>();

    public Dictionary() {
    }

    public Dictionary(Map<String, Explanation> data) {
        this.dictionary = data;
    }

    public Map<String, Explanation> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, Explanation> dictionary) {
        this.dictionary = dictionary;
    }

    public Explanation searchWord(String word){
        return dictionary.get(word);
    }

    public void printWordList(){
        Set<String> words = dictionary.keySet();
        ArrayList<String> wordsArray = new ArrayList<>(words);
//        Collections.sort(wordsArray);
        // Sắp xếp nổi bọt trước khi in
        for (int i = 0; i < wordsArray.size(); i++) {
            for (int j = 0; j < wordsArray.size()-i-1; j++) {
                if(wordsArray.get(j).compareTo(wordsArray.get(j+1)) > 0){
                    String temp = wordsArray.get(j);
                    wordsArray.set(j, wordsArray.get(j+1));
                    wordsArray.set(j+1, temp);
                }
            }
        }
        for (int i = 0; i < wordsArray.size(); i++) {
            System.out.println((i+1)+". "+wordsArray.get(i));
        }
//        for (String word:
//             wordsArray) {
//            System.out.println(word);
//        }
    }

    public int numberOfWord(){
        return dictionary.size();
    }

    public boolean isExitedWord(String word){
        if(dictionary.containsKey(word)){
            return true;
        }
        return false;
    }


    @Override
    public void writeToFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(dictionary);
        oos.close();
        os.close();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        if(file.exists()){
            InputStream is = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(is);
            dictionary = (HashMap<String, Explanation>) ois.readObject();
        }
    }
}
