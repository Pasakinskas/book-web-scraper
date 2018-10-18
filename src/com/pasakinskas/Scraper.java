package com.pasakinskas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Class is used to scrape the website www.knygos.lt. The method scrapeBooks()
 * scrapes a document with a list of newest books, ordered by discount. This
 * list is very light on information: book's name, author, price and discount.
 *
 * The method scrapeDetailedBook returns a complete book page that contains enough
 * information about the book to form an object of the Book class.
 */
public class Scraper {

    // Method gets books from the newest category, sorted by discount
    public static Document scrapeBooks() throws IOException {
        Document doc = Jsoup.connect("https://www.knygos.lt/lt/knygos/naujausios/")
                .data("form_orderlist", "discountdesc")
                .post();

        return doc;
    }

    public static Document scrapeDetailedBook(String bookUrl) throws IOException {
        String url = "https://www.knygos.lt/" + bookUrl;
        return Jsoup.connect(url).get();
    }
}
