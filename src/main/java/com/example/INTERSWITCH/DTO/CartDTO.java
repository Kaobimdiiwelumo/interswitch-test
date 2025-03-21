package com.example.INTERSWITCH.DTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

public class CartDTO {
    private Long id;
    private BookDTO book;
    private int quantity;

    public CartDTO(Long id,BookDTO book, int quantity){
        this.id = id;
        this.book = book;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
