package duke.helptool;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private final ArrayList<Task> TaskList;

    public TaskList(ArrayList<Task> arrayList){
        this.TaskList = Objects.requireNonNullElseGet(arrayList, ArrayList::new);
    }

    public TaskList() {
        this.TaskList = new ArrayList<>();
    }


    public void addTask(Task task){
        this.TaskList.add(task);
    }

    public void delete(int index){
        this.TaskList.remove(index);
    }

    public Task getTask(int index){
        return this.TaskList.get(index);
    }

    public int getSize(){
        return this.TaskList.size();
    }


}
