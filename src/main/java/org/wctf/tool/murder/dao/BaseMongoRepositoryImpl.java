package org.wctf.tool.murder.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class BaseMongoRepositoryImpl<T, ID extends Serializable>
		implements BaseMongoRepository<T, ID>, InitializingBean {
	@Autowired
	protected MongoTemplate mongoTemplate;
	private SimpleMongoRepository<T, ID> delegate;
	private Class<T> domainCls;

	public BaseMongoRepositoryImpl(Class<T> domainCls) {
		this.domainCls = domainCls;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		MongoRepositoryFactory mongoRepositoryFactory = new MongoRepositoryFactory(mongoTemplate);
		MongoEntityInformation<T, ID> entityInfomation = mongoRepositoryFactory.getEntityInformation(domainCls);
		delegate = new SimpleMongoRepository<T, ID>(entityInfomation, mongoTemplate);
	}

	public <S extends T> List<S> save(Iterable<S> entities) {
		return delegate.saveAll(entities);
	}

	public List<T> findAll() {
		return delegate.findAll();
	}

	public List<T> findAll(Sort sort) {
		return delegate.findAll(sort);
	}

	public <S extends T> S insert(S entity) {
		return delegate.insert(entity);
	}

	public <S extends T> List<S> insert(Iterable<S> entities) {
		return delegate.insert(entities);
	}

	public <S extends T> List<S> findAll(Example<S> example) {
		return delegate.findAll(example);
	}

	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return delegate.findAll(example, sort);
	}

	public Page<T> findAll(Pageable pageable) {
		return delegate.findAll(pageable);
	}

	public <S extends T> S save(S entity) {
		return delegate.save(entity);
	}

	public T findOne(ID id) {
		Optional<T> result = delegate.findById(id);
		if (result.isPresent())
			return result.get();
		return null;
	}

	public boolean exists(ID id) {
		return delegate.existsById(id);
	}

	public Iterable<T> findAll(Iterable<ID> ids) {
		return delegate.findAllById(ids);
	}

	public long count() {
		return delegate.count();
	}

	public void delete(ID id) {
		delegate.deleteById(id);
	}

	public void delete(T entity) {
		delegate.delete(entity);
	}

	public void delete(Iterable<? extends T> entities) {
		delegate.deleteAll(entities);
	}

	public void deleteAll() {
		delegate.deleteAll();
	}

	public <S extends T> S findOne(Example<S> example) {
		Optional<S> result = delegate.findOne(example);
		if (result.isPresent())
			return result.get();
		return null;
	}

	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return delegate.findAll(example, pageable);
	}

	public <S extends T> long count(Example<S> example) {
		return delegate.count(example);
	}

	public <S extends T> boolean exists(Example<S> example) {
		return delegate.exists(example);
	}
}