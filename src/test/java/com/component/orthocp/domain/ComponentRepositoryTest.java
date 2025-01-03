package com.component.orthocp.domain;
import static com.component.orthocp.common.ComponentConstants.COMPONENT;
import static org.assertj.core.api.Assertions.assertThat;

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
}
