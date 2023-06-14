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

import shop.domain.OrderDetail;
import shop.repository.OrderDetailRepository;
import shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDetailRepository detailRepository;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return detailRepository.save(entity);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return detailRepository.saveAll(entities);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return detailRepository.findOne(example);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return detailRepository.findAll(sort);
	}

	@Override
	public void flush() {
		detailRepository.flush();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return detailRepository.findAll(pageable);
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return detailRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return detailRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<OrderDetail> findAll() {
		return detailRepository.findAll();
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Long> ids) {
		return detailRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		detailRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return detailRepository.findAll(example, pageable);
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		return detailRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		detailRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return detailRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		detailRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends OrderDetail> long count(Example<S> example) {
		return detailRepository.count(example);
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return detailRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		detailRepository.deleteAllInBatch();
	}

	@Override
	public OrderDetail getOne(Long id) {
		return detailRepository.getOne(id);
	}

	@Override
	public <S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return detailRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return detailRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		detailRepository.deleteById(id);
	}

	@Override
	public OrderDetail getById(Long id) {
		return detailRepository.getById(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		detailRepository.delete(entity);
	}

	@Override
	public OrderDetail getReferenceById(Long id) {
		return detailRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		detailRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		detailRepository.deleteAll(entities);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return detailRepository.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return detailRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		detailRepository.deleteAll();
	}
	
	
}
