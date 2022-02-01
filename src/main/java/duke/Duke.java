package duke;

import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * The Duke program implements a simple task bot application with CRUD functionality.
 * The program can add three different types of tasks (todo, deadline, event), mark tasks as done, and
 * delete tasks.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke. This instantiates UI and storage objects (one of each),and loads Tasks from a
     * specified filePath into tasks.
     * If there is an error with loading Tasks from the specified file, it initializes tasks to be an empty
     * TaskList.
     *
     * @param filePath path of the storage file from the current directory
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Handles the execution and main logic of the Duke program. It polls for user input continuously, parses
     * the user input and displays appropriate messages until user input is "bye", upon which it displays a
     * goodbye message and terminates the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                String command = Parser.parse(input);

                if (command.equals("")) {
                    ui.showCommandMessage(command, tasks);
                    continue;
                }
                input = Parser.handleInput(input);

                try {
                    switch (command) {
                    case "list":
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "do":
                        int i = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                        tasks.get(i).markComplete();
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "undo":
                        int j = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                        tasks.get(j).markIncomplete();
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "delete":
                        int k = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                        tasks.remove(k);
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "todo":
                        Todo t = new Todo(input);
                        tasks.add(t);
                        ui.showCommandMessage(command, tasks);
                        System.out.println(t);
                        break;
                    case "deadline":
                        String datetime = input.replaceAll(".* by ", "");
                        input = input.replaceAll(" by .*", "");
                        Deadline d = new Deadline(input, datetime);
                        tasks.add(d);
                        ui.showCommandMessage(command, tasks);
                        System.out.println(d);
                        break;
                    case "event":
                        String time = input.replaceAll(".* at ", "");
                        input = input.replaceAll(" at .*", "");
                        Event e = new Event(input, time);
                        tasks.add(e);
                        ui.showCommandMessage(command, tasks);
                        System.out.println(e);
                        break;
                    }

                    if (!command.equals("list") && !command.equals("bye")) {
                        storage.save(tasks);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }

                isExit = command.equals("bye");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method that starts the program by calling a new instance of Duke with a specified file path.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Duke("../../../data/tasks.txt").run();
    }

}
