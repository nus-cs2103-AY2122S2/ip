package Tasks;

import SparkExceptions.TaskModificationExceptions.TaskAlreadyMarked;
import SparkExceptions.TaskModificationExceptions.TaskAlreadyUnMarked;
import SparkExceptions.TaskModificationExceptions.TaskNotFoundException;

import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public void addToDo(String name) {
        ToDo toDo = new ToDo(name);
        tasks.add(toDo);

        System.out.println("Got it, I've added this todo:");
        System.out.format("   %s\n", toDo);

        showNumberOfTasks();
    }

    public void addDeadline(String name, String by) {
        Deadline deadline = new Deadline(name, by);
        tasks.add(deadline);

        System.out.println("Got it, I've added this deadline:");
        System.out.format("   %s\n", deadline);

        showNumberOfTasks();
    }

    public void addEvent(String name, String at) {
        Event event = new Event(name, at);
        tasks.add(event);

        System.out.println("Got it, I've added this event:");
        System.out.format("   %s\n", event);

        showNumberOfTasks();
    }

    public void markTask(String param) {
        try {
            int taskId = Integer.parseInt(param);
            int index = taskId - 1;
            Task t = getTaskByIndex(index);
            t.mark();

            System.out.println("Awesome! I've marked this task as done:");
            System.out.format("   %s\n", t);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("That doesn't seem like a number. Task numbers are integers!");
        } catch (TaskAlreadyMarked e) {
            System.out.println(e.getMessage());
        }
    }

    public void unMarkTask(String param) {
        try {
            int taskId = Integer.parseInt(param);
            int index = taskId - 1;
            Task t = getTaskByIndex(index);
            t.unMark();

            System.out.println("Okay, I've marked this task as not done yet:");
            System.out.format("   %s\n", t);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("That doesn't seem like a number. Task numbers are integers!");
        } catch (TaskAlreadyUnMarked e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String param) {
        try {
            int taskId = Integer.parseInt(param);
            int index = taskId - 1;
            Task t = getTaskByIndex(index);

            System.out.println("Noted. I've removed this task:");
            System.out.format("   %s\n", t);

            tasks.remove(t);
            showNumberOfTasks();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("That doesn't seem like a number. Task numbers are integers!");
        }
    }

    public void showTaskList() {
        // if there are no tasks, inform the user
        if (tasks.size() == 0) {
            System.out.println("No tasks found! (trust me, I've looked everywhere)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("    %d. %s\n", i + 1, tasks.get(i));
            }
        }
    }

    public void showNumberOfTasks() {
        System.out.format("Now you have %d tasks in the list.\n", this.tasks.size());
    }

    private Task getTaskByIndex(int index) throws TaskNotFoundException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
