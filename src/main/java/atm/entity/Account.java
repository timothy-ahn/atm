package atm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, updatable = false, unique = true)
    private String iban;
    @Column(length = 3, updatable = false)
    private String currency;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AccountType type;
    private BigDecimal interestRate;

    private boolean isBlocked;
    @Column(updatable = false)
    private LocalDateTime openedAt;
    @Column(updatable = false)
    private LocalDateTime closedAt;
    private LocalDateTime closesAt;
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToMany(mappedBy = "accounts")
    private List<Card> cards = new ArrayList<>();

    public enum AccountType {
        CHECKING,
        SAVING
    }
}
