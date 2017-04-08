package com.youndevice.services.repoServices;

import com.youndevice.domain.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

abstract class BaseRepoService<T extends BaseEntity, ID extends Serializable> {

    abstract JpaRepository<T, ID> getRepository();

    public long count() {
        return getRepository().count();
    }

    public <S extends T> long count(Example<S> example) {
        return getRepository().count(example);
    }

    public boolean exists(ID id) {
        return getRepository().exists(id);
    }

    public <S extends T> boolean exists(Example<S> example) {
        return getRepository().exists(example);
    }

    public T getOne(ID id) {
        return getRepository().getOne(id);
    }

    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    public <S extends T> S findOne(Example<S> example) {
        return getRepository().findOne(example);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public List<T> findAll(Iterable<ID> ids) {
        return getRepository().findAll(ids);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return getRepository().findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }

    public void flush() {
        getRepository().flush();
    }

    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    public <S extends T> S saveAndFlush(S entity) {
        return getRepository().saveAndFlush(entity);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        return getRepository().save(entities);
    }

    public void delete(ID id) {
        //TODO Discuss with Richa and Tushar whether this should be har delete or soft delete.
        getRepository().delete(id);
    }

    public void delete(T entity) {
        //TODO Discuss with Richa and Tushar whether this should be har delete or soft delete.
        getRepository().delete(entity);
    }

    public void delete(Iterable<? extends T> entities) {
        //TODO Discuss with Richa and Tushar whether this should be har delete or soft delete.
        getRepository().delete(entities);
    }

    public void deleteAll() {
        //TODO Discuss with Richa and Tushar whether this should be har delete or soft delete.
        getRepository().deleteAll();
    }

    public void deleteInBatch(Iterable<T> entities) {
        //TODO Discuss with Richa and Tushar whether this should be har delete or soft delete.
        getRepository().deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        //TODO Discuss with Richa and Tushar whether this should be har delete or soft delete.
        getRepository().deleteAllInBatch();
    }
}
