package com.pasakinskas;

public class Book {
    private String title;
    private String author;
    private String genre;
    private int noOfPages;
    private boolean isHardcover;
    private String ISBN;

    public Book(String title, String author, String genre,
                int noOfPages, boolean isHardcover, String ISBN) {
        this.title = title;
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
                "Hardcover?: " + isHardcover + ";\n" +
                "ISBN: " + ISBN + ";\n";
    }
}
