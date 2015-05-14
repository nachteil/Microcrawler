package com.yorg;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collection;

public class Microcrawler {

    private String url;

    public Microcrawler(String startingUrl) {
        this.url = startingUrl;
    }

    public void printSentencesWithWords(Collection<String> wantedWords) {
        Webpage webpage = new Webpage(url);
        for(Webpage nextPage : webpage) {
            Finder finder = new TextFinder(nextPage);
            System.out.println(finder.search(wantedWords));
        }
    }

    public static void main(String[] args) {
        new Microcrawler("http://pl.wikipedia.org/wiki/Powstanie_styczniowe")
                .printSentencesWithWords(Arrays.asList("tylko"));
    }

}
