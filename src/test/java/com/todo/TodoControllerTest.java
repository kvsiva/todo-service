package com.todo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.entity.Todo;
import com.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TodoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void getPlannedTasks() throws Exception {
        List<Todo> toDoList = new ArrayList<Todo>();
        toDoList.add(new Todo(1,"Eat thrice","High"));
        toDoList.add(new Todo(2,"Seep Twice","High"));
        when(todoService.findPlannedTasks()).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos/getPlannedTasks")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    void getInProgressTasks() throws Exception {
        List<Todo> toDoList = new ArrayList<Todo>();
        toDoList.add(new Todo(1,"Eat thrice","High"));
        when(todoService.findInProgressTasks()).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos/getInProgressTasks")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

    @Test
    void getDoneTasks() throws Exception {
        List<Todo> toDoList = new ArrayList<Todo>();
        toDoList.add(new Todo(1,"Eat thrice","High"));
        when(todoService.findDoneTasks()).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos/getDoneTasks")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

  @Test
  public void saveTodo() throws Exception {
      String uri = "/todos/createTask";
      Todo todo = new Todo(1,"Eat thrice","HIGH");

      String inputJson = mapToJson(todo);
      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
              .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
   }

    @Test
    public void deleteTaskPlanned() throws Exception {
        String uri = "/todos/deleteTaskPlanned/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteTaskProgress() throws Exception {
        String uri = "/todos/deleteTaskProgress/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateTask() throws Exception {
        String uri = "/todos/updateTask/1";
        Todo todo = new Todo(1,"Eat thrice","HIGH");
        String inputJson = mapToJson(todo);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
     }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
