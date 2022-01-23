package dukeClasses;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task updateTask(int index, boolean isDone){
        if (isDone) {
            tasks.get(index).setIsDone(true);
        } else {
            tasks.get(index).setIsDone(false);
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList(){
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

}
