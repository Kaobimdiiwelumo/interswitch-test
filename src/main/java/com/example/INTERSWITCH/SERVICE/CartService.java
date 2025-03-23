package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.DTO.CartDTO;
import com.example.INTERSWITCH.DTO.BookDTO;
import com.example.INTERSWITCH.ENTITY.Book;
import com.example.INTERSWITCH.ENTITY.Cart;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.BookRepository;
import com.example.INTERSWITCH.REPOSITORY.CartRepository;
import com.example.INTERSWITCH.REPOSITORY.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartDTO> viewCart(HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in to view the cart.");
        }


        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid user.");
        }
        User user = userOptional.get();


        List<Cart> cartItems = cartRepository.findByUser(user);

        System.out.println("Retrieved Cart Items: " + cartItems);


        return cartItems.stream()
                .map(cartItem -> new CartDTO(
                        cartItem.getId(),
                        new BookDTO(
                                cartItem.getBook().getId(),
                                cartItem.getBook().getTitle(),
                                cartItem.getBook().getGenre(),
                                cartItem.getBook().getIsbn(),
                                cartItem.getBook().getAuthor(),
                                cartItem.getBook().getYearOfPublication()
                        ),
                        cartItem.getQuantity()
                ))
                .collect(Collectors.toList());
    }
    public CartDTO addToCart(Long userId, Long bookId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Cart cartItem = cartRepository.findByUserAndBook(user, book)
                .orElse(new Cart(user, book, 0));

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartRepository.save(cartItem);

        return new CartDTO(
                cartItem.getId(),
                new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getIsbn(),
                        book.getAuthor(),
                        book.getYearOfPublication()
                ),
                cartItem.getQuantity()
        );
    }
}
