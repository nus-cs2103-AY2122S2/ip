package dukeclasses;

import java.util.Scanner;

/**
 * Represent a program that reads commands from user input and logs them into tasks that are
 * stored for tracking whenever the program is booted up.
 */
public class Duke {

    private static final String TEXT_DATA_FILE_PATH = "data.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath String indicating path of permanent storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException errorMessage) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        ParsedCommand parsedCommand = null;
        while (!isExit) {

           try {
               parsedCommand = Parser.parse(sc.nextLine());
           } catch (DukeException errorMessage) {
               ui.showInputError();
               continue;
           }
           if (parsedCommand.equals(null)) {
               continue;
            }

            switch (parsedCommand.getCommand()) {
            case "hi":
                ui.greet();
                break;
            case "bye":
                isExit = true;
                ui.sayBye();
                break;
            case "list":
                ui.listTask(storage.getStorageFilePath());
                break;
            case "mark":
                if (parsedCommand.getIndex() > tasks.getTaskList().size()) {
                    new DukeException();
                    break;
                }
                Task markedTask = tasks.updateTask(parsedCommand.getIndex(), true);
                ui.identifyTask(markedTask);
                try {
                    storage.updateStorage(tasks.getTaskList());
                } catch (DukeException errorMessage) {
                    ui.showStorageError();
                    continue;
                }
                break;
            case "unmark":
                if (parsedCommand.getIndex() > tasks.getTaskList().size()) {
                    new DukeException();
                    continue;
                }

                Task unmarkedTask = tasks.updateTask(parsedCommand.getIndex(), false);
                ui.identifyTask(unmarkedTask);

                try {
                    storage.updateStorage(tasks.getTaskList());
                } catch (DukeException errorMessage) {
                    ui.showStorageError();
                    continue;
                }
                break;
            case "todo":
                ToDo todo = new ToDo(parsedCommand.getTask());

                try {
                storage.appendToStorage(todo);
                } catch (DukeException errorMessage) {
                    ui.showStorageError();
                    continue;
                }

                tasks.addTask(todo);
                ui.newTask(todo, tasks.getTaskList().size());
                break;
            case "event":
                Event event = new Event(parsedCommand.getTask(), parsedCommand.getDueDate());

                try {
                    storage.appendToStorage(event);
                } catch (DukeException errorMessage) {
                    ui.showStorageError();
                    continue;
                }

                tasks.addTask(event);
                ui.newTask(event, tasks.getTaskList().size());
                break;
            case "deadline":
                Deadline deadline = new Deadline(parsedCommand.getTask(), parsedCommand.getDueDate());

                try {
                    storage.appendToStorage(deadline);
                } catch (DukeException errorMessage) {
                    ui.showStorageError();
                    continue;
                }

                tasks.addTask(deadline);
                ui.newTask(deadline, tasks.getTaskList().size());
                break;
            case "delete":

                if (parsedCommand.getIndex() > tasks.getTaskList().size()) {
                    new DukeException();
                    continue;
                }

               Task deletedTask = tasks.deleteTask(parsedCommand.getIndex());

                try {
                    storage.updateStorage(tasks.getTaskList());
                } catch (DukeException errorMessage) {
                    ui.showStorageError();
                    continue;
                }

                ui.deleteTask(deletedTask);
            }
        }
        sc.close();
    }

    /**
     * Initiates the program by calling the driving method run().
     *
     * @param args String array that represents the supplied command line arguments.
     */
    public static void main(String[] args) {
        new Duke(TEXT_DATA_FILE_PATH).run();
    }

}
