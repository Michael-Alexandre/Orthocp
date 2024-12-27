package com.component.orthocp.domain;

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
}
