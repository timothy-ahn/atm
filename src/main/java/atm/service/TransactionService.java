package atm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import atm.entity.Transaction;
import atm.respository.TransactionRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<Transaction> getAll() {
        return transactionRepo.findAll();
    }
}
