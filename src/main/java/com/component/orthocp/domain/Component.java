package com.component.orthocp.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "components")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    private String draw;
    private String binlocation;

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

    
    
}
