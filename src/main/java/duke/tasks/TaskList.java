package duke.tasks;

import java.io.Serializable;
import java.util.ArrayList;

import duke.tasks.*;
import duke.exceptions.DukeException;

public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task remove(int idx) throws DukeException {
        try {
            return tasks.remove(idx);
        } catch (Exception e) {
            throw new DukeException("Invalid input! This task number does not exist.");
        }
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public Task markTask(int idx, boolean isMark) throws DukeException {
        try {
            Task task = tasks.get(idx);
            task.setCompleted(isMark);
            return task;
        } catch (Exception e) {
            throw new DukeException("Invalid input! This task number does not exist.");
        }
    }

    public Todo addTodoTask(String[] token) throws DukeException {
        Todo task = Todo.createTask(token);
        tasks.add(task);
        return task;
    }

    public Deadline addDeadlineTask(String[] token) throws DukeException {
        Deadline task = Deadline.createTask(token);
        tasks.add(task);
        return task;
    }

    public Event addEventTask(String[] token) throws DukeException {
        Event task = Event.createTask(token);
        tasks.add(task);
        return task;
    }

    public ArrayList<String> search(String searchString) {
        ArrayList<String> taskSet = new ArrayList<>();
        for (Task task : tasks) {
            if (task.item.contains(searchString)) {
                taskSet.add(task.toString());
            }
        }
        return taskSet;
    }
}
