package task;

import exception.JarvisException;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private final Ui ui;

    /**
     * Constructs a TaskList object.
     *
     * @param tasks The initial task list.
     * @param ui For printing messages to the standard output.
     */
    public TaskList(List<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void printTasks() {
        int count = tasks.size();
        if (count == 0) {
            ui.echo("You have no tasks in your list. :-)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(i + 1).append(".\t").append(tasks.get(i));
            if (i + 1 != count) {
                sb.append("\n");
            }
        }
        ui.echo(sb.toString());
    }

    public void markAsDone(HashMap<String, Object> parsedCommand) throws JarvisException {
        checkIfEmpty();
        try {
            Task task = tasks.get((Integer) parsedCommand.get("num"));
            task.markAsDone();
            ui.echo("I've marked the following task as completed:\n\t" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
    }

    public void markAsUndone(HashMap<String, Object> parsedCommand) throws JarvisException {
        checkIfEmpty();
        try {
            Task task = tasks.get((Integer) parsedCommand.get("num"));
            task.markAsUndone();
            ui.echo("I've marked the following task as incomplete:\n\t" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
    }

    public void delete(HashMap<String, Object> parsedCommand) throws JarvisException {
        checkIfEmpty();
        try {
            Task task = tasks.get((Integer) parsedCommand.get("num"));
            tasks.remove(task);
            ui.echo("Understood. I've removed the following task:\n\t"
                    + task + "\n"
                    + "Now you have " + tasks.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
    }

    public void addTodo(HashMap<String, Object> parsedCommand) throws JarvisException {
        Todo todo = new Todo((String) parsedCommand.get("description"));
        tasks.add(todo);
        ui.echo("Got it. I've added this todo:\n\t"
                + todo + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
    }

    public void addDeadline(HashMap<String, Object> parsedCommand) throws JarvisException {
        Deadline deadline = new Deadline(
                (String) parsedCommand.get("description"), (LocalDateTime) parsedCommand.get("date"));
        tasks.add(deadline);
        ui.echo("Got it. I've added this deadline:\n\t"
                + deadline + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
    }

    public void addEvent(HashMap<String, Object> parsedCommand) throws JarvisException {
        Event event = new Event(
                (String) parsedCommand.get("description"), (LocalDateTime) parsedCommand.get("date"));
        tasks.add(event);
        ui.echo("Got it. I've added this event:\n\t"
                + event + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
    }


    private void checkIfEmpty() throws JarvisException {
        if (tasks.size() == 0) {
            throw new JarvisException("You have no tasks in your list.");
        }
    }
}
