package com.snake.web.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static com.snake.web.boot.Constants.success;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by HP on 2018/7/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createTest() throws Exception {
        this.mockMvc.perform(post("/person/create").param("id", "1").param("name", "wenxy"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string((String) success()));
    }

    @Test
    public void listTest() throws Exception {
        this.mockMvc.perform(get("/person/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name",is("wenxy")).isString());
    }

    @Test
    public void updateTest() throws Exception {
        this.mockMvc.perform(post("/person/update").param("id", "1").param("name", "wenxueyong"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string((String) success()));
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(post("/person/delete").param("id","1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string((String) success()));
    }

}
