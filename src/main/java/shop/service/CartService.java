package shop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import shop.domain.Cart;

public interface CartService {

	void deleteAll();

	<S extends Cart> List<S> findAll(Example<S> example, Sort sort);

	<S extends Cart> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Cart> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Cart getReferenceById(Long id);

	void delete(Cart entity);

	Cart getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Cart, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Cart getOne(Long id);

	void deleteAllInBatch();

	<S extends Cart> boolean exists(Example<S> example);

	<S extends Cart> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Cart> entities);

	Optional<Cart> findById(Long id);

	<S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Cart> entities);

	List<Cart> findAllById(Iterable<Long> ids);

	List<Cart> findAll();

	<S extends Cart> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Cart> S saveAndFlush(S entity);

	Page<Cart> findAll(Pageable pageable);

	void flush();

	List<Cart> findAll(Sort sort);

	<S extends Cart> Optional<S> findOne(Example<S> example);

	<S extends Cart> List<S> saveAll(Iterable<S> entities);

	<S extends Cart> S save(S entity);

}
