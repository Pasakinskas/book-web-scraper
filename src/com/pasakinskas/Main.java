package com.pasakinskas;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        init();
    }

    public static void init() throws IOException {
        Document doc = Scraper.scrapeBooks();
        Elements scrapedElements = doc.select("div.product-in-list");
        ArrayList<BookListing> bookListings = BookListing.getBookListings(scrapedElements);

        int booksSelected = 0;
        for (BookListing bookListing : bookListings) {
            if (booksSelected >= 3) {
                break;
            }
            if (bookListing.getPrice() < 15 && bookListing.getDiscount() > 20) {
                Book book = bookListing.toBook();
                System.out.println(book);
                booksSelected++;
            }
        }
    }
}
