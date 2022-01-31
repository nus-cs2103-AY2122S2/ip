package jarvis;

import java.util.HashMap;

import enums.Command;
import exception.JarvisException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Jarvis {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    private static boolean processNext = true;

    /**
     * Runs the main chat/program loop.
     */
    public void getResponse() {
        startup();

        while (processNext) {
            try {
                String input = ui.readCommand();
                HashMap<String, Object> parsedCommand = Parser.parseInput(input);
                switch (Command.valueOf((String) parsedCommand.get("command"))) {
                case BYE:
                    shutdown();
                    return;
                case LIST:
                    tasks.printTasks();
                    break;
                case MARK:
                    tasks.markAsDone(parsedCommand);
                    break;
                case UNMARK:
                    tasks.markAsUndone(parsedCommand);
                    break;
                case DELETE:
                    tasks.delete(parsedCommand);
                    break;
                case TODO:
                    tasks.addTodo(parsedCommand);
                    break;
                case DEADLINE:
                    tasks.addDeadline(parsedCommand);
                    break;
                case EVENT:
                    tasks.addEvent(parsedCommand);
                    break;
                case FIND:
                    tasks.findTasks(parsedCommand);
                    break;
                default:
                    break;
                }
            } catch (IllegalArgumentException e) {
                ui.echo("I'm afraid I don't understand your request.");
            } catch (JarvisException je) {
                ui.echo("I'm afraid I wasn't able to fulfill your request.\n" + je.getMessage());
            }
        }
    }

    /**
     * Initializes the UI and loads the data file in storage into the program.
     */
    public void startup() {
        try {
            storage = new Storage("data/data.txt");
            ui = new Ui();
            tasks = new TaskList(storage.loadData(), ui);
            ui.welcome();
        } catch (JarvisException e) {
            ui.echo(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Saves the program state into local storage and closes open resources.
     */
    public void shutdown() {
        try {
            processNext = false;
            storage.saveChanges(tasks);
            ui.shutdown();
        } catch (JarvisException e) {
            ui.echo(e.getMessage());
            System.exit(0);
        }
    }
}
