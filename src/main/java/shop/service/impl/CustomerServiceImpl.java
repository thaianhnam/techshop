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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import shop.domain.Account;
import shop.domain.Customer;
import shop.repository.CustomerRepository;
import shop.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Optional<Customer> findByName(String name) {
		return customerRepository.findByName(name);
	}

	@Override
	public List<Customer> findByNameContaining(String name) {
		return customerRepository.findByNameContaining(name);
	}
	
	@Override
	public Customer login(String username, String password) {
		Optional<Customer> optExist = findByName(username);
		
		System.out.println(password);
		System.out.println(optExist.isPresent());
		
		if (optExist.isPresent() && bCryptPasswordEncoder.matches(password, optExist.get().getPassword())) {
			
			System.out.println(bCryptPasswordEncoder.matches(password, optExist.get().getPassword()));
			optExist.get().setPassword("");
			return optExist.get();
			
		}
		
		
		return null;
	}

	@Override
	public <S extends Customer> S save(S entity) {
		Optional<Customer> optExist = findByName(entity.getName());
		if (optExist.isPresent()) {
			if (StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(optExist.get().getPassword());
			}else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
		}else {
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		}
		return customerRepository.save(entity);
	}

	@Override
	public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
		return customerRepository.saveAll(entities);
	}

	@Override
	public <S extends Customer> Optional<S> findOne(Example<S> example) {
		return customerRepository.findOne(example);
	}

	@Override
	public List<Customer> findAll(Sort sort) {
		return customerRepository.findAll(sort);
	}

	@Override
	public void flush() {
		customerRepository.flush();
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public <S extends Customer> S saveAndFlush(S entity) {
		return customerRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
		return customerRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findAllById(Iterable<Long> ids) {
		return customerRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Customer> entities) {
		customerRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
		return customerRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Customer> entities) {
		customerRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return customerRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		customerRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Customer> long count(Example<S> example) {
		return customerRepository.count(example);
	}

	@Override
	public <S extends Customer> boolean exists(Example<S> example) {
		return customerRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		customerRepository.deleteAllInBatch();
	}

	@Override
	public Customer getOne(Long id) {
		return customerRepository.getOne(id);
	}

	@Override
	public <S extends Customer, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return customerRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return customerRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer getById(Long id) {
		return customerRepository.getById(id);
	}

	@Override
	public void delete(Customer entity) {
		customerRepository.delete(entity);
	}

	@Override
	public Customer getReferenceById(Long id) {
		return customerRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		customerRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		customerRepository.deleteAll(entities);
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example) {
		return customerRepository.findAll(example);
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
		return customerRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
	}
	
	
}
