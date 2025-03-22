package com.example.INTERSWITCH.SERVICE;

import com.example.INTERSWITCH.ENTITY.PurchaseHistory;
import com.example.INTERSWITCH.ENTITY.User;
import com.example.INTERSWITCH.REPOSITORY.PurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryService {

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    public List<PurchaseHistory> getPurchaseHistoryByUser(User user) {
        return purchaseHistoryRepository.findByUser(user);
    }
}
