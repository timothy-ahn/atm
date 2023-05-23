package atm.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import atm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
