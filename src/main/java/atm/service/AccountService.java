package atm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import atm.entity.Account;
import atm.respository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;        
    }

    public List<Account> getAll() {
        return accountRepo.findAll();
    }
}
