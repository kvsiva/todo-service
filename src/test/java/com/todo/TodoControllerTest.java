package com.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.entity.Todo;
import com.todo.service.TodoService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  /*  @Ignore
    @Test
    void successfullyCreateAToDo() throws Exception {
        Todo eatToDo = new Todo(1, "Eat thrice", "HIGH");
        when(todoService.save(any(Todo.class))).thenReturn(eatToDo);
        ObjectMapper objectMapper = new ObjectMapper();
        String eatToDoJSON = objectMapper.writeValueAsString(eatToDo);

        ResultActions result = mockMvc.perform(post("/todos/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eatToDoJSON)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Eat thrice"))
                .andExpect(jsonPath("$.completed").value("HIGH"));
    }
*/
}
