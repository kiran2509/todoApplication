package com.example.todoApp;

import com.example.todoApp.dto.TaskResponseDTO;
import com.example.todoApp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskBuilder {

    public static List<TaskResponseDTO> convertTaskListToDTOList(List<Task> tasks) {
        List<TaskResponseDTO> taskDTOList = new ArrayList<>();
        if(tasks.isEmpty())
            return taskDTOList;
        for(Task task : tasks){
            taskDTOList.add( new TaskResponseDTO(task.getId(), task.getTaskName(), task.getTaskDescription(), task.getTaskStatus()));
        }
        return taskDTOList;
    }

    public static TaskResponseDTO convertTaskToTaskDTO(Task task) {
        return new TaskResponseDTO(task.getId(), task.getTaskName(), task.getTaskDescription(), task.getTaskStatus());
    }

}
