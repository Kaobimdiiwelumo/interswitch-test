package com.example.INTERSWITCH.CONTROLLER;

import com.example.INTERSWITCH.DTO.BookDTO;
import com.example.INTERSWITCH.ENTITY.Genre;
import com.example.INTERSWITCH.SERVICE.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Book Management", description = "Endpoints for retrieving and searching books")
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all books available in the inventory.")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Search books by title", description = "Finds books with a given title or partial title match.")
    public List<BookDTO> searchByTitle(@PathVariable String title) {
        return bookService.searchByTitle(title);
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "Search books by author", description = "Finds books written by a specific author.")
    public List<BookDTO> searchByAuthor(@PathVariable String author) {
        return bookService.searchByAuthor(author);
    }

    @GetMapping("/year/{year}")
    @Operation(summary = "Search books by year", description = "Finds books published in a specific year.")
    public List<BookDTO> searchByYear(@PathVariable int year) {
        return bookService.searchByYear(year);
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Search books by genre", description = "Finds books by their genre.")
    public List<BookDTO> searchByGenre(@PathVariable Genre genre) {
        return bookService.searchByGenre(genre);
    }
}
