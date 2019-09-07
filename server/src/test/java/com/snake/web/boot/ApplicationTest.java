package com.snake.web.boot;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
   private Logger logger = LoggerFactory.getLogger(ApplicationTest.class);
   @Test
   public void testLog(){
        logger.info("info");
        logger.info("debug");
        logger.info("error");
    }

}