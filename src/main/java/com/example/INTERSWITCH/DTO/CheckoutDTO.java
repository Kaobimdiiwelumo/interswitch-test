package com.example.INTERSWITCH.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
    private Long userId;
    private String paymentMethod;

    public Long getUserId() {
        return userId;
    }
    public CheckoutDTO(Long userId, String paymentMethod) {
        this.userId = userId;
        this.paymentMethod = paymentMethod;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }




    }

