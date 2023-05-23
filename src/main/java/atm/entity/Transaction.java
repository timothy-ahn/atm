package atm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private OperationType operation;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(updatable = false)
    private LocalDateTime transactionDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public enum OperationType {
        DEBIT,
        CREDIT
    }

    public enum TransactionStatus {
        IN_PROCESS,
        COMMITED,
        BALANCE_UPDATED        
    }

    public enum TransactionType {
        WITHDRAWAL,
        DEPOSIT,
        TRANSFER,
        PAYMENT,
        LOAN,
        COMMISION,
        REFUND
    }
}
