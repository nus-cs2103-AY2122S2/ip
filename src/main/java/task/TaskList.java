package task;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import exception.JarvisException;

public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructs a TaskList object.
     *
     * @param taskList The initial task list.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the current task list as a formatted string.
     *
     * @return The formatted task list.
     */
    public String printTaskList() {
        int count = taskList.size();
        if (count == 0) {
            return "You have no tasks in your list. :-)";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(i + 1).append(".\t").append(taskList.get(i));
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
            Task task = taskList.get((Integer) parsedCommand.get("num"));
            task.markAsDone();
            return "I've marked the following task as completed:\n\t" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to "
                    + taskList.size() + " inclusive).");
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
            Task task = taskList.get((Integer) parsedCommand.get("num"));
            task.markAsUndone();
            return "I've marked the following task as incomplete:\n\t" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to "
                    + taskList.size() + " inclusive).");
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
            Task task = taskList.get((Integer) parsedCommand.get("num"));
            taskList.remove(task);
            return "Understood. I've removed the following task:\n\t"
                    + task + "\n"
                    + "Now you have " + taskList.size() + " task(s) in your list.";
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to "
                    + taskList.size() + " inclusive).");
        }
    }

    /**
     * Creates and adds a Todo task to the task list.
     *
     * @param parsedCommand The input command containing the task.
     */
    public String addTodo(HashMap<String, Object> parsedCommand) {
        Todo todo = new Todo((String) parsedCommand.get("description"));
        taskList.add(todo);
        return "Got it. I've added this todo:\n\t"
                + todo + "\n"
                + "Now you have " + taskList.size() + " task(s) in your list.";
    }

    /**
     * Creates and adds a Deadline task to the task list.
     *
     * @param parsedCommand The input command containing the task.
     */
    public String addDeadline(HashMap<String, Object> parsedCommand) {
        Deadline deadline = new Deadline(
                (String) parsedCommand.get("description"), (LocalDateTime) parsedCommand.get("date"));
        taskList.add(deadline);
        return "Got it. I've added this deadline:\n\t"
                + deadline + "\n"
                + "Now you have " + taskList.size() + " task(s) in your list.";
    }

    /**
     * Creates and adds an Event task to the task list.
     *
     * @param parsedCommand The input command containing the task.
     */
    public String addEvent(HashMap<String, Object> parsedCommand) {
        Event event = new Event(
                (String) parsedCommand.get("description"), (LocalDateTime) parsedCommand.get("date"));
        taskList.add(event);
        return "Got it. I've added this event:\n\t"
                + event + "\n"
                + "Now you have " + taskList.size() + " task(s) in your list.";
    }

    /**
     * Searches the task list and prints those that contains the keyword.
     *
     * @param parsedCommand The input command containing the keyword.
     */
    public String findTasks(HashMap<String, Object> parsedCommand) {
        String keyword = (String) parsedCommand.get("keyword");
        List<Task> filtered = taskList.stream()
                .filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < filtered.size(); i++) {
            sb.append(i + 1).append(".\t").append(taskList.get(i));
            if (i + 1 != filtered.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Snoozes a deadline or event task by one day.
     *
     * @param parsedCommand The input command containing the task number.
     * @throws JarvisException If task number is invalid.
     */
    public String snoozeTask(HashMap<String, Object> parsedCommand) throws JarvisException {
        checkIfEmpty();
        try {
            Task task = taskList.get((Integer) parsedCommand.get("num"));
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                deadline.snooze();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                event.snooze();
            } else {
                throw new JarvisException("Only deadlines and events can be snoozed.");
            }
            return "I've snoozed the following task by one day:\n\t" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to "
                    + taskList.size() + " inclusive).");
        }
    }

    /**
     * Checks if the current task list is empty.
     *
     * @throws JarvisException If the task list is empty.
     */
    private void checkIfEmpty() throws JarvisException {
        if (taskList.size() == 0) {
            throw new JarvisException("You have no tasks in your list.");
        }
    }
}
