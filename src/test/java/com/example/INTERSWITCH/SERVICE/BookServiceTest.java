package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.DTO.BookDTO;
import com.example.INTERSWITCH.ENTITY.Book;
import com.example.INTERSWITCH.ENTITY.Genre;
import com.example.INTERSWITCH.REPOSITORY.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book mockBook1;
    private Book mockBook2;

    @BeforeEach
    void setUp() {
        // Create sample Book entities
        mockBook1 = new Book();
        mockBook1.setId(1L);
        mockBook1.setTitle("Mock Title One");
        mockBook1.setGenre(Genre.FICTION);
        mockBook1.setIsbn("123-456");
        mockBook1.setAuthor("Author One");
        mockBook1.setYearOfPublication(2001);

        mockBook2 = new Book();
        mockBook2.setId(2L);
        mockBook2.setTitle("Mock Title Two");
        mockBook2.setGenre(Genre.HORROR);
        mockBook2.setIsbn("789-012");
        mockBook2.setAuthor("Author Two");
        mockBook2.setYearOfPublication(2002);
    }

    @Test
    void getAllBooks() {
        // Arrange
        List<Book> mockBooks = Arrays.asList(mockBook1, mockBook2);
        when(bookRepository.findAll()).thenReturn(mockBooks);

        // Act
        List<BookDTO> result = bookService.getAllBooks();

        // Assert
        assertEquals(2, result.size(), "Should return two books");
        assertEquals("Mock Title One", result.get(0).getTitle(), "First book title should match");
        assertEquals("Mock Title Two", result.get(1).getTitle(), "Second book title should match");
    }

    @Test
    void searchByTitle() {
        when(bookRepository.findByTitleContainingIgnoreCase(anyString()))
                .thenReturn(Arrays.asList(mockBook1));

        List<BookDTO> result = bookService.searchByTitle("Title");
        assertEquals(1, result.size(), "Should return one book for title search");
        assertEquals("Mock Title One", result.get(0).getTitle());
    }

    @Test
    void searchByAuthor() {
        when(bookRepository.findByAuthorContainingIgnoreCase(anyString()))
                .thenReturn(Arrays.asList(mockBook2));

        List<BookDTO> result = bookService.searchByAuthor("Author Two");
        assertEquals(1, result.size(), "Should return one book for author search");
        assertEquals("Mock Title Two", result.get(0).getTitle());
    }

    @Test
    void searchByYear() {
        when(bookRepository.findByYearOfPublication(2001))
                .thenReturn(Arrays.asList(mockBook1));

        List<BookDTO> result = bookService.searchByYear(2001);
        assertEquals(1, result.size(), "Should return one book for year search");
        assertEquals(2001, result.get(0).getYearOfPublication());
    }

    @Test
    void searchByGenre() {
        when(bookRepository.findByGenre(Genre.FICTION))
                .thenReturn(Arrays.asList(mockBook1));

        List<BookDTO> result = bookService.searchByGenre(Genre.FICTION);
        assertEquals(1, result.size(), "Should return one book for genre search");
        assertEquals(Genre.FICTION, result.get(0).getGenre());
    }
}
