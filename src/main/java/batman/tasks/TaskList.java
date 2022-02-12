package batman.tasks;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import batman.exception.DukeException;
import batman.exception.Error;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds tasks depending on what type of task it is.
     *
     * @param command An array of strings containing the
     *                details of a task.
     */
    public static String addTask(String[] command) {
        try {
            String task;
            Task t;
            String description;
            String details;
            task = command[1];
            switch (command[0]) {
            case "deadline":
                description = task.split(" /")[0];
                details = task.split("/by ")[1];
                t = new Deadline(description, details);
                break;
            case "event":
                description = task.split(" /")[0];
                details = task.split("/at ")[1];
                t = new Event(description, details);
                break;
            default:
                description = task;
                t = new Todo(description);
            }
            tasks.add(t);
            return printTask(t);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Missing argument(s) for tasks\n"
                    + "e.g. <task> <desc> /(at or by) <datetime>\n";
        } catch (DateTimeParseException e) {
            return "Get the date format right!\ndd/MM/yyyy HH:mm OR yyyy-MM-dd HH:mm\n";
        }
    }

    /**
     * Deletes an existing task based on the index given.
     *
     * @param index The index of an existing task in the list.
     * @return String object of deleted task and existing list.
     */
    public static String deleteTask(int index) {
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LIST_ERROR);
            }
            Task t = tasks.get(index);
            tasks.remove(index);
            return "Got it. Task removed:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
        } catch (DukeException e) {
            return printList() + e.listError();
        }
    }

    /**
     * Toggles the status of the task.
     *
     * @param action Mark/Unmark task.
     * @param index The index of an existing task in the list.
     * @return StringBuilder object of the task's changed status.
     */
    public static StringBuilder toggleStatus(String action, int index) {
        StringBuilder sb = new StringBuilder();
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LIST_ERROR);
            }
            switch (action) {
            case "mark":
                tasks.get(index).markDone();
                sb.append("Alfred, mark it as done!\n  ")
                        .append(tasks.get(index).toString()).append("\n");
                break;
            default:
                tasks.get(index).unmarkDone();
                sb.append("Make up your mind. Alfred, unmark it!\n  ")
                        .append(tasks.get(index).toString()).append("\n");
                break;
            }
            return sb;
        } catch (DukeException e) {
            return sb.append(printList()).append(e.listError());
        }
    }

    /**
     * Finds tasks using given keyword.
     *
     * @param keyword A word given by user.
     * @return StringBuilder object of the tasks found.
     */
    public static StringBuilder findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(keyword)) {
                temp.add(t);
            }
        }
        if (temp.size() == 0) {
            sb.append("No matching tasks in your list.\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < temp.size(); i++) {
                sb.append(i + 1).append(". ").append(temp.get(i)).append("\n");
            }
        }
        return sb;
    }

    /**
     * Prints all existing tasks.
     *
     * @return String object of task list.
     */
    public static String printList() {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            return "List is empty.\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }

    private static String printTask(Task t) {
        return "Got it. Task added:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
    }
}
