package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.accident.AccidentService;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {
    @MockBean
    private AccidentService accidentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void viewAllAccidentsAndGetDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/accidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/allAccidents"));
    }

    @Test
    @WithMockUser
    void viewCreateAccidentAndGetDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"));
    }

    @Test
    @WithMockUser
    void formUpdateAndGetPageWithError() throws Exception {
        this.mockMvc.perform(get("/formUpdateAccident?id=-5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errors/404"));
    }

    @Test
    @WithMockUser
    void formUpdateAAndGetDefaultMessage() throws Exception {
        Accident accident = new Accident(1, "name", "text", "address", new AccidentType(0, "type"),
                new HashSet<>());
        when(accidentService.findById(anyInt())).thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("/formUpdateAccident?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/updateAccident"));
    }

    @Test
    @WithMockUser
    void whenSaveAccidentAndGetDefaultMessage() throws Exception {
        Accident accident = new Accident(1, "name", "text", "address", new AccidentType(0, "type"),
                new HashSet<>());
        List<Integer> rIds = List.of(1);
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("name", accident.getName());
        parameters.add("text", accident.getText());
        parameters.add("address", accident.getAddress());
        parameters.add("type_id", String.valueOf(accident.getType().getId()));
        parameters.add("rIds", "1");
        when(accidentService.create(accident, rIds)).thenReturn(accident);
        this.mockMvc.perform(post("/saveAccident")
                .params(parameters))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accidents"));

        ArgumentCaptor<Accident> accidentCaptor = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<List<Integer>> idsCaptor = ArgumentCaptor.forClass(List.class);
        verify(accidentService).create(accidentCaptor.capture(), idsCaptor.capture());
        assertThat(accidentCaptor.getValue().getName()).isEqualTo(accident.getName());
    }

    @Test
    @WithMockUser
    void whenUpdateAccidentAndGetDefaultMessage() throws Exception {
        Accident accident = new Accident(1, "name", "text", "address", new AccidentType(0, "type"),
                new HashSet<>());
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("name", accident.getName());
        parameters.add("text", accident.getText());
        parameters.add("address", accident.getAddress());
        parameters.add("type_id", String.valueOf(accident.getType().getId()));
        parameters.add("rIds", "1");
        when(accidentService.update(any(Accident.class), any(List.class))).thenReturn(Optional.of(accident));
        this.mockMvc.perform(post("/updateAccident")
                        .params(parameters))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        ArgumentCaptor<Accident> accidentCaptor = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<List<Integer>> idsCaptor = ArgumentCaptor.forClass(List.class);
        verify(accidentService).update(accidentCaptor.capture(), idsCaptor.capture());
        assertThat(accidentCaptor.getValue().getName()).isEqualTo(accident.getName());
    }
}