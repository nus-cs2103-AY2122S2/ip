import java.util.ArrayList;
import java.util.List;

//TaskList: contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<String> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            this.taskList.add(new Task(taskList.get(i)));
        }
    }



    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void add(String description) {
        this.taskList.add(new Task(description));
    }

    public void remove(int index) {
        this.taskList.remove(index);

    }

    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        this.taskList.get(index).unmarkAsDone();
    }
}

