package task;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import exception.JarvisException;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a TaskList object.
     *
     * @param tasks The initial task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the current task list as a formatted string.
     *
     * @return The formatted task list.
     */
    public String printTasks() {
        int count = tasks.size();
        if (count == 0) {
            return "You have no tasks in your list. :-)";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(i + 1).append(".\t").append(tasks.get(i));
            if (i + 1 != count) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param parsedCommand The input command containing the task number.
     * @throws JarvisException If task number is invalid.
     */
    public String markAsDone(HashMap<String, Object> parsedCommand) throws JarvisException {
        checkIfEmpty();
        try {
            Task task = tasks.get((Integer) parsedCommand.get("num"));
            task.markAsDone();
            return "I've marked the following task as completed:\n\t" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to "
                    + tasks.size() + " inclusive).");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param parsedCommand The input command containing the task number.
     * @throws JarvisException If task number is invalid.
     */
    public String markAsUndone(HashMap<String, Object> parsedCommand) throws JarvisException {
        checkIfEmpty();
        try {
            Task task = tasks.get((Integer) parsedCommand.get("num"));
            task.markAsUndone();
            return "I've marked the following task as incomplete:\n\t" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to "
                    + tasks.size() + " inclusive).");
        }
    }

    /**
     * Deletes a task.
     *
     * @param parsedCommand The input command containing the task number.
     * @throws JarvisException If task number is invalid.
     */
    public String delete(HashMap<String, Object> parsedCommand) throws JarvisException {
        checkIfEmpty();
        try {
            Task task = tasks.get((Integer) parsedCommand.get("num"));
            tasks.remove(task);
            return "Understood. I've removed the following task:\n\t"
                    + task + "\n"
                    + "Now you have " + tasks.size() + " task(s) in your list.";
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to "
                    + tasks.size() + " inclusive).");
        }
    }

    /**
     * Creates and adds a Todo task to the task list.
     *
     * @param parsedCommand The input command containing the task.
     */
    public String addTodo(HashMap<String, Object> parsedCommand) {
        Todo todo = new Todo((String) parsedCommand.get("description"));
        tasks.add(todo);
        return "Got it. I've added this todo:\n\t"
                + todo + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.";
    }

    /**
     * Creates and adds a Deadline task to the task list.
     *
     * @param parsedCommand The input command containing the task.
     */
    public String addDeadline(HashMap<String, Object> parsedCommand) {
        Deadline deadline = new Deadline(
                (String) parsedCommand.get("description"), (LocalDateTime) parsedCommand.get("date"));
        tasks.add(deadline);
        return "Got it. I've added this deadline:\n\t"
                + deadline + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.";
    }

    /**
     * Creates and adds an Event task to the task list.
     *
     * @param parsedCommand The input command containing the task.
     */
    public String addEvent(HashMap<String, Object> parsedCommand) {
        Event event = new Event(
                (String) parsedCommand.get("description"), (LocalDateTime) parsedCommand.get("date"));
        tasks.add(event);
        return "Got it. I've added this event:\n\t"
                + event + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.";
    }

    /**
     * Searches the task list and prints those that contains the keyword.
     *
     * @param parsedCommand The input command containing the keyword.
     */
    public String findTasks(HashMap<String, Object> parsedCommand) {
        String keyword = (String) parsedCommand.get("keyword");
        List<Task> filtered = tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < filtered.size(); i++) {
            sb.append(i + 1).append(".\t").append(tasks.get(i));
            if (i + 1 != filtered.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Checks if the current task list is empty.
     *
     * @throws JarvisException If the task list is empty.
     */
    private void checkIfEmpty() throws JarvisException {
        if (tasks.size() == 0) {
            throw new JarvisException("You have no tasks in your list.");
        }
    }
}
