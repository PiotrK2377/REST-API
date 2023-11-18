package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTasks() throws Exception {
        //Given
        Task task = new Task(1L, "Task 1", "Content 1");
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Content 1");

        when(dbService.getAllTasks()).thenReturn(List.of(task));
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(List.of(taskDto));

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Task 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Content 1")));

    }

    @Test
    void deleteTask() throws Exception {
        // Given
        Task task = new Task(1L, "Task 1", "Content 1");
        when(dbService.saveTask(task)).thenReturn(task);
        dbService.deleteTask(task.getId());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void updateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated Content");
        Task task = new Task(1L, "Updated Task", "Updated Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.
                perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void createTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "New Task", "New Content");
        Task task = new Task(1L, "New Task", "New Content");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.
                perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));



    }


}