package com.component.orthocp.domain;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ComponentService {
    private ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Component create(Component component) {
        return componentRepository.save(component);
    }

    public Optional<Component> get(Long id) {
        return componentRepository.findById(id);
    }

    public Optional<Component> getByCode(String code) {
        return componentRepository.findByCode(code);
    }

    public List<Component> list(String code, String description) {
        Example<Component> query = QueryBuilder.makeQuery(new Component(code, description, null, null));
        return componentRepository.findAll(query);
    }

    public void remove(Long id) {
        componentRepository.deleteById(id);
    }
}
