package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> tasks) {
        this.tasks = new ArrayList<>();
        tasks.forEach(entry -> {
            //parse entries
            try {
                this.tasks.add(Parser.parseStringToTask(entry));
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });
    }


    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public Task getLast() {
        return this.tasks.get(this.tasks.size() - 1);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}