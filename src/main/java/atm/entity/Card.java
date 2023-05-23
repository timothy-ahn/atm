package atm.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private int cardId;

    private int customerId;
    private String cardNum;
    private LocalDate expiryDate;
    private String cardHolder;
    private short cvv;
    private short pin;
    private boolean isBlocked;
    private Provider provider;
    private boolean isVirtual;
    private BigDecimal serviceFee;
    private short pinFails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Customer customer;
    private List<Account> accounts = new ArrayList<>();

    public enum Provider {
        VISA,
        MASTERCARD
    }
}
