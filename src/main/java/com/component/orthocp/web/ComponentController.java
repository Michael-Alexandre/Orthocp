package com.component.orthocp.web;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.component.orthocp.domain.Component;
import com.component.orthocp.domain.ComponentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/component")
public class ComponentController {
    @Autowired
    private ComponentService componentService;

    @PostMapping
    public ResponseEntity<Component> create(@RequestBody Component component) {
        //process POST request
        Component componentCreated = componentService.create(component);
        return ResponseEntity.status(HttpStatus.CREATED).body(componentCreated);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Component> get(@PathVariable("id") Long id) {
        return componentService.get(id).map(component -> ResponseEntity.ok(component))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Component> getByCode(@PathVariable("code") String code) {
        return componentService.getByCode(code).map(component -> ResponseEntity.ok(component))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
