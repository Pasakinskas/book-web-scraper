package com.pasakinskas;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        init(5, 10, 10, "discountdesc");
    }

    public static void init(int booksToSelect, int discount, int price, String sortType) throws IOException {
        Document doc = Scraper.scrapeBooks(sortType);
        Elements scrapedElements = doc.select("div.product-in-list");
        ArrayList<BookListing> bookListings = BookListing.getBookListings(scrapedElements);

        int booksSelected = 0;
        for (BookListing bookListing : bookListings) {
            if (booksSelected >= booksToSelect) {
                break;
            }
            if (bookListing.getPrice() < price && bookListing.getDiscount() > discount) {
                Book book = bookListing.toBook();
                System.out.println(book);
                booksSelected++;
            }
        }
    }
}
