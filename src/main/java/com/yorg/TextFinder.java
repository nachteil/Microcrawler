package com.yorg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextFinder implements Finder {

    private final String[] sentences;

    public TextFinder(Webpage webpage) {
        String pageText = webpage.getBodyText();
        sentences = pageText.split("\\.");
    }

    @Override
    public Collection<String> search(Collection<String> templates) {
        List<String> result = new ArrayList<>();
        for(String sentence : sentences) {
            for(String wantedWord : templates) {
                if(sentence.contains(wantedWord)) {
                    result.add(sentence);
                    break; // TODO: breaks're ugly, get rid of it
                }
            }
        }
        return result;
    }
}
