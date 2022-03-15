package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Command class, containing the commands' logic for the bot.
 *
 * @author Jet Tan
 */
public class Command {
    /**
     * Lists out all tasks in the TaskList.
     */
    public static void listCommand() {
        if (TaskList.getTasks().size() == 0) {
            System.out.println(Ui.getBorder() + "The list is empty. Why not add some tasks?\n" + Ui.getBorder());
        } else {
            StringBuilder listString = new StringBuilder();
            for (int i = 0; i < TaskList.getTasks().size(); i++) {
                Task t = TaskList.getTasks().get(i);
                listString.append(i + 1).append(".").append(t.toString()).append("\n");
            }
            System.out.println(Ui.getBorder() + listString + Ui.getBorder());
        }
    }

    /**
     * Marks the specified task.
     *
     * @param index index of the specified task to mark.
     */
    public static void markCommand(int index) throws IOException {
        if (index > TaskList.getTasks().size()) {
            throw new InvalidInputException(
                    Ui.getBorder() + "The specified task does not exist.\n" + Ui.getBorder());
        } else if (index < 1) {
            throw new InvalidInputException(Ui.getBorder() + "Selection must be positive.\n" + Ui.getBorder());
        }
        TaskList.mark(index);
        Storage.saveToFile();
    }

    /**
     * Unmarks the specified task.
     *
     * @param index index of the specified task to unmark.
     */
    public static void unmarkCommand(int index) throws IOException {
        if (index > TaskList.getTasks().size()) {
            throw new InvalidInputException(
                    Ui.getBorder() + "The specified task does not exist.\n" + Ui.getBorder());
        } else if (index < 1) {
            throw new InvalidInputException(Ui.getBorder() + "Selection must be positive.\n" + Ui.getBorder());
        }
        TaskList.unmark(index);
        Storage.saveToFile();
    }

    /**
     * Adds a Todo to the TaskList.
     *
     * @param input the user's input to the bot.
     */
    public static void todoCommand(String input) throws IOException {
        String desc = input.replaceFirst("todo", "").trim();
        Todo newTodo = new Todo(desc);
        if (desc.equals("")) {
            throw new EmptyDescException(Ui.getBorder() + "Todo description cannot be empty.\n" + Ui.getBorder());
        }
        ArrayList<Task> tl = TaskList.getTasks();
        for (Task currTask : tl) {
            if (newTodo.toString().equals(currTask.toString())) {
                System.out.println("Duplicate task, not added.");
                return;
            }
        }
        TaskList.getTasks().add(newTodo);
        System.out.println(Ui.successMessage(newTodo));
        Storage.saveToFile();
    }

    /**
     * Adds an event to the TaskList.
     *
     * @param input the user's input to the bot.
     */
    public static void eventCommand(String input) throws IOException {
        if (!input.contains("/at")) {
            throw new InvalidInputException("Usage: event <description> /at <YYYY-MM-DD> <24-hr time, e.g. 2359>");
        }
        String[] descDateTime = input.replaceFirst("event", "").trim().split("/at");
        if (descDateTime.length < 2) {
            throw new InvalidInputException("Usage: event <description> /at <YYYY-MM-DD> <24-hr time, e.g. 2359>");
        }
        String desc = descDateTime[0].trim();
        if (desc.equals("")) {
            throw new EmptyDescException(Ui.getBorder() + "Event description cannot be empty.\n" + Ui.getBorder());
        }
        String dateTime = descDateTime[1].trim();
        Event newEvent = new Event(desc, dateTime);
        ArrayList<Task> tl = TaskList.getTasks();
        for (Task currTask : tl) {
            if (newEvent.toString().equals(currTask.toString())) {
                System.out.println("Duplicate task, not added.");
                return;
            }
        }
        TaskList.getTasks().add(newEvent);
        System.out.println(Ui.successMessage(newEvent));
        Storage.saveToFile();
    }

    /**
     * Adds a Deadline to the TaskList.
     *
     * @param input the user's input to the bot.
     */
    public static void deadlineCommand(String input) throws IOException {
        if (!input.contains("/by")) {
            throw new InvalidInputException(
                    "Usage: deadline <description> /by <YYYY-MM-DD> <24-hr time, e.g. 2359>");
        }
        String[] descTimePair = input.replaceFirst("deadline", "").trim().split("/by");
        if (descTimePair.length < 2) {
            throw new InvalidInputException(
                    "Usage: deadline <description> /by <YYYY-MM-DD> <24-hr time, e.g. 2359>");
        }
        String desc = descTimePair[0].trim();
        if (desc.equals("")) {
            throw new EmptyDescException(
                    Ui.getBorder() + "Deadline description cannot be empty.\n" + Ui.getBorder());
        }
        String dateTime = descTimePair[1].trim();
        Deadline newDeadline = new Deadline(desc, dateTime);
        ArrayList<Task> tl = TaskList.getTasks();
        for (Task currTask : tl) {
            if (newDeadline.toString().equals(currTask.toString())) {
                System.out.println("Duplicate task, not added.");
                return;
            }
        }
        TaskList.getTasks().add(newDeadline);
        System.out.println(Ui.successMessage(newDeadline));
        Storage.saveToFile();
    }

    /**
     * Deletes the specified task.
     *
     * @param index index of the specified task to delete.
     */
    public static void deleteCommand(int index) throws IOException {
        if (index > TaskList.getTasks().size()) {
            throw new InvalidInputException(
                    Ui.getBorder() + "The specified task does not exist.\n" + Ui.getBorder());
        } else if (index < 1) {
            throw new InvalidInputException(Ui.getBorder() + "Selection must be positive.\n" + Ui.getBorder());
        }
        TaskList.delete(index);
        Storage.saveToFile();
    }

    /**
     * Finds the specified task.
     *
     * @param input the user's input to the bot.
     */
    public static void findCommand(String input) {
        String desc = input.replaceFirst("find", "").trim();
        Pattern pattern = Pattern.compile("\\b" + desc + "\\b");
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < TaskList.getTasks().size(); i++) {
            Task t = TaskList.getTasks().get(i);
            Matcher matcher = pattern.matcher(t.getDescription());
            if (matcher.find()) {
                listString.append(i + 1).append(".").append(t).append("\n");
            }
        }
        System.out.println(Ui.getBorder() + "Here are the matching tasks in your list:\n"
                + listString + Ui.getBorder());
    }
}
