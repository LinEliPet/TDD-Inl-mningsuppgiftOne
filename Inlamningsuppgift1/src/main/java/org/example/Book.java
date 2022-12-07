package org.example;

import java.util.ArrayList;

public class Book {
        private String title;
        private String author;
        private String date;
        private String genre;
        private ArrayList<Integer> grade;
        private ArrayList<String> comment;
        private boolean stock;
        private int price;

        //Constructor
        public Book(String title, String author, String date, String genre, ArrayList<Integer> grade, ArrayList<String> comment, boolean stock, int price) {
            this.title = title;
            this.author = author;
            this.date = date;
            this.genre = genre;
            this.grade = grade;
            this.comment = comment;
            this.stock = stock;
            this.price = price;
        }

        //Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public ArrayList<Integer> getGrade() {
            return grade;
        }

        public void setGrade(ArrayList grade) {
            this.grade = grade;
        }

        public ArrayList getComment() {
            return comment;
        }

        public void setComment(ArrayList comment) {
            this.comment = comment;
        }

        public boolean getStock() {
            return stock;
        }

        public void setStock(boolean stock) {
            this.stock = stock;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
