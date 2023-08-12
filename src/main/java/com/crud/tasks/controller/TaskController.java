package com.crud.tasks.controller;

import com.crud.tasks.com.crud.tasks.domain.Task;
import com.crud.tasks.com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

//    @GetMapping(value = "{taskId}")
//    public TaskDto getTask(@PathVariable Long taskId) {
//        return new TaskDto(1L, "test title", "test_content");
//    }

    @GetMapping(value = "{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws Exception {
        return ResponseEntity.ok(taskMapper.mapToTaskDto(service.getTaskId(taskId)));
    }

    @DeleteMapping(value = "{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        return "delete task number ID: " + taskId;
    }

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping(value = "{taskDto}")
    public String createTask(@PathVariable TaskDto taskDto) {
        return "create new task";
    }
}
