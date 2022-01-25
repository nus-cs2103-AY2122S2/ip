package task;

import enums.Command;
import exception.JarvisException;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private final Ui ui;

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
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".\t").append(tasks.get(i));
            if (i + 1 != count) {
                sb.append("\n");
            }
        }
        ui.echo(sb.toString());
    }

    public void markAsDone(String[] tokens) throws JarvisException {
        if (tasks.size() == 0) {
            throw new JarvisException("You have no tasks in your list.");
        }

        int num;

        try {
            num = Integer.parseInt(tokens[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a task number to mark as completed.");
        } catch (NumberFormatException e) {
            throw new JarvisException("Please specify the task number numerically.");
        }
        try {
            Task task = tasks.get(num);
            task.markAsDone();
            ui.echo("I've marked the following task as completed:\n\t" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
    }

    public void markAsUndone(String[] tokens) throws JarvisException {
        if (tasks.size() == 0) {
            throw new JarvisException("You have no tasks in your list.");
        }

        int num;

        try {
            num = Integer.parseInt(tokens[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a task number to mark as incomplete.");
        } catch (NumberFormatException e) {
            throw new JarvisException("Please specify the task number numerically.");
        }
        try {
            Task task = tasks.get(num);
            task.markAsUndone();
            ui.echo("I've marked the following task as incomplete:\n\t" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
    }

    public void delete(String[] tokens) throws JarvisException {
        if (tasks.size() == 0) {
            throw new JarvisException("You have no tasks in your list.");
        }

        int num;

        try {
            num = Integer.parseInt(tokens[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a task number to delete.");
        } catch (NumberFormatException e) {
            throw new JarvisException("Please specify the task number numerically.");
        }
        try {
            Task task = tasks.remove(num);
            ui.echo("Understood. I've removed the following task:\n\t"
                    + task + "\n"
                    + "Now you have " + tasks.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("Please specify a valid task number (between 1 to " + tasks.size() + " inclusive).");
        }
    }

    public void addTodo(String input) throws JarvisException {
        String description;
        try {
            description = input.trim().substring(Command.TODO.toString().length() + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("The description of the todo cannot be empty.");
        }

        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.echo("Got it. I've added this todo:\n\t"
                + todo + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
    }

    public void addDeadline(String input) throws JarvisException {
        String description;
        String[] split = input.split("/by");

        try {
            description = split[0].trim().substring(Command.DEADLINE.toString().length() + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("The description of the deadline cannot be empty.");
        }
        if (split.length == 1) {
            throw new JarvisException("Please specify the date of the deadline (usage: `deadline <description> /by <date>`).");
        }
        if (split[1].trim().equals("")) {
            throw new JarvisException("The date of the deadline cannot be empty.");
        }

        Deadline deadline = new Deadline(description, parseDateTime(split[1]));
        tasks.add(deadline);
        ui.echo("Got it. I've added this deadline:\n\t"
                + deadline + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
    }

    public void addEvent(String input) throws JarvisException {
        String description;
        String[] split = input.split("/at");

        try {
            description = split[0].trim().substring(Command.EVENT.toString().length() + 1);
        } catch (IndexOutOfBoundsException e) {
            throw new JarvisException("The description of the event cannot be empty.");
        }
        if (split.length == 1) {
            throw new JarvisException("Please specify the date of the event (usage: `event <description> /at <date>`).");
        }
        if (split[1].trim().equals("")) {
            throw new JarvisException("The date of the event cannot be empty.");
        }

        Event event = new Event(description, parseDateTime(split[1]));
        tasks.add(event);
        ui.echo("Got it. I've added this event:\n\t"
                + event + "\n"
                + "Now you have " + tasks.size() + " task(s) in your list.");
    }

    private LocalDateTime parseDateTime(String str) throws JarvisException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(str.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new JarvisException("Please specify the date format as follows: 2022-12-25 2359");
        }
    }
}
