package com.component.orthocp.domain;

import static com.component.orthocp.common.ComponentConstants.COMPONENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
public class ComponentRepositoryTest {
    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void cleanAfter() {
        COMPONENT.setId(null);
    }

    @Test
    public void createComponent_ValidComponent_returnComponent() {
        
        Component component = componentRepository.save(COMPONENT);
        
        Component sut = testEntityManager.find(Component.class, component.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getDraw()).isEqualTo(COMPONENT.getDraw());
        assertThat(sut.getCode()).isEqualTo(COMPONENT.getCode());
        assertThat(sut.getBinlocation()).isEqualTo(COMPONENT.getBinlocation());
        assertThat(sut.getDescription()).isEqualTo(COMPONENT.getDescription());        
    }

    @Test
    public void createComponent_InvalidComponent_throwException() {
        Component emptyComponent = new Component(null, null, null, null);
        Component invalidComponent = new Component("", "", "", "");

        assertThatThrownBy(() -> componentRepository.save(emptyComponent)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> componentRepository.save(invalidComponent)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createComponent_Existing_throwException() {
        Component component = testEntityManager.persistFlushFind(COMPONENT);
        testEntityManager.detach(component); // Detach the entity to simulate a new instance
        component.setId(null);
        assertThatThrownBy(() -> componentRepository.save(component)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getComponent_ValidId_ReturnComponent() {
        Component component = testEntityManager.persistFlushFind(COMPONENT);

        Component sut = componentRepository.findById(component.getId()).get();

        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(component);
        }
}
