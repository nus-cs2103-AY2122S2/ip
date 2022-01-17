package Tasks;

import SparkExceptions.TaskModificationExceptions.TaskNotFoundException;

import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    private int generateNewTaskId() {
        return tasks.size() + 1; // taskId numbering starts from 1
    }

    public void addToDo(String name) {
        int taskId = generateNewTaskId();
        ToDo toDo = new ToDo(taskId, name);
        tasks.add(toDo);

        showNumberOfTasks();
    }

    public void addDeadline(String name, String by) {
        int taskId = generateNewTaskId();
        Deadline deadline = new Deadline(taskId, name, by);
        tasks.add(deadline);

        showNumberOfTasks();
    }

    public void addEvent(String name, String at) {
        int taskId = generateNewTaskId();
        Event event = new Event(taskId, name, at);
        tasks.add(event);

        showNumberOfTasks();
    }

    public void markTask(int taskId) {
        try {
            Task t = findTaskById(taskId);
            t.mark();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unMarkTask(int taskId) {
        try {
            Task t = findTaskById(taskId);
            t.unMark();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int taskId) {
        try {
            Task t = findTaskById(taskId);

            System.out.println("Noted. I've removed this task:");
            System.out.println(t);

            tasks.remove(t);
            showNumberOfTasks();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        // if there are no tasks, inform the user
        if (tasks.size() == 0) {
            System.out.println("No tasks found! (trust me, I've looked everywhere)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }

    public void showNumberOfTasks() {
        System.out.format("Now you have %d tasks in the list.\n", this.tasks.size());
    }

    private Task findTaskById(int taskId) throws TaskNotFoundException {
        Task match = null;

        for (Task t : tasks) {
            if (t.taskId == taskId) {
                match = t;
                break;
            }
        }

        if (match == null) {
            throw new TaskNotFoundException();
        }

        return match;
    }
}
