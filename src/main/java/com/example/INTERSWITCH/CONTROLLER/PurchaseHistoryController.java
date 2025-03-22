package com.example.INTERSWITCH.CONTROLLER;

import com.example.INTERSWITCH.DTO.PurchaseHistoryDTO;
import com.example.INTERSWITCH.DTO.PurchasedBookDTO;
import com.example.INTERSWITCH.ENTITY.PurchaseHistory;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.SERVICE.PurchaseHistoryService;
import com.example.INTERSWITCH.REPOSITORY.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchase-history")
public class PurchaseHistoryController {

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @Autowired
    private UserRepository userRepository;

    @Operation(
            summary = "Retrieve purchase history",
            description = "Allows a logged-in user to view their purchase history."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved purchase history"),
            @ApiResponse(responseCode = "401", description = "User not logged in"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping
    public ResponseEntity<?> getPurchaseHistory(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to view purchase history.");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        List<PurchaseHistory> purchaseHistories = purchaseHistoryService.getPurchaseHistoryByUser(user);
        List<PurchaseHistoryDTO> historyDTOs = purchaseHistories.stream().map(history ->
                new PurchaseHistoryDTO(
                        history.getPurchaseDate(),
                        history.getPurchasedBooks().stream()
                                .map(book -> new PurchasedBookDTO(book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity()))
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(historyDTOs);
    }
}
