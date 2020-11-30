package ru.kpekepsalt.diary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@ApiModel("Task")
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(example = "1")
    @Getter
    @Setter
    private Long id;

    @Column(name = "title")
    @ApiModelProperty(example = "Test task")
    @Getter
    @Setter
    private String title;

    @Column(name = "task_date")
    @ApiModelProperty(example = "2020-11-19")
    @Getter
    @Setter
    private LocalDate date;

    @Column(name = "start_time")
    @ApiModelProperty(example = "00:00:00", dataType = "java.sql.Date")
    @JsonFormat(pattern = "HH::mm:ss")
    @Getter
    @Setter
    private LocalTime startTime;

    @Column(name = "end_time")
    @ApiModelProperty(example = "00:00:00", dataType = "java.sql.Date")
    @JsonFormat(pattern = "HH::mm:ss")
    @Getter
    @Setter
    private LocalTime endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(name = "status", example = "OPEN")
    @Getter
    @Setter
    private TaskStatus taskStatus;

    @Column(name = "user_id")
    @ApiModelProperty(hidden = true)
    @Getter
    @Setter
    private Long userId;

}
