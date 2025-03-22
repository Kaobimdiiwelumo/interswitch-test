package com.example.INTERSWITCH.ENTITY;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime purchaseDate;

    @OneToMany(mappedBy = "purchaseHistory", cascade = CascadeType.ALL)
    private List<PurchasedBook> purchasedBooks = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<PurchasedBook> getPurchasedBooks() {
        if (purchasedBooks == null) {
            purchasedBooks = new ArrayList<>();
        }
        return purchasedBooks;
    }

    public void setPurchasedBooks(List<PurchasedBook> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }
}
