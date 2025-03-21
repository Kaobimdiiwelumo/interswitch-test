package com.example.INTERSWITCH.REPOSITORY;

import com.example.INTERSWITCH.ENTITY.Book;
import com.example.INTERSWITCH.ENTITY.Cart;
import com.example.INTERSWITCH.ENTITY.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c JOIN FETCH c.book WHERE c.user = :user")
    List<Cart> findByUser(@Param("user") User user);
    Optional<Cart> findByUserAndBook(User user, Book book);
}
