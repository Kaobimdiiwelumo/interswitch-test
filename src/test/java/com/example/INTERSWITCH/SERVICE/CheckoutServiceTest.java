package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.ENTITY.Book;
import com.example.INTERSWITCH.ENTITY.Cart;
import com.example.INTERSWITCH.ENTITY.Genre;
import com.example.INTERSWITCH.ENTITY.PurchaseHistory;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.CartRepository;
import com.example.INTERSWITCH.REPOSITORY.PurchaseHistoryRepository;
import com.example.INTERSWITCH.REPOSITORY.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CheckoutService checkoutService;

    private User sampleUser;
    private Book sampleBook;
    private Cart sampleCart;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setUsername("testuser");

        sampleBook = new Book();
        sampleBook.setId(10L);
        sampleBook.setTitle("Test Book");
        sampleBook.setAuthor("Test Author");
        sampleBook.setGenre(Genre.FICTION);
        sampleBook.setIsbn("123-456");
        sampleBook.setYearOfPublication(2020);

        sampleCart = new Cart(sampleUser, sampleBook, 1);
        sampleCart.setId(100L);
    }

    @Test
    void testCheckout_EmptyCart() {
        when(cartRepository.findByUser(sampleUser)).thenReturn(Collections.emptyList());
        String result = checkoutService.checkout(sampleUser, "WEB");
        assertEquals("Cart is empty. Cannot proceed with checkout.", result);
    }

    @Test
    void testCheckout_PaymentFailed() {
        when(cartRepository.findByUser(sampleUser)).thenReturn(List.of(sampleCart));
        String result = checkoutService.checkout(sampleUser, "INVALID");
        assertEquals("Payment failed. Try again.", result);
    }

    @Test
    void testCheckout_Success() {
        when(cartRepository.findByUser(sampleUser)).thenReturn(List.of(sampleCart));
        // Prepare a PurchaseHistory object with an initialized purchasedBooks list
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUser(sampleUser);
        purchaseHistory.setPurchaseDate(LocalDateTime.now());
        purchaseHistory.setPurchasedBooks(new ArrayList<>());
        when(purchaseHistoryRepository.save(any(PurchaseHistory.class))).thenReturn(purchaseHistory);

        String result = checkoutService.checkout(sampleUser, "WEB");
        assertEquals("Checkout successful!", result);
        verify(cartRepository, times(1)).deleteAll(anyList());
    }
}
