package shop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import shop.domain.CartItem;

public interface CartItemService {

	void deleteAll();

	<S extends CartItem> List<S> findAll(Example<S> example, Sort sort);

	<S extends CartItem> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends CartItem> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	CartItem getReferenceById(Long id);

	void delete(CartItem entity);

	CartItem getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends CartItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	CartItem getOne(Long id);

	void deleteAllInBatch();

	<S extends CartItem> boolean exists(Example<S> example);

	<S extends CartItem> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<CartItem> entities);

	Optional<CartItem> findById(Long id);

	<S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<CartItem> entities);

	List<CartItem> findAllById(Iterable<Long> ids);

	List<CartItem> findAll();

	<S extends CartItem> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends CartItem> S saveAndFlush(S entity);

	Page<CartItem> findAll(Pageable pageable);

	void flush();

	List<CartItem> findAll(Sort sort);

	<S extends CartItem> Optional<S> findOne(Example<S> example);

	<S extends CartItem> List<S> saveAll(Iterable<S> entities);

	<S extends CartItem> S save(S entity);

}
