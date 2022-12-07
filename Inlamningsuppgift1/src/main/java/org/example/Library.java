package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.mockito.Mockito.when;

public class Library {
    private BookingRequest bookingRequest;
    private PaymentService paymentService;
    private CommentService commentService;
    private SearchingService searchingService;
    private BooksDatabase booksDatabase;

    public Library(BookingRequest bookingRequest,
                   CommentService commentService,
                   PaymentService paymentService,
                   SearchingService searchingService,
                   BooksDatabase booksDatabase
    ) {
        this.bookingRequest = bookingRequest;
        this.commentService = commentService;
        this.paymentService = paymentService;
        this.searchingService = searchingService;
        this.booksDatabase = booksDatabase;
    }

    public BookingRequest getBookingRequest() {
        return bookingRequest;
    }


    public PaymentService getPaymentService() {
        return paymentService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public SearchingService getSearchingService() {
        return searchingService;
    }

    public void payForBorrowIfIsInStock(Book book) {
        if (book.getStock()) {
            paymentService.pay(100);

        }
    }

    public Book borrowBook(Book book) {
        if (book.getStock()) {
            book.setStock(false);
            return book;
        } else {
            throw new IllegalStateException();
        }
    }

    public BooksDatabase getBooksDatabase() {
        return booksDatabase;
    }

    public Book checkHighestGrade() {
        int highestRate = 0;
        int tempHighestRate = 0;
        Book bookWithHighestRate = null;
        for (int i = 0; i < booksDatabase.getBooks().size(); i++) {
            Book book = booksDatabase.getBooks().get(i);
            for (int j = 0; j < book.getGrade().size(); j++) {
                tempHighestRate += book.getGrade().get(j);
            }
            tempHighestRate = tempHighestRate / book.getGrade().size();
            if (tempHighestRate > highestRate) {
                highestRate = tempHighestRate;
                bookWithHighestRate = book;
            }
            tempHighestRate=0;

        }
        return bookWithHighestRate;
    }

}


