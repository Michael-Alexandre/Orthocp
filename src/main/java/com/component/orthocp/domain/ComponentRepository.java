package com.component.orthocp.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface ComponentRepository extends CrudRepository<Component, Long> {
    Optional<Component> findByCode(String code);
}
