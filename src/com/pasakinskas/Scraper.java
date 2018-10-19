package com.pasakinskas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Class is used to scrape the website www.knygos.lt. The method scrapeBooks()
 * scrapes a document with a list of newest books, ordered by discount. This
 * list is very light on information: book's name, author, price and discount.
 * <p>
 * The method scrapeDetailedBook returns a complete book page that contains enough
 * information about the book to form an object of the Book class.
 */

public class Scraper {

    // Method gets books from the newest category, sorted by discount
    public static Document scrapeBooks(String sortType) throws IOException {
        Document doc = Jsoup.connect("https://www.knygos.lt/lt/knygos/naujausios/")
                .data("form_orderlist", sortType)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
                .post();

        return doc;
    }


    public static Document scrapeDetailedBook(String bookUrl) throws IOException {
        String url = "https://www.knygos.lt/" + bookUrl;
        return Jsoup.connect(url).get();
    }
}
