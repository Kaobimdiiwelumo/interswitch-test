package com.example.INTERSWITCH.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseHistoryDTO {
    private LocalDateTime purchaseDate;
    private List<PurchasedBookDTO> purchasedBooks;

    public PurchaseHistoryDTO(LocalDateTime purchaseDate, List<PurchasedBookDTO> purchasedBooks) {
        this.purchaseDate = purchaseDate;
        this.purchasedBooks = purchasedBooks;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<PurchasedBookDTO> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(List<PurchasedBookDTO> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }
}
