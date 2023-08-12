package com.crud.tasks.service;

import com.crud.tasks.com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskId(final Long id) throws Exception{
        return repository.findById(id).orElseThrow(Exception::new);
    }

}
