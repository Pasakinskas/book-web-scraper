package com.pasakinskas;

public class Book extends BookListing {
    private String title;
    private String author;
    private String genre;
    private int noOfPages;
    private boolean isHardcover;
    private String ISBN;

    public Book(double price, int discount, String link, String title, String author,
                String genre, int noOfPages, boolean isHardcover, String ISBN) {
        super(price, discount, title, link);
        this.author = author;
        this.genre = genre;
        this.noOfPages = noOfPages;
        this.isHardcover = isHardcover;
        this.ISBN = ISBN;
    }

    public String toString() {
        return "Title: " + title + ";\n" +
                "Author: " + author + ";\n" +
                "genre: " + genre + ";\n" +
                "Number of pages: " + noOfPages + ";\n" +
                "Is hardcover: " + isHardcover + ";\n" +
                "ISBN: " + ISBN + ";\n";
    }
}
