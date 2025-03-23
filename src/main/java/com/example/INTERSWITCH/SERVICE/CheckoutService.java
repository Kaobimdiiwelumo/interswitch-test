package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.DTO.CheckoutDTO;
import com.example.INTERSWITCH.ENTITY.*;
import com.example.INTERSWITCH.REPOSITORY.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    private static final double BOOK_PRICE = 10.00;

    public String checkout(User user, String paymentMethod) {
        List<Cart> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            return "Cart is empty. Cannot proceed with checkout.";
        }

        // Simulate payment process
        String paymentStatus = simulatePayment(paymentMethod);
        if (!"SUCCESS".equals(paymentStatus)) {
            return "Payment failed. Try again.";
        }

        // Move items to purchase history
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUser(user);
        purchaseHistory.setPurchaseDate(LocalDateTime.now());
        purchaseHistoryRepository.save(purchaseHistory);

        if (purchaseHistory.getPurchasedBooks() == null) {
            purchaseHistory.setPurchasedBooks(new ArrayList<>());
        }

        for (Cart cart : cartItems) {
            PurchasedBook purchasedBook = new PurchasedBook();
            purchasedBook.setTitle(cart.getBook().getTitle());
            purchasedBook.setAuthor(cart.getBook().getAuthor());
            purchasedBook.setPrice(BOOK_PRICE);
            purchasedBook.setQuantity(cart.getQuantity());
            purchasedBook.setPurchaseHistory(purchaseHistory);
            purchaseHistory.getPurchasedBooks().add(purchasedBook);
        }

        // Clear cart after successful checkout
        cartRepository.deleteAll(cartItems);
        return "Checkout successful!";
    }

    private String simulatePayment(String paymentMethod) {
        switch (paymentMethod.toUpperCase()) {
            case "WEB":
            case "USSD":
            case "TRANSFER":
                return "SUCCESS";
            default:
                return "FAILED";
        }
    }
}


