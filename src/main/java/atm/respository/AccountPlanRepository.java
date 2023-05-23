package atm.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import atm.entity.AccountPlan;
import atm.entity.Account.AccountType;

public interface AccountPlanRepository extends JpaRepository<AccountPlan, Integer> {
    public List<AccountPlan> findByType(AccountType accountType);
}
