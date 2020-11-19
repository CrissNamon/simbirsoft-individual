package ru.kpekepsalt.diary.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.repository.TaskRepository;
import ru.kpekepsalt.diary.service.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public boolean save(TaskDto taskDto) {
        Task task = new Task(taskDto);
        return isEmpty(
                taskRepository.save(task)
        );
    }

    @Override
    public boolean save(Task task) {
        return isEmpty(
                taskRepository.save(task)
        );
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> findByDate(LocalDate date) {
        return taskRepository.findByDate(date);
    }

}
