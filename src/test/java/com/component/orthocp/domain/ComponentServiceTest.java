package com.component.orthocp.domain;

import static com.component.orthocp.common.ComponentConstants.COMPONENT;
import static com.component.orthocp.common.ComponentConstants.INVALID_COMPONENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void createComponent_invalid_ThrowsException() {
        when(componentRepository.save(INVALID_COMPONENT)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> componentService.create(INVALID_COMPONENT)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getComponent_validId_returnPlanet() {
        when(componentRepository.findById(1L)).thenReturn(Optional.of(COMPONENT));
        Optional<Component> sut = componentService.get(1L);
        
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(COMPONENT);
    }

    @Test
    public void getComponent_invalidId_returnEmpty() {
        when(componentRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Component> sut = componentService.get(1L);
        
        assertThat(sut).isEmpty();
    }

    @Test
    public void getComponent_validCode_returnPlanet() {
        when(componentRepository.findByCode("code")).thenReturn(Optional.of(COMPONENT));
        Optional<Component> sut = componentService.getByCode("code");
        
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(COMPONENT);
    }

    @Test
    public void getComponent_invalidCode_returnEmpty() {
        when(componentRepository.findByCode("invalid code")).thenReturn(Optional.empty());
        Optional<Component> sut = componentService.getByCode("invalid code");
        
        assertThat(sut).isEmpty();
    }

    @Test
    public void listComponent_returnAll() {
        List<Component> components = List.of(COMPONENT);
        when(componentRepository.findAll(any())).thenReturn(components);

        List<Component> sut = componentService.list(COMPONENT.getCode(), COMPONENT.getDescription());

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut).contains(COMPONENT);
    }

    @Test
    public void listComponent_returnEmpty() {
        when(componentRepository.findAll(any())).thenReturn(Collections.emptyList());

        List<Component> sut = componentService.list(COMPONENT.getCode(), COMPONENT.getDescription());
        
        assertThat(sut).isEmpty();
    }

    @Test
    public void removeComponent_validId_anyException() {
        assertThatCode(()-> componentService.remove(1L)).doesNotThrowAnyException();
    }

    @Test
    public void removeComponent_invalidId_ThrowsException() {
        doThrow(new RuntimeException()).when(componentRepository).deleteById(99L);

        assertThatThrownBy(() -> componentService.remove(99L)).isInstanceOf(RuntimeException.class);
    }

}
