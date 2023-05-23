package atm.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import atm.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
}
