package atm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import atm.entity.AccountPlan;
import atm.respository.AccountPlanRepository;

@Service
public class AccountPlanService {
    private final AccountPlanRepository accountPlanRepo;

    public AccountPlanService(AccountPlanRepository accountPlanRepo) {
        this.accountPlanRepo = accountPlanRepo;
    }

    public List<AccountPlan> getAll() { 
        return accountPlanRepo.findAll();
    }

    public void createAccountPlan(AccountPlan accountPlan) {
        accountPlanRepo.save(accountPlan);
    }
}
