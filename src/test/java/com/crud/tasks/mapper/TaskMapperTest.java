package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task 1", "Content 1");

        //When
        Task result = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), result.getId());
        assertEquals(taskDto.getTitle(), result.getTitle());
        assertEquals(taskDto.getContent(), result.getContent());
    }

    @Test
    void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "Task 1", "Content 1");

        //When
        TaskDto result = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTitle(), result.getTitle());
        assertEquals(task.getContent(), result.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "Task 1", "Content 1"),
                new Task(2L, "Task 2", "Content 2"));
        List<TaskDto> taskDtos = List.of(new TaskDto(1L, "Task 1", "Content 1"),
                new TaskDto(2L, "Task 2", "Content 2"));

        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(taskDtos, result);
    }
}