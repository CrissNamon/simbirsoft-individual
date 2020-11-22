package ru.kpekepsalt.diary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kpekepsalt.diary.model.TaskStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskDto {


    /**
     * Task title
     */
    private String title;
    /**
     * Task date
     */
    private LocalDate date;
    /**
     * Task start time
     */
    private LocalTime startTime;
    /**
     * Task end time
     */
    private LocalTime endTime;
    /**
     * Task status
     */
    private TaskStatus taskStatus;

    /**
     * Does the task visible for everyone?
     */
    private boolean isPublic;

    /**
     * @return Task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title Task title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Task date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date Task date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return Task start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime Task start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return Task end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime Task end time
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return Task status
     */
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    /**
     * @param taskStatus Task status
     */
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
