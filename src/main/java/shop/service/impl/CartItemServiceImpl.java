package shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import shop.domain.CartItem;
import shop.repository.CartItemRepository;
import shop.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public <S extends CartItem> S save(S entity) {
		return cartItemRepository.save(entity);
	}

	@Override
	public <S extends CartItem> List<S> saveAll(Iterable<S> entities) {
		return cartItemRepository.saveAll(entities);
	}

	@Override
	public <S extends CartItem> Optional<S> findOne(Example<S> example) {
		return cartItemRepository.findOne(example);
	}

	@Override
	public List<CartItem> findAll(Sort sort) {
		return cartItemRepository.findAll(sort);
	}

	@Override
	public void flush() {
		cartItemRepository.flush();
	}

	@Override
	public Page<CartItem> findAll(Pageable pageable) {
		return cartItemRepository.findAll(pageable);
	}

	@Override
	public <S extends CartItem> S saveAndFlush(S entity) {
		return cartItemRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends CartItem> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cartItemRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<CartItem> findAll() {
		return cartItemRepository.findAll();
	}

	@Override
	public List<CartItem> findAllById(Iterable<Long> ids) {
		return cartItemRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<CartItem> entities) {
		cartItemRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cartItemRepository.findAll(example, pageable);
	}

	@Override
	public Optional<CartItem> findById(Long id) {
		return cartItemRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<CartItem> entities) {
		cartItemRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return cartItemRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		cartItemRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends CartItem> long count(Example<S> example) {
		return cartItemRepository.count(example);
	}

	@Override
	public <S extends CartItem> boolean exists(Example<S> example) {
		return cartItemRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		cartItemRepository.deleteAllInBatch();
	}

	@Override
	public CartItem getOne(Long id) {
		return cartItemRepository.getOne(id);
	}

	@Override
	public <S extends CartItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cartItemRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return cartItemRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		cartItemRepository.deleteById(id);
	}

	@Override
	public CartItem getById(Long id) {
		return cartItemRepository.getById(id);
	}

	@Override
	public void delete(CartItem entity) {
		cartItemRepository.delete(entity);
	}

	@Override
	public CartItem getReferenceById(Long id) {
		return cartItemRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		cartItemRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends CartItem> entities) {
		cartItemRepository.deleteAll(entities);
	}

	@Override
	public <S extends CartItem> List<S> findAll(Example<S> example) {
		return cartItemRepository.findAll(example);
	}

	@Override
	public <S extends CartItem> List<S> findAll(Example<S> example, Sort sort) {
		return cartItemRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		cartItemRepository.deleteAll();
	}
	
	
}
