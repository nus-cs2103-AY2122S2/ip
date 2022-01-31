package jarvis;

import java.util.HashMap;

import enums.Command;
import exception.JarvisException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Jarvis {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private boolean processNext = true;

    public Jarvis() {
        startup();
    }

    /**
     * Runs the main chat/program loop.
     *
     * @return The response given based on the input.
     */
    public String getResponse(String input) {
        while (processNext) {
            try {
                HashMap<String, Object> parsedCommand = Parser.parseInput(input);
                switch (Command.valueOf((String) parsedCommand.get("command"))) {
                case BYE:
                    return shutdown();
                case LIST:
                    return tasks.printTasks();
                case MARK:
                    return tasks.markAsDone(parsedCommand);
                case UNMARK:
                    return tasks.markAsUndone(parsedCommand);
                case DELETE:
                    return tasks.delete(parsedCommand);
                case TODO:
                    return tasks.addTodo(parsedCommand);
                case DEADLINE:
                    return tasks.addDeadline(parsedCommand);
                case EVENT:
                    return tasks.addEvent(parsedCommand);
                case FIND:
                    return tasks.findTasks(parsedCommand);
                default:
                    break;
                }
            } catch (IllegalArgumentException e) {
                return "I'm afraid I don't understand your request.";
            } catch (JarvisException je) {
                return "I'm afraid I wasn't able to fulfill your request.\n" + je.getMessage();
            }
        }
        return "I'm afraid I wasn't able to fulfill your request.";
    }

    /**
     * Initializes the UI and loads the data file in storage into the program.
     */
    public void startup() {
        try {
            storage = new Storage("data/data.txt");
            ui = new Ui();
            tasks = new TaskList(storage.loadData());
            ui.welcome();
        } catch (JarvisException e) {
            ui.echo(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Saves the program state into local storage and closes open resources.
     */
    public String shutdown() {
        try {
            processNext = false;
            storage.saveChanges(tasks);
            return ui.shutdown();
        } catch (JarvisException e) {
            return e.getMessage();
        }
    }
}
