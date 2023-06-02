package com.example.todoApp;


import com.example.todoApp.dto.TaskResponseDTO;
import com.example.todoApp.exception.TaskNotFoundException;
import com.example.todoApp.exception.validationErrorException;
import com.example.todoApp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() throws TaskNotFoundException {


        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(TaskBuilder.convertTaskListToDTOList(tasks));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id)  {

        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(TaskBuilder.convertTaskToTaskDTO(task));
        }catch(TaskNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<TaskResponseDTO> create(@RequestBody TaskResponseDTO taskDto){

        try {
            Task task = taskService.createTask(taskDto);
            return ResponseEntity.ok(TaskBuilder.convertTaskToTaskDTO(task));
        }catch(validationErrorException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("")
    public ResponseEntity<TaskResponseDTO> updateStatus(@RequestBody TaskResponseDTO taskDto){

        try{
            Task task = taskService.updateStatus(taskDto);
            return ResponseEntity.ok(TaskBuilder.convertTaskToTaskDTO(task));
        }catch(TaskNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody TaskResponseDTO taskDto){

        try {
            Task task = taskService.updateTask(taskDto);
            return ResponseEntity.ok(TaskBuilder.convertTaskToTaskDTO(task));
        }
        catch(TaskNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id){

        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task is successfully deleted");
        }
        catch(TaskNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}