package com.example.INTERSWITCH.SERVICE;
import com.example.INTERSWITCH.DTO.BookDTO;
import com.example.INTERSWITCH.ENTITY.Book;
import com.example.INTERSWITCH.ENTITY.Genre;
import com.example.INTERSWITCH.REPOSITORY.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Convert Book entity to BookDTO
    private BookDTO convertToDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getIsbn(),
                book.getAuthor(),
                book.getYearOfPublication()
        );
    }

    // Retrieve all books
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Search books by title
    public List<BookDTO> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Search books by author
    public List<BookDTO> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Search books by year
    public List<BookDTO> searchByYear(int year) {
        return bookRepository.findByYearOfPublication(year)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Search books by genre
    public List<BookDTO> searchByGenre(Genre genre) {
        return bookRepository.findByGenre(genre)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
