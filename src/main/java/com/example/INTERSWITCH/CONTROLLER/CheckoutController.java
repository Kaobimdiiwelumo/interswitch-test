package com.example.INTERSWITCH.CONTROLLER;

import com.example.INTERSWITCH.SERVICE.CheckoutService;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private UserRepository userRepository;

    @Operation(
            summary = "Checkout books from the cart",
            description = "Allows a logged-in user to checkout books in their cart. Simulates payment using Web, USSD, or Transfer."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Checkout successful"),
            @ApiResponse(responseCode = "401", description = "User not logged in"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Payment failed")
    })
    @PostMapping
    public ResponseEntity<String> checkout(@RequestParam String paymentMethod, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to checkout.");
        }

        // Fetch the User entity from the database
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        String result = checkoutService.checkout(user, paymentMethod);
        return ResponseEntity.ok(result);
    }
}
