package com.snake.web.boot.module.rup;

import com.snake.web.boot.module.rup.model.ModelInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2018/11/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ModelInfoControllerTests {
   @Autowired
   private MockMvc mockMvc;
    @Test
    public void startModel() throws Exception {
        ModelInfo modelInfo=new ModelInfo();
        this.mockMvc.perform(post("/model/getModelInfo").param("id","10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
