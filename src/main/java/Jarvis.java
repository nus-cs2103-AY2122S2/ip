import enums.Command;
import exception.JarvisException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.HashMap;

public class Jarvis {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    private static boolean processNext = true;

    /**
     * Runs the main chat/program loop.
     */
    public static void main(String[] args) throws JarvisException {
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
     *
     * @throws JarvisException If storage initialization fails.
     */
    public static void startup() throws JarvisException {
        storage = new Storage("data/data.txt");
        ui = new Ui();
        tasks = new TaskList(storage.loadData(), ui);
        ui.welcome();
    }

    /**
     * Saves the program state into local storage and closes open resources.
     *
     * @throws JarvisException If save to storage fails.
     */
    public static void shutdown() throws JarvisException {
        processNext = false;
        storage.saveChanges(tasks);
        ui.shutdown();
    }
}
