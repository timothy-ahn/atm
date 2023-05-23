package atm.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import atm.entity.Account;
import atm.entity.AccountPlan;
import atm.entity.Customer;
import atm.entity.Transaction;
import atm.entity.Account.AccountType;
import atm.entity.Customer.DocumentType;
import atm.entity.Transaction.OperationType;
import atm.entity.Transaction.TransactionStatus;
import atm.entity.Transaction.TransactionType;
import atm.respository.AccountPlanRepository;
import atm.respository.AccountRepository;
import atm.respository.CustomerRepository;
import atm.respository.TransactionRepository;
import net.datafaker.Faker;

@Component
public class DataLoader implements ApplicationRunner {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRespository;
    private final AccountPlanRepository accountPlanRepository;

    public DataLoader(AccountRepository accountRepository, 
        CustomerRepository customerRepository, TransactionRepository transactionRespository,
        AccountPlanRepository accountPlanRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRespository = transactionRespository;
        this.accountPlanRepository = accountPlanRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Faker faker = new Faker(new Locale("ru"), new Random(999));    
        
        for(int i = 0; i < 5; i++) {            
            Customer customer = new Customer();
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
            customer.setSex(faker.number().numberBetween(0, 2) == 1 ? "Male" : "Female");
            customer.setCitizenship("KAZAKHSTAN");
            customer.setStateIsoCode("KAZ");
            customer.setBirthDate(faker.date().birthday(18, 68).toLocalDateTime().toLocalDate());
            customer.setResidenceAddress(faker.address().streetAddress(false));
            customer.setActualAddress(faker.address().streetAddress(false));
            customer.setEmail(faker.internet().emailAddress());
            customer.setPhoneNumber(faker.numerify("747#######"));
            customer.setDocType(DocumentType.PASSPORT);
            customer.setDocNum(String.format("N%s", faker.number().digits(8)));
            customer.setDocIssuer("MINISTRI OF KAZAKHSTAN");
            customer.setDocIssueDate(faker.date().past(3650, TimeUnit.DAYS).toLocalDateTime().toLocalDate());
            customer.setDocExpiryDate(customer.getDocIssueDate().plusYears(10));
            customer.setCreatedAt(LocalDateTime.now().minusDays(90));
            customer.setUpdatedAt(LocalDateTime.now().minusDays(90));
            
            Account kztAccount = new Account();
            kztAccount.setCustomer(customer);
            kztAccount.setIban(faker.numerify("KZ##################"));
            kztAccount.setCurrency("KZT");
            kztAccount.setType(AccountType.CHECKING);
            kztAccount.setInterestRate(BigDecimal.ZERO);
            kztAccount.setOpenedAt(LocalDateTime.now().minusDays(90));
            kztAccount.setUpdatedAt(LocalDateTime.now().minusDays(90));
            for (int j = 0; j < 10; j++) {
                Transaction transaction = new Transaction();
                transaction.setAmount(BigDecimal.valueOf((((int)faker.number().randomDouble(0, 1000, 1000000))) / 500 * 500 ));
                transaction.setOperation(OperationType.DEBIT);
                transaction.setStatus(TransactionStatus.BALANCE_UPDATED);
                transaction.setType(TransactionType.DEPOSIT);
                transaction.setTransactionDate(faker.date().past(10, TimeUnit.DAYS).toLocalDateTime());
                transaction.setAccount(kztAccount);
                kztAccount.getTransactions().add(transaction);
            }

            kztAccount.setBalance(kztAccount.getTransactions().stream()
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add));

            Account usdAccount = new Account();
            usdAccount.setCustomer(customer);
            usdAccount.setIban(faker.numerify("KZ##################"));
            usdAccount.setCurrency("USD");
            usdAccount.setType(AccountType.CHECKING);
            usdAccount.setInterestRate(BigDecimal.ZERO);
            usdAccount.setOpenedAt(LocalDateTime.now().minusDays(90));
            usdAccount.setUpdatedAt(LocalDateTime.now().minusDays(90));
            for (int j = 0; j < 10; j++) {
                Transaction transaction = new Transaction();
                transaction.setAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 100)));
                transaction.setOperation(OperationType.DEBIT);
                transaction.setStatus(TransactionStatus.BALANCE_UPDATED);
                transaction.setType(TransactionType.TRANSFER);
                transaction.setTransactionDate(faker.date().past(10, TimeUnit.DAYS).toLocalDateTime());
                transaction.setAccount(usdAccount);
                usdAccount.getTransactions().add(transaction);
            }

            usdAccount.setBalance(usdAccount.getTransactions().stream()
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add));

            Account eurAccount = new Account();
            eurAccount.setCustomer(customer);
            eurAccount.setIban(faker.numerify("KZ##################"));
            eurAccount.setCurrency("EUR");
            eurAccount.setType(AccountType.CHECKING);
            eurAccount.setInterestRate(BigDecimal.ZERO);
            eurAccount.setOpenedAt(LocalDateTime.now().minusDays(90));
            eurAccount.setUpdatedAt(LocalDateTime.now().minusDays(90));
            for (int j = 0; j < 10; j++) {
                Transaction transaction = new Transaction();
                transaction.setAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 1000)));
                transaction.setOperation(OperationType.DEBIT);
                transaction.setStatus(TransactionStatus.BALANCE_UPDATED);
                transaction.setType(TransactionType.TRANSFER);
                transaction.setTransactionDate(faker.date().past(10, TimeUnit.DAYS).toLocalDateTime());
                transaction.setAccount(eurAccount);
                eurAccount.getTransactions().add(transaction);
            }

            eurAccount.setBalance(eurAccount.getTransactions().stream()
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add));

            customer.setAccounts(List.of(kztAccount, usdAccount, eurAccount));

            customerRepository.save(customer);
            accountRepository.saveAll(customer.getAccounts());
            for (Account account : customer.getAccounts()) {
                transactionRespository.saveAll(account.getTransactions());
            }
        }

        AccountPlan kztChecking = new AccountPlan();
        kztChecking.setCurrency("KZT");
        kztChecking.setType(AccountType.CHECKING);
        kztChecking.setInterestRate(BigDecimal.ZERO);
        kztChecking.setMinBalance(0);
        
        AccountPlan usdChecking = new AccountPlan();
        usdChecking.setCurrency("USD");
        usdChecking.setType(AccountType.CHECKING);
        usdChecking.setInterestRate(BigDecimal.ZERO);
        usdChecking.setMinBalance(0);

        AccountPlan eurChecking = new AccountPlan();
        eurChecking.setCurrency("EUR");
        eurChecking.setType(AccountType.CHECKING);
        eurChecking.setInterestRate(BigDecimal.ZERO);
        eurChecking.setMinBalance(0);

        AccountPlan kztSaving = new AccountPlan();
        kztSaving.setCurrency("KZT");
        kztSaving.setType(AccountType.SAVING);
        kztSaving.setInterestRate(BigDecimal.valueOf(16));
        kztSaving.setMinBalance(1000);
        
        AccountPlan usdSaving = new AccountPlan();
        usdSaving.setCurrency("USD");
        usdSaving.setType(AccountType.SAVING);
        usdSaving.setInterestRate(BigDecimal.valueOf(1));
        usdSaving.setMinBalance(50);

        AccountPlan eurSaving = new AccountPlan();
        eurSaving.setCurrency("EUR");
        eurSaving.setType(AccountType.SAVING);
        eurSaving.setInterestRate(BigDecimal.valueOf(1));
        eurSaving.setMinBalance(50);

        accountPlanRepository.saveAll(List.of(kztChecking, usdChecking, eurChecking, kztSaving, usdSaving, eurSaving));
    }
    
}
