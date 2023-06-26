package com.taskmanagerSpringbootJava.entities;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateTaskDto {

    String description;
    String deadline;
    Boolean isCompleted;

}
