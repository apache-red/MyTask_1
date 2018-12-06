package com.redcompany.red.start;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    //this task use GUAVA! add GUAVA from the lib folder to the project

    public static final String FILEPATH = "./src/com/redcompany/red/inputfiles/File.txt";
    private Multiset<Word> wordList = HashMultiset.create();

    public static void main(String[] args) {
        Main main = new Main();
        Multiset multiset = main.readFromFile();
        main.showListDuplicates(multiset);
    }

    private Multiset<Word> readFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader((FILEPATH)))) {
            String line;
            int n = 1;
            while ((line = reader.readLine()) != null) {
                String[] strArray = line.replaceAll("[^a-zA-Z+ ]", " ").toLowerCase().split("\\s+");
                for (int i = 0; i < strArray.length; i++) {
                    wordList.add(new Word(strArray[i]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was no found!");
        } catch (IOException e) {
            System.out.println("Problems with the file");
        }
        return wordList;
    }

    private void showListDuplicates(Multiset<Word> showlist) {
        int numberOfDisplayedResults = 20;
        int count = 1;
        Multiset<Word> histogram = showlist;
        for (Word word : Multisets.copyHighestCountFirst(histogram).elementSet()) {
            if (count <= numberOfDisplayedResults) {
                System.out.println(word + "= " + histogram.count(word));
            } else {
                break;
            }
            count++;
        }
    }

}

class Word {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Number of occurrences of the word {" +
                word + "} in the text" +
                '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}


