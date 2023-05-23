package atm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import atm.entity.Account;
import atm.entity.AccountPlan;
import atm.entity.Customer;
import atm.entity.Account.AccountType;
import atm.exception.NotFoundException;
import atm.respository.AccountPlanRepository;
import atm.respository.AccountRepository;
import atm.respository.CustomerRepository;
import net.datafaker.Faker;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;
    private final AccountPlanRepository accountPlanRepo;

    public CustomerService(CustomerRepository customerRepo, AccountRepository accountRepo, AccountPlanRepository accountPlanRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
        this.accountPlanRepo = accountPlanRepo;
    }

    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    public Customer findById(int id) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (!optionalCustomer.isPresent()) {
            throw new NotFoundException("Customer with id " + id + " was not found!");
        }

        return optionalCustomer.get();
    }

    public void createCustomer(Customer customer) {    
        customer.setCitizenship(customer.getCitizenship().toUpperCase());
        customer.setStateIsoCode(customer.getStateIsoCode().toUpperCase());
        customerRepo.save(customer);

        Faker faker = new Faker(new Locale("ru"));
        List<AccountPlan> accountPlans = accountPlanRepo.findByType(AccountType.CHECKING);
        
        Optional<AccountPlan> kztPlan = accountPlans.stream().filter(a -> a.getCurrency().equals("KZT")).findFirst();
        if (kztPlan.isPresent()) {
            Account kztAccount = new Account();
            kztAccount.setCustomer(customer);
            kztAccount.setIban(faker.numerify("KZ##################"));
            kztAccount.setCurrency("KZT");
            kztAccount.setType(AccountType.CHECKING);
            kztAccount.setInterestRate(kztPlan.get().getInterestRate());
            kztAccount.setOpenedAt(LocalDateTime.now());
            kztAccount.setUpdatedAt(LocalDateTime.now());
            accountRepo.save(kztAccount);
        } else {
            LOGGER.warn("Could not create a default KZT account for Customer with id" + customer.getId());
        }

        Optional<AccountPlan> usdPlan = accountPlans.stream().filter(a -> a.getCurrency().equals("USD")).findFirst();
        if (usdPlan.isPresent()) {
            Account usdAccount = new Account();
            usdAccount.setCustomer(customer);
            usdAccount.setIban(faker.numerify("KZ##################"));
            usdAccount.setCurrency("USD");
            usdAccount.setType(AccountType.CHECKING);
            usdAccount.setInterestRate(kztPlan.get().getInterestRate());
            usdAccount.setOpenedAt(LocalDateTime.now());
            usdAccount.setUpdatedAt(LocalDateTime.now());
            accountRepo.save(usdAccount);
        } else {
            LOGGER.warn("Could not create a default USD account for Customer with id" + customer.getId());
        }

        Optional<AccountPlan> eurPlan = accountPlans.stream().filter(a -> a.getCurrency().equals("EUR")).findFirst();
        if (eurPlan.isPresent()) {
            Account eurAccount = new Account();
            eurAccount.setCustomer(customer);
            eurAccount.setIban(faker.numerify("KZ##################"));
            eurAccount.setCurrency("KZT");
            eurAccount.setType(AccountType.CHECKING);
            eurAccount.setInterestRate(kztPlan.get().getInterestRate());
            eurAccount.setOpenedAt(LocalDateTime.now());
            eurAccount.setUpdatedAt(LocalDateTime.now());
            accountRepo.save(eurAccount);
        } else {
            LOGGER.warn("Could not create a default EUR account for Customer with id" + customer.getId());
        }
    }

    public void updateCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customer.getId());
        if (!optionalCustomer.isPresent()) {
            throw new NotFoundException("Customer with id " + customer.getId() + " was not found!");
        }

        Customer customerToUpdate = optionalCustomer.get();   
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setMiddleName(customer.getMiddleName());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setActualAddress(customer.getActualAddress());
        customerToUpdate.setResidenceAddress(customer.getResidenceAddress());

        customerRepo.save(customerToUpdate);
    }

    public void deleteCustomer(int id) {
        customerRepo.deleteById(id);
    }
}
