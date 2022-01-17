package duke.main;

import duke.Tasks.*;
import java.util.*;

public class TaskList {
    private List<Task> toDoList;

    public TaskList() {
        this.toDoList = new ArrayList<>();
    }

    public void add(Task t) {
        this.toDoList.add(t);
    }
    public Task remove(int index) {
        return this.toDoList.remove(index);
    }

    public int size() {
        return this.toDoList.size();
    }

    public Task get(int i) {
        return this.toDoList.get(i);
    }
}
