package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskControllerTest {

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Task 1", "Content 1");
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Content 1");

        when(dbService.getTask(task.getId())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> responseEntity = taskController.getTask(taskDto.getId());

        // Then
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(taskDto, responseEntity.getBody());
    }

    @Test
    void deleteTask() {
        // Given
        Task task = new Task(1L, "Task 1", "Content 1");

        // When
        ResponseEntity<String> responseEntity = taskController.deleteTask(task.getId());

        // Then
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Delete Task with number: " + task.getId(), responseEntity.getBody());

    }

    @Test
    void updateTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated Content");
        Task task = new Task(1L, "Updated Task", "Updated Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        ResponseEntity<TaskDto> responseEntity = taskController.updateTask(taskDto);

        //Then
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(taskDto, responseEntity.getBody());
    }

    @Test
    void createTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "New Task", "New Content");
        Task task = new Task(1L, "New Task", "New Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        //When
        ResponseEntity<Void> responseEntity = taskController.createTask(taskDto);

        //then
        assertEquals(200, responseEntity.getStatusCodeValue());

    }


}