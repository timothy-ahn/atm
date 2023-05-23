package atm.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import atm.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
    
}
