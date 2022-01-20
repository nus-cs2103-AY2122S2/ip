package Tasks;

import SparkExceptions.FormatExceptions.EmptyDateException;
import SparkExceptions.FormatExceptions.EmptyTitleException;
import SparkExceptions.TaskModificationExceptions.InvalidTaskIdException;
import SparkExceptions.TaskModificationExceptions.TaskAlreadyMarked;
import SparkExceptions.TaskModificationExceptions.TaskAlreadyUnMarked;
import SparkExceptions.TaskModificationExceptions.TaskNotFoundException;

import java.util.*;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public void addToDo(String[] tokens) throws EmptyTitleException {
        List<String> words = new ArrayList<>(Arrays.asList(tokens).subList(1, tokens.length));
        String title = String.join(" ", words);
        if (title.equals("")) { // ToDo must have a title
            throw new EmptyTitleException();
        }

        ToDo toDo = new ToDo(title);
        tasks.add(toDo);

        System.out.println("Got it, I've added this todo:");
        System.out.format("   %s\n", toDo);

        showNumberOfTasks();
    }

    public void addDeadline(String[] tokens) throws EmptyTitleException, EmptyDateException {
        List<String> firstHalf = new ArrayList<>();
        List<String> secondHalf = new ArrayList<>();

        boolean inSecondHalf = false;
        for (int i=1; i<tokens.length; i++) {
            if (tokens[i].equals("/by")) {
                inSecondHalf = true;
            } else if (!inSecondHalf) {
                firstHalf.add(tokens[i]);
            } else {
                secondHalf.add(tokens[i]);
            }
        }

        String title = String.join(" ", firstHalf);
        String by = String.join(" ", secondHalf);

        // Deadline must have a title
        if (title.equals("")) {
            throw new EmptyTitleException();
        }

        // Deadline must have a date
        if (by.equals("")) {
            throw new EmptyDateException();
        }

        Deadline deadline = new Deadline(title, by);
        tasks.add(deadline);

        System.out.println("Got it, I've added this deadline:");
        System.out.format("   %s\n", deadline);

        showNumberOfTasks();
    }

    public void addEvent(String[] tokens) throws EmptyTitleException, EmptyDateException {
        List<String> firstHalf = new ArrayList<>();
        List<String> secondHalf = new ArrayList<>();

        boolean inSecondHalf = false;
        for (int i=1; i<tokens.length; i++) {
            if (tokens[i].equals("/at")) {
                inSecondHalf = true;
            } else if (!inSecondHalf) {
                firstHalf.add(tokens[i]);
            } else {
                secondHalf.add(tokens[i]);
            }
        }

        String title = String.join(" ", firstHalf);
        String at = String.join(" ", secondHalf);

        // Event must have a title
        if (title.equals("")) {
            throw new EmptyTitleException();
        }

        // Event must have a date
        if (at.equals("")) {
            throw new EmptyDateException();
        }

        Event event = new Event(title, at);
        tasks.add(event);

        System.out.println("Got it, I've added this event:");
        System.out.format("   %s\n", event);

        showNumberOfTasks();
    }

    public void markTask(String[] tokens) throws TaskNotFoundException, TaskAlreadyMarked, InvalidTaskIdException {
        int taskId = getTaskId(tokens[1]);

        int index = taskId - 1;
        Task t = getTaskByIndex(index);
        t.mark();

        System.out.println("Awesome! I've marked this task as done:");
        System.out.format("   %s\n", t);
    }

    public void unMarkTask(String[] tokens) throws TaskNotFoundException, TaskAlreadyUnMarked, InvalidTaskIdException {
        int taskId = getTaskId(tokens[1]);

        int index = taskId - 1;
        Task t = getTaskByIndex(index);
        t.unMark();

        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.format("   %s\n", t);
    }

    public void deleteTask(String[] tokens) throws TaskNotFoundException, InvalidTaskIdException {
        int taskId = getTaskId(tokens[1]);

        int index = taskId - 1;
        Task t = getTaskByIndex(index);

        System.out.println("Noted. I've removed this task:");
        System.out.format("   %s\n", t);

        tasks.remove(t);

        showNumberOfTasks();
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

    private void showNumberOfTasks() {
        System.out.format("Now you have %d tasks in the list.\n", this.tasks.size());
    }

    private Task getTaskByIndex(int index) throws TaskNotFoundException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    private int getTaskId(String input) throws InvalidTaskIdException {
        int taskId;

        try {
            taskId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIdException(input);
        }

        return taskId;
    }
}
