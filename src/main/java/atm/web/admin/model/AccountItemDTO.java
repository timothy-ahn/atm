package atm.web.admin.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AccountItemDTO {
    private int id;
    private String iban;
    private String currency;
    private BigDecimal balance;
    private String type;
    private BigDecimal interestRate;

    private boolean isBlocked;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private LocalDateTime closesAt;
    private LocalDateTime updatedAt;
}
