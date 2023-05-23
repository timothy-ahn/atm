package atm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import atm.entity.Account.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Column(length = 3)
    @Pattern(regexp = "^[A-Za-z]{3}$")
    private String currency;
    private double minBalance;
    private BigDecimal interestRate;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
