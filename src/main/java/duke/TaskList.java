package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task removeTask(int index) throws Exception_handler {
        if (index < 0 || index >= taskList.size()) {
            throw new Exception_handler("Invalid index");
        }
        return taskList.remove(index - 1);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

//    public Task removeTask(int index){
//        return taskList.remove(index);
//    }

    public List<Task> getListOfTasks(){
        return taskList;
    }

    public int getSize(){
        return taskList.size();
    }
}