package com.javarush.service;

import com.javarush.dao.TaskDAO;
import com.javarush.entity.Status;
import com.javarush.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskDAO taskDAO;

    public List<TaskEntity> getAll(int offset, int limit) {
        return taskDAO.getAll(offset, limit);
    }

    public int getAllCount() {
        return taskDAO.getAllCount();
    }

    @Transactional
    public TaskEntity edit(Long id, String description, Status status) {
        TaskEntity task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Task is not found");
        }

        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);

        return task;
    }

    public TaskEntity create(String description, Status status) {
        TaskEntity task = new TaskEntity();

        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);

        return task;
    }

    @Transactional
    public void delete(long id) {
        TaskEntity task = taskDAO.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Task is not found");
        }

        taskDAO.delete(task);
    }
}
