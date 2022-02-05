package duke;

import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
    public static void parse(String input, Scanner sc, TaskList tasks, Storage storage) {
        while (!input.equalsIgnoreCase("bye")) {
            Ui.showLine();
            int id;
            switch (input) {
            case "list":
                tasks.list();
                break;
            case "todo":
                sc.reset();
                try {
                    input = sc.nextLine().strip();
                    if (input.isEmpty()) {
                        throw new DukeException("Do what, sir?");
                    }
                    Todo task = new Todo(input);
                    tasks.add(task);
                    storage.create(task);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            case "deadline":
                sc.reset();
                input = sc.nextLine().strip();
                String[] deadline = input.split(" /by ");
                try {
                    if (deadline.length < 2) {
                        throw new DukeException("Invalid deadline task, sir.");
                    }
                    LocalDate date = LocalDate.parse(deadline[1]);
                    Deadline task = new Deadline(deadline[0], date);
                    tasks.add(task);
                    storage.create(task);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            case "event":
                sc.reset();
                input = sc.nextLine().strip();
                String[] event = input.split(" /at ");
                try {
                    if (event.length < 2) {
                        throw new DukeException("Invalid event task, sir.");
                    }
                    Event task = new Event(event[0], event[1]);
                    tasks.add(task);
                    storage.create(task);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            case "mark":
                // Fallthrough
            case "unmark":
                // Fallthrough
            case "remove":
                id = sc.nextInt();
                storage.update(id, input);
                tasks.update(id, input);
                break;
            default :
                sc.reset();
                Ui.unknownCommand(sc.nextLine());
                break;
            }
            Ui.showLine();
            input = sc.next();
        }
    }
}
