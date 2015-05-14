package com.yorg;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class Webpage implements Iterable<Webpage> {

    private String webpageBody;

    private Set<String> visitedUrls = new HashSet<>();

    Queue<String> urls;
    String mainUrl;

    public Webpage(String url) {

        this.mainUrl = url;
        urls = new ArrayDeque<String>();
        urls.add(url);
    }

    @SneakyThrows
    public String getBodyHTML() {
        Document doc = Jsoup.connect(mainUrl).get();
        return doc.body().html();
    }

    @SneakyThrows
    public String getBodyText() {
        Document doc = Jsoup.connect(mainUrl).get();
        return doc.body().text();
    }

    @Override
    public Iterator<Webpage> iterator() {
        return new WebpageIterator();
    }


    private class WebpageIterator implements Iterator<Webpage> {

        @Override
        public boolean hasNext() {
            return !urls.isEmpty();
        }

        @Override
        @SneakyThrows
        public Webpage next() {

            String first = urls.remove();

            Document document = Jsoup.connect(first).get();

            Elements elements = document.select("a[href]");
            for(Element e : elements) {
                String nextUrl = e.attr("abs:href");
                if(visitedUrls.add(nextUrl)) {
                    urls.add(nextUrl);
                }
            }


            return new Webpage(first);
        }
    }
}
