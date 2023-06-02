package com.example.todoApp;

import com.example.todoApp.dto.TaskResponseDTO;
import com.example.todoApp.exception.TaskNotFoundException;
import com.example.todoApp.exception.validationErrorException;
import com.example.todoApp.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {


    private List<Task> tasks = new ArrayList<>();
    private Integer nextTaskId = 0;

    public List<Task> getAllTasks() throws TaskNotFoundException {

        if(tasks != null)
            return tasks;
        throw new TaskNotFoundException("Tasks are empty");
    }

    public Task createTask(TaskResponseDTO taskDTO) throws validationErrorException {

        Task task = new Task(++nextTaskId, taskDTO.getTaskName(), taskDTO.getTaskDescription(), "created");
        validateTask(task);
        tasks.add(task);
        return task;
    }

    public Task getTaskById(Integer id) throws TaskNotFoundException{

        for(Task tk : tasks){
            if(tk.getId().equals(id))
                return tk;
        }
        throw new TaskNotFoundException("Task not found with id:  "+id);
    }

    public Task updateStatus(TaskResponseDTO taskDTO) throws TaskNotFoundException {

        Task task = getTaskById(taskDTO.getId());
        if(task != null) {
            task.setTaskStatus(taskDTO.getTaskStatus());
            return task;
        }
        throw new TaskNotFoundException("Status is not updated because task is not found");
    }

    public Task updateTask(TaskResponseDTO taskDTO) throws TaskNotFoundException {

        Task task = getTaskById(taskDTO.getId());
        if(task == null) {
            throw new TaskNotFoundException("Updated task is not found");
        }
        else{
            task.setTaskStatus(taskDTO.getTaskStatus());
            return task;
        }
    }

    public boolean deleteTask(Integer id) throws TaskNotFoundException {

        try {
            Task task = getTaskById(id);
            tasks.remove(task);
            return true;
        }catch(TaskNotFoundException e){
            throw e;
        }
    }

    public void validateTask(Task task) throws validationErrorException {

        if(task.getTaskName().length() > 50 || task.getTaskName().length() < 1)
            throw new validationErrorException("Task name should be length of atmost 50 characters");
        else if(task.getTaskDescription().length() > 200 || task.getTaskDescription().length() < 1)
            throw new validationErrorException("Task description should be length og atmost 200 characters");
    }
}