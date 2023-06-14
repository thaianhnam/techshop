package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.domain.OrderDetail;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
