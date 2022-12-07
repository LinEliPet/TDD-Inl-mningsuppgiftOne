package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryTest {
    private Book book;
    private BookingRequest bookingRequest;
    private PaymentService paymentService;
    private SearchingService searchingService;
    private CommentService commentService;
    private BooksDatabase booksDatabase;
    private Library library;

    @BeforeEach
    public void setUp() {
        bookingRequest = mock(BookingRequest.class);
        commentService = mock(CommentService.class);
        paymentService = spy(PaymentService.class);
        searchingService = spy(SearchingService.class);
        booksDatabase = mock(BooksDatabase.class);
        library = new Library(
                bookingRequest,
                commentService,
                paymentService,
                searchingService, booksDatabase);

        //BeforeEach Library hämtas, hämtas även book1-book4 för mock/test
            Book book1= new Book(
                    "In search of lost time",
                    "Marcel Proust",
                    "Nittonhundratretton",
                    "Thriller",
                    new ArrayList<Integer>(Arrays.asList(10,9,5,1)),
                    new ArrayList<String>(),
                    true,
                    1);

            Book book2 = new Book(
                    "Jurassic parking",
                    "T-rex",
                    "A long long time ago",
                    "Drama",
                    new ArrayList<Integer>(Arrays.asList(10, 9)), //Bokens betyg
                    new ArrayList<String>(),
                    false, //Finns ej tillgänglig för att lånas
                    5);

            Book book3 = new Book(
                    "A book",
                    "An author",
                    "Once upon a time",
                    "Romance",
                    new ArrayList<Integer>(Arrays.asList(0,1)),
                    new ArrayList<String>(),
                    true,
                    4);

            Book book4 = new Book(
                    "Bear Grylls: How to survive everything",
                    "Bear Grylls",
                    "2010",
                    "Selfhelp",
                    new ArrayList<Integer>(Arrays.asList(8,6,9)),
                    new ArrayList<String>(),
                    true,
                    2);

            ArrayList<Book> books = new ArrayList<>();
            books.add(book1);
            books.add(book2);
            books.add(book3);
            books.add(book4);
            when(booksDatabase.getBooks()).thenReturn(books);

    }

        /*
    Jag vill att ni, användandes av Testdriven Utveckling, skall testa er väg fram till att
utveckla datorsystemet till ett bibliotek. Jag vill att ni skapar så att det finns ett
visst antal böcker i programmet som kan lånas till kunder i utbyte mot betalning.
Dessa kunder kan sedan ge betyg och lämna kommentarer på böckerna de läser.
Om en kund letar efter en särskild bok skall de kunna söka efter den via en del av
bokens namn via en sök-funktion, och de skall också söka via genre, författare,
utgivningsdatum eller efter de som fått högst betyg av andra läsare. Programmet
skall hålla koll på om böcker redan är utlånade och inte låta ej existerande
böcker bli lånade, etc.
     */

    @Test
    public void bookExistToBeBorrowedForPayment(){
        Book book1= new Book(
                "In search of lost time",
                "Marcel Proust",
                "Nittonhundratretton",
                "Thriller",
                new ArrayList<Integer>(Arrays.asList(10,9,5,1)),
                new ArrayList<String>(),
                true,
                1);
        library.payForBorrowIfIsInStock(book1);
        verify(paymentService, times(1)).pay(anyInt());
    }

    @Test
    public void bookExistToBeBorrowed(){
        Book book1= new Book(
                "In search of lost time",
                "Marcel Proust",
                "Nittonhundratretton",
                "Thriller",
                new ArrayList<Integer>(Arrays.asList(10,9,5,1)),
                new ArrayList<String>(),
                true,
                1);
       Book bug = library.borrowBook(book1);
        assertFalse(bug.getStock());
    }

    @Test
    public void bookDoesNotExist_ShouldThrowAnException(){
        Book book1= new Book(
                "In search of lost time",
                "Marcel Proust",
                "Nittonhundratretton",
                "Thriller",
                new ArrayList<Integer>(Arrays.asList(10,9,5,1)),
                new ArrayList<String>(),
                false,
                1);
        Executable bug = ()-> library.borrowBook(book1);
        assertThrows(IllegalStateException.class,bug);
    }

    @Test
    public void highestBookGrade(){
        Book actual = library.checkHighestGrade();
        assertEquals(booksDatabase.getBooks().get(1),  actual);
    }

    @Test
    public void ableToLeaveCommentOnBook(){
        String leavingComment = commentService.leaveComment();
        System.out.println(leavingComment);
    }

    @Test
    public void getBookByIndex(){
        Book actual = library.getBooksDatabase().getBooks().get(0);
        assertEquals("In search of lost time", actual.getTitle());
    }

    @Test
    public void getBookByTitle(){
        Book actual = library.getSearchingService().searchForBook("In search of lost time", library.getBooksDatabase().getBooks());
        assertEquals("In search of lost time", actual.getTitle());
    }

    @Test
    public void getBookByAuthor(){
        Book actual = library.getSearchingService().searchForBook("Bear Grylls: How to survive everything", library.getBooksDatabase().getBooks());
        assertEquals("Bear Grylls", actual.getAuthor());
    }

    @Test
    public void getBookByGenre(){
        Book actual = library.getSearchingService().searchForBook("Jurassic parking", library.getBooksDatabase().getBooks());
        assertEquals("Drama", actual.getGenre());
    }

}
