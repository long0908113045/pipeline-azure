package com.example.spring.user.controller;

import com.example.spring.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  private UserRepository userRepository;

  @Test
  public void getAllUsers() throws Exception {
    when(userRepository.findAll()).thenReturn(null);
    this.mockMvc.perform(get("/api/user/all").contentType(MediaType.ALL))
      .andDo(print())
      .andExpect(status().isOk());
  }
}
