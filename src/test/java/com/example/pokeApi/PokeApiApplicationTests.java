package com.example.pokeApi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class PokeApiApplicationTests {

    private MockMvc mockMvc;

        @Autowired
        private WebApplicationContext webApplicationContext;

        @BeforeEach
        void setUpMockMvc() {
                mockMvc = webAppContextSetup(webApplicationContext).build();
        }

    @Test
    void canCreateRetrieveAndDeletePokemon() throws Exception {
        mockMvc.perform(post("/api/pokemons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"pikachu\",\"url\":\"https://pokeapi.co/api/v2/pokemon/25/\",\"type\":\"electric\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/api/pokemons/1")));

        mockMvc.perform(get("/api/pokemons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pikachu"))
                .andExpect(jsonPath("$.type").value("electric"));

        mockMvc.perform(delete("/api/pokemons/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/pokemons/1"))
                .andExpect(status().isNotFound());
    }
}
