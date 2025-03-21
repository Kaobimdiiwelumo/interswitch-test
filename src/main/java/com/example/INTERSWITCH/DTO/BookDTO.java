package com.example.INTERSWITCH.DTO;

import com.example.INTERSWITCH.ENTITY.Genre;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private Genre genre;
    private String isbn;
    private String author;
    private int yearOfPublication;

    public BookDTO(Long id, String title, Genre genre, String isbn, String author, int yearOfPublication){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
