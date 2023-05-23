package atm.entity;


import lombok.Data;

@Data
public class CardAccount {
    private final int cardId;
    private final int accountId;

    private final Card card;
    private final Account account;
}
