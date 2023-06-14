package shop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import shop.domain.OrderDetail;

public interface OrderDetailService {

	void deleteAll();

	<S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort);

	<S extends OrderDetail> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends OrderDetail> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	OrderDetail getReferenceById(Long id);

	void delete(OrderDetail entity);

	OrderDetail getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	OrderDetail getOne(Long id);

	void deleteAllInBatch();

	<S extends OrderDetail> boolean exists(Example<S> example);

	<S extends OrderDetail> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<OrderDetail> entities);

	Optional<OrderDetail> findById(Long id);

	<S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<OrderDetail> entities);

	List<OrderDetail> findAllById(Iterable<Long> ids);

	List<OrderDetail> findAll();

	<S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends OrderDetail> S saveAndFlush(S entity);

	Page<OrderDetail> findAll(Pageable pageable);

	void flush();

	List<OrderDetail> findAll(Sort sort);

	<S extends OrderDetail> Optional<S> findOne(Example<S> example);

	<S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

	<S extends OrderDetail> S save(S entity);

}
