package com.example.INTERSWITCH.REPOSITORY;

import com.example.INTERSWITCH.ENTITY.Book;
import com.example.INTERSWITCH.ENTITY.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByYearOfPublication(int year);
    List<Book> findByGenre(Genre genre);
}
