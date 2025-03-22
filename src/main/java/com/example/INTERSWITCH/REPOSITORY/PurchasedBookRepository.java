package com.example.INTERSWITCH.REPOSITORY;

import com.example.INTERSWITCH.ENTITY.PurchasedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedBookRepository extends JpaRepository<PurchasedBook, Long> {
}
