package com.component.orthocp.web;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.component.orthocp.domain.Component;
import com.component.orthocp.domain.ComponentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/components")
public class ComponentController {
    @Autowired
    private ComponentService componentService;

    @PostMapping
    public ResponseEntity<Component> create(@RequestBody Component component) {
        //process POST request
        Component componentCreated = componentService.create(component);
        return ResponseEntity.status(HttpStatus.CREATED).body(componentCreated);
    }
    
}
