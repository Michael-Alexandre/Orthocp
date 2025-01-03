package com.component.orthocp.domain;
import org.apache.commons.lang3.builder.EqualsBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "components")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String code;
    @NotEmpty
    @Column(nullable = false)
    private String description;
    @NotEmpty
    @Column(nullable = false)
    private String draw;
    @NotEmpty
    @Column(nullable = false)
    private String binlocation;

    public Component(String binlocation, String code, String description, String draw) {
        this.binlocation = binlocation;
        this.code = code;
        this.description = description; 
        this.draw = draw;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDraw() {
        return draw;
    }
    public void setDraw(String draw) {
        this.draw = draw;
    }
    public String getBinlocation() {
        return binlocation;
    }
    public void setBinlocation(String binlocation) {
        this.binlocation = binlocation;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }
    
}
