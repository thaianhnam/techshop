package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.domain.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
