package shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.domain.Category;
import shop.domain.Customer;
import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByName(String name);
	List<Customer> findByNameContaining(String name);
}
