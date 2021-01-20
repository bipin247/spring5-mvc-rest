package guru.springframework.repositories;

import guru.springframework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByFirstname(String firstName) throws RuntimeException;
    Customer findByLastname(String lastName) throws RuntimeException;
}
