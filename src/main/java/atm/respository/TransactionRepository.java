package atm.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import atm.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    
}
