package ru.kpekepsalt.diary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kpekepsalt.diary.dto.TaskDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "task_date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "public")
    private boolean isPublic;

    public Task() {}

    public Task(TaskDto taskDto) {
        this.title = taskDto.getTitle();
        this.date = taskDto.getDate();
        this.startTime = taskDto.getStartTime();
        this.endTime = taskDto.getEndTime();
        if(isEmpty(taskDto.getTaskStatus())) {
            this.taskStatus = TaskStatus.OPEN;
        }else{
            this.taskStatus = taskDto.getTaskStatus();
        }
        this.isPublic = taskDto.isPublic();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
