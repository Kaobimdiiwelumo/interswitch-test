package com.example.INTERSWITCH.ENTITY;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Title is required")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title must contain only letters and numbers")
        private String title;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Genre genre;

        @NotBlank(message = "ISBN is required")
        @Pattern(regexp = "^[0-9-]+$", message = "ISBN must contain only numbers and dashes")
        private String isbn;

        @NotBlank(message = "Author is required")
        private String author;

        private int yearOfPublication;

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

