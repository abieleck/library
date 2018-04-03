package com.kodilla.library.repository;

import com.kodilla.library.domain.Author;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    TitleRepository titleRepository;

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void shouldNotAddNewBookUnsavedDependent() {
        //Given
        Author author = new Author("Jan Kowalski");
        Title title = new Title("Maliny", author, 2016);
        Book book = new Book(title, BookStatus.IN_CIRCULATION);
        //When
        bookRepository.save(book);
        //Then - exception is thrown
    }

    @Test
    public void shouldAddNewBook() {
        //Given
        Author author = new Author("Jan Kowalski");
        authorRepository.save(author);
        Title title = new Title("Maliny", author, 2016);
        titleRepository.save(title);
        Book book = new Book(title, BookStatus.IN_CIRCULATION);
        long bookCount = bookRepository.count();
        //When
        bookRepository.save(book);
        //Then
        assertEquals(bookCount + 1, bookRepository.count());
        // Clean Up
        bookRepository.delete(book);
        titleRepository.delete(title);
        authorRepository.delete(author);
    }
}
