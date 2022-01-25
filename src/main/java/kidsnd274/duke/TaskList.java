package kidsnd274.duke;

import java.util.ArrayList;
import java.util.List;

import kidsnd274.duke.taskobjects.Task;

// Just a container for the tasklist
public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean add(Task task) {
        return taskList.add(task);
    }

    public Task remove(int taskNo) {
        return taskList.remove(taskNo);
    }

    public Task get(int taskNo) {
        return taskList.get(taskNo);
    }

    public List<Task> getList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public String listAll() {
        StringBuilder newString = new StringBuilder("Tasklist:\n");
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                newString.append("\n");
            }
            newString.append(i + 1);
            newString.append(". ");
            newString.append(taskList.get(i).getCurrentStatus());
        }
        return newString.toString();
    }

//    public boolean markAsDone(int taskNo) {
//        Task currentTask = taskList.get(taskNo);
//        currentTask.markAsDone();
//        return true;
//    }
//
//    public boolean markAsUndone(int taskNo) {
//        Task currentTask = taskList.get(taskNo);
//        currentTask.markAsUndone();
//        return true;
//    }
}
