package com.taskmanagerSpringbootJava.service;
import com.taskmanagerSpringbootJava.entities.TaskEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class TaskService {
    private ArrayList<TaskEntity> tasks= new ArrayList<>();
    private int taskId=1;

    public TaskEntity addTask(String title, String description, String deadline){
        TaskEntity task =new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
//        task.setDeadline(new Date(deadline));// date format yyyy-mm-dd
        task.setCompleted(false);
        tasks.add(task);
        taskId++;

        return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }


    public TaskEntity getTaskById(int id){
        for(TaskEntity task:tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;

    }


}
