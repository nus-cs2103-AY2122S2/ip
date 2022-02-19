package jarvis;

import java.time.LocalDate;

/**
 * The Parser program implements an application which parses a user's input and acts accordingly.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class Parser {
    /**
     * Parses the user input and allows J.A.R.V.I.S to act accordingly.
     *
     * @param input user's String input
     * @param tasks a TaskList to store all tasks
     * @param storage a Storage object which saves and updates a todo list file
     * @return String J.A.R.V.I.S response to user input
     */
    public static String parse(String input, TaskList tasks, Storage storage) {
        String[] command = input.split(" ", 2);
        switch (command[0]) {
        case "list":
            return tasks.list();
        case "todo":
            try {
                if (command[1].isEmpty()) {
                    throw new DukeException("Do what, sir?");
                }
                Todo task = new Todo(command[1]);
                storage.create(task);
                return tasks.add(task);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "deadline":
            String[] deadline = command[1].split(" /by ");
            try {
                if (deadline.length < 2) {
                    throw new DukeException("Invalid deadline task, sir.");
                }
                LocalDate date = LocalDate.parse(deadline[1]);
                Deadline task = new Deadline(deadline[0], date);
                storage.create(task);
                return tasks.add(task);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "event":
            String[] event = command[1].split(" /at ");
            try {
                if (event.length < 2) {
                    throw new DukeException("Invalid event task, sir.");
                }
                Event task = new Event(event[0], event[1]);
                storage.create(task);
                return tasks.add(task);
            } catch (DukeException e) {
                return e.getMessage();
            }
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "remove":
            int id = Integer.parseInt(command[1]);
            storage.update(id, command[0]);
            return tasks.update(id, command[0]);
        case "find":
            return tasks.find(command[1]);
        case "bye":
            return Ui.bye();
        default:
            return Ui.unknownCommand("\"" + input + "\"");
        }
    }
}
