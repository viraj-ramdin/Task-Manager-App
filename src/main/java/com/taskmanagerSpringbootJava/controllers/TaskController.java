package com.taskmanagerSpringbootJava.controllers;

import com.taskmanagerSpringbootJava.entities.CreateTaskDTO;
import com.taskmanagerSpringbootJava.entities.ErrorResponseDto;
import com.taskmanagerSpringbootJava.entities.TaskEntity;
import com.taskmanagerSpringbootJava.entities.UpdateTaskDto;
import com.taskmanagerSpringbootJava.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks=taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id,@RequestBody UpdateTaskDto body) throws ParseException {
        var task=taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.getIsCompleted());
        if(task==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id ){
        var task=taskService.getTaskById(id);

        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/add")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task=taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid Date Format"));
        }

        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Invalid Request"));
    }
}
