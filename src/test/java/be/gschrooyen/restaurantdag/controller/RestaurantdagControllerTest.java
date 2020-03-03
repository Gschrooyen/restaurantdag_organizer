package be.gschrooyen.restaurantdag.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RestaurantdagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNewRestaurantdagWithEmptyBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/restaurantdag/new")).andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void testNewRestaurantdagWithEmptyName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/restaurantdag/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"epochDate\": 0, " +
                        "\"gerechten\": [{\"naam\": \"steak\", \"prijs\": 12.5, \"isKindergerecht\": false}]}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testNewRestaurantdagSucces() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/restaurantdag/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"naam\": \"restaurantdag 2020\", " +
                        "\"epochDate\": 0, " +
                        "\"gerechten\": [{\"naam\": \"steak\", \"prijs\": 12.5, \"isKindergerecht\": false}]}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}