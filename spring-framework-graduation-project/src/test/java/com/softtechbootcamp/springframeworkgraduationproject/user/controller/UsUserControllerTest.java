package com.softtechbootcamp.springframeworkgraduationproject.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtechbootcamp.springframeworkgraduationproject.general.BaseTest;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UsUserControllerTest extends BaseTest {

    private static final String BASE_PATH = "/users";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldSaveUser() throws Exception {
        UsUserSaveDto usUserSaveDto = UsUserSaveDto.builder()
                .name("TestN").surname("TestS").username("UsernameTest2").userPassword("123").build();

        String content = objectMapper.writeValueAsString(usUserSaveDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void shouldUpdateUser() throws Exception {
        UsUserDto usUserDto = new UsUserDto();
        usUserDto.setId(2L);
        usUserDto.setName("Name");
        usUserDto.setSurname("Surname");
        usUserDto.setUsername("Armin");
        usUserDto.setUserPassword("2004");

        String content = objectMapper.writeValueAsString(usUserDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldDeleteUser() throws Exception {
        MvcResult result = mockMvc.perform(
                delete(BASE_PATH+ "/5").content("5").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotDeleteWhenUserIdDoesNotExist() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/9999").content("9999").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }
}