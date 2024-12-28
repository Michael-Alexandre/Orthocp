package com.component.orthocp.domain;

import static com.component.orthocp.common.ComponentConstants.COMPONENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest(classes = ComponentService.class)
public class ComponentServiceTest {
    @Autowired
    private ComponentService componentService;

    @MockitoBean
    private ComponentRepository componentRepository;

    //nomear testes = operacao_estado_retorno
    @Test
    public void createComponent_valid_returnsComponent() {
        when(componentRepository.save(COMPONENT)).thenReturn(COMPONENT);
        // system under test
        Component sut = componentService.create(COMPONENT);
        assertThat(sut).isEqualTo(COMPONENT);
        
    }

}
