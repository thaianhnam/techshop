package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.domain.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}