package com.component.orthocp.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;


public interface ComponentRepository extends CrudRepository<Component, Long>, QueryByExampleExecutor<Component> {
    Optional<Component> findByCode(String code);

    @Override
    <S extends Component> List<S> findAll(Example<S> example);
}
