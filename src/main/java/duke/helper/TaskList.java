package duke.helper;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();


    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    public Task get(int i){
        return taskList.get(i);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void insert(Task task){
        taskList.add(task);
    }

    public void delete(int i){
        taskList.remove(i);
    }

    public int size(){
        return taskList.size();
    }

}
