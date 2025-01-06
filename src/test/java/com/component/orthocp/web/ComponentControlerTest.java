package com.component.orthocp.web;

import static com.component.orthocp.common.ComponentConstants.COMPONENT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.component.orthocp.domain.Component;
import com.component.orthocp.domain.ComponentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ComponentController.class)
public class ComponentControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ComponentService componentService;

    @Test
    public void createComponent_Valid_returnCreated() throws Exception {
        when(componentService.create(COMPONENT)).thenReturn(COMPONENT);

        mockMvc.perform(post("/component").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(COMPONENT)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$").value(COMPONENT));
    }

    @Test
    public void createComponent_Invalid_returnBadRequest() throws Exception {
        Component emptyComponent = new Component(null, null, null, null);
        Component invalidComponent = new Component("", "", "", "");

        mockMvc.perform(post("/component")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(emptyComponent)))
        .andExpect(status().isBadRequest());

        mockMvc.perform(post("/component")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(invalidComponent)))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void createComponent_Existing_returnBadRequest() throws Exception {
        when(componentService.create(any())).thenThrow(DataIntegrityViolationException.class);

        mockMvc.perform(post("/component")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(COMPONENT)))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void getComponentById_Valid_returnOk() throws Exception {
        when(componentService.get(1L)).thenReturn(Optional.of(COMPONENT));

        mockMvc.perform(get("/component/1"))
        .andExpect(status().isOk());
    }

    @Test
    public void getComponentById_Invalid_returnNotFound() throws Exception {
    
        mockMvc.perform(get("/component/1", COMPONENT.getId()))
        .andExpect(status().isNotFound());
    }
}
