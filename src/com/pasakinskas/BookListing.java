package com.pasakinskas;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The BookListing class is meant to structure data scraped
 * from the website knygos.lt using the Scraper class.
 * This is done to filter out which listed books might be
 * Interesting or attractive based on their price and/or the
 * discount.
 */

public class BookListing {
    private double price;
    private int discount;
    private String title;
    private String link;

    public BookListing(double price, int discount, String title, String link) {
        this.price = price;
        this.discount = discount;
        this.title = title;
        this.link = link;
    }

    public String toString() {
        return "[price: " + price + "; discount: " + discount + "; title: " + title + "; link: " + link + "]";
    }

    public static ArrayList<BookListing> getBookListings(Elements elements) {
        ArrayList<BookListing> bookListings = new ArrayList<BookListing>();
        for (Element element : elements) {
            bookListings.add(new BookListing(
                    priceToDouble(element.select("p.main-price > span.value").text()),
                    discountToInt(element.select("div.discount-badge__content").text()),
                    element.select("p.product-title-in-list > a").text(),
                    element.select("p.product-title-in-list > a").attr("href")
            ));
        }
        return bookListings;
    }

    private static int discountToInt(String discount) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(discount);
        matcher.find();
        return Integer.parseInt(matcher.group());
    }

    private static double priceToDouble(String price) {
        Pattern pattern = Pattern.compile("\\d+,\\d+");
        Matcher matcher = pattern.matcher(price);
        matcher.find();
        return Double.parseDouble(matcher.group().replaceAll(",", "."));
    }

    public Book toBook() throws IOException {
        Document doc = Scraper.scrapeDetailedBook(link);

        String author = doc.select("p.book_details > a").text();
        String genre = doc.select("ul.link-list > li").first().text();
        int noOfPages = Integer.parseInt(doc.select("p.book_details > span[itemprop=numberOfPages]").text());
        boolean isHardcover = doc.select("p.book_details").toString().contains("kieti");
        String ISBN = doc.select("p.book_details > span[itemprop=isbn]").text();

        return new Book(this.price, this.discount, this.link, this.title,
                author, genre, noOfPages, isHardcover, ISBN);
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}
