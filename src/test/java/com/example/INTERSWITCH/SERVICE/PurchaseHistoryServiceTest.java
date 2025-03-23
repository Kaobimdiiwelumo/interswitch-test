package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.ENTITY.PurchaseHistory;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.PurchaseHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseHistoryServiceTest {

    @Mock
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @InjectMocks
    private PurchaseHistoryService purchaseHistoryService;

    private User sampleUser;
    private PurchaseHistory history1;
    private PurchaseHistory history2;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setUsername("testuser");

        history1 = new PurchaseHistory();
        history1.setId(1L);
        history1.setUser(sampleUser);
        history1.setPurchaseDate(LocalDateTime.now());
        history1.setPurchasedBooks(new ArrayList<>());

        history2 = new PurchaseHistory();
        history2.setId(2L);
        history2.setUser(sampleUser);
        history2.setPurchaseDate(LocalDateTime.now());
        history2.setPurchasedBooks(new ArrayList<>());
    }

    @Test
    void getPurchaseHistoryByUser() {
        when(purchaseHistoryRepository.findByUser(sampleUser))
                .thenReturn(Arrays.asList(history1, history2));

        List<PurchaseHistory> result = purchaseHistoryService.getPurchaseHistoryByUser(sampleUser);
        assertEquals(2, result.size(), "Purchase history list should contain 2 records.");
    }
}
