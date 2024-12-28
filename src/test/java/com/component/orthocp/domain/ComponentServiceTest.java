package com.component.orthocp.domain;

import static com.component.orthocp.common.ComponentConstants.COMPONENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
// menos eficiente @SpringBootTest(classes = ComponentService.class)
public class ComponentServiceTest {
    
    // spring @Autowired
    @InjectMocks
    private ComponentService componentService;

    // @MockitoBean
    @Mock
    private ComponentRepository componentRepository;

    //nomear testes = operacao_estado_retorno
    @Test
    public void createComponent_valid_returnsComponent() {
        // AAA = Arrange, act, assert
        when(componentRepository.save(COMPONENT)).thenReturn(COMPONENT);
        // sut = system under test; A = act
        Component sut = componentService.create(COMPONENT);
        // a = assert
        assertThat(sut).isEqualTo(COMPONENT);
        
    }

}
