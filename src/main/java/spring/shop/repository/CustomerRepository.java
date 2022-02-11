package spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shop.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
