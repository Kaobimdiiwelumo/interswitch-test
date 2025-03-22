package com.example.INTERSWITCH.REPOSITORY;

import com.example.INTERSWITCH.ENTITY.PurchaseHistory;
import com.example.INTERSWITCH.ENTITY.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    List<PurchaseHistory> findByUser(User user);
}
