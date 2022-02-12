package main.duke;

import main.duke.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TaskList {
    private Stack<ArrayList<Task>> previousTasks;
    private ArrayList<Task> Tasks;

    public TaskList() {
        this.Tasks = new ArrayList<>();
        this.previousTasks = new Stack<>();
    }

    public Task getTask(int taskIndex) {
        return this.Tasks.get(taskIndex);
    }

    public void addTask(Task newTask) {
        this.Tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        this.Tasks.remove(taskIndex);
    }

    public int getTasksCount() {
        return this.Tasks.size();
    }

    public void undo() throws DukeException {
        if (previousTasks.size() == 1) {
            throw new DukeException("There is nothing to undo.");
        }
        this.previousTasks.pop();
        if (previousTasks.size() != 1) {
            this.Tasks = this.previousTasks.pop();
        } else {
            this.Tasks = this.previousTasks.peek();
        }
    }

    public void updateStack() {
        System.out.println(previousTasks.size());
        ArrayList<Task> oldTasks = new ArrayList<>();
        for (int i = 0; i < this.getTasksCount(); i++) {
            oldTasks.add(this.getTask(i).clone());
        }
        this.previousTasks.add(oldTasks);
        for (ArrayList al : previousTasks) {
            System.out.println(Arrays.toString(al.toArray()));
        }
    }

    public String taskCountToString() {
        return String.format("Now you have %d task(s) in the list.", this.getTasksCount());
    }
}
