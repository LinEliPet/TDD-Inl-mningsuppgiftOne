package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class SearchingService {


    public Book searchForBook(String title, ArrayList<Book> books) {
        Book result = null;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (Objects.equals(book.getTitle(), title) || Objects.equals(book.getAuthor(), title) || Objects.equals(book.getDate(), title) || Objects.equals(book.getGenre(), title)) {
                result = book;
            }
        }
        System.out.println(result);
        return result;
    }


}