package shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import shop.domain.Account;
import shop.domain.Category;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	List<Account> findByUsernameContaining(String name);
}
