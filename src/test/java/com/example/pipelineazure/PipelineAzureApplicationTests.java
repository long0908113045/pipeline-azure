package com.example.pipelineazure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PipelineAzureApplicationTests {

	@Test
	void contextLoads() {
    String name = "Hello word";
    assertEquals(name, "Product is created successfully");
	}

}
