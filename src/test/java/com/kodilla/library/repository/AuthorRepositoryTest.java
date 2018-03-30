package com.kodilla.library.repository;

import com.kodilla.library.domain.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void shouldAddAuthorToDatabase() {
        //Given
        Author author = new Author("Jan Kowalski");
        long authorsCount = authorRepository.count();
        //When
        authorRepository.save(author);
        //Then
        assertNotNull(author.getId());
        assertEquals(authorsCount + 1, authorRepository.count());
        //Clean up
        authorRepository.delete(author);
    }

}
