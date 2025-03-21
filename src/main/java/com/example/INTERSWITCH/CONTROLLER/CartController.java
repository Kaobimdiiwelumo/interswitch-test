package com.example.INTERSWITCH.CONTROLLER;

import com.example.INTERSWITCH.DTO.CartDTO;
import com.example.INTERSWITCH.SERVICE.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartDTO addToCart(@RequestParam Long bookId, @RequestParam int quantity, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // Get logged-in user from session
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in to view the cart.", null);
        }
        return cartService.addToCart(userId, bookId, quantity);
    }


    @GetMapping("/view")
    public List<CartDTO> viewCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        System.out.println("Logged in User ID: " + userId);
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in to view the cart.", null);
        }
        return cartService.viewCart(session);
    }
}
