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
    private TaskList taskList;
    private Storage storage;

    /** Boolean variable for the while loop. */
    private boolean hasNext = true;

    /**
     * Constructs a new Jarvis instance.
     */
    public Jarvis() {}

    /**
     * Runs the main chat/program loop.
     *
     * @return The response given based on the input.
     */
    public String getResponse(String input) {
        while (hasNext) {
            try {
                HashMap<String, Object> parsedCommand = Parser.parseInput(input);
                switch (Command.valueOf((String) parsedCommand.get("command"))) {
                case BYE:
                    return shutdown();
                case LIST:
                    return taskList.printTaskList();
                case MARK:
                    return taskList.markAsDone(parsedCommand);
                case UNMARK:
                    return taskList.markAsUndone(parsedCommand);
                case DELETE:
                    return taskList.delete(parsedCommand);
                case TODO:
                    return taskList.addTodo(parsedCommand);
                case DEADLINE:
                    return taskList.addDeadline(parsedCommand);
                case EVENT:
                    return taskList.addEvent(parsedCommand);
                case FIND:
                    return taskList.findTasks(parsedCommand);
                case SNOOZE:
                    return taskList.snoozeTask(parsedCommand);
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
    public String startup() {
        try {
            storage = new Storage("data/data.txt");
            ui = new Ui();
            taskList = new TaskList(storage.loadData());
            return ui.welcome();
        } catch (JarvisException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves the program state into local storage and closes open resources.
     */
    public String shutdown() {
        try {
            hasNext = false;
            storage.saveChanges(taskList);
            return ui.shutdown();
        } catch (JarvisException e) {
            return e.getMessage();
        }
    }
}
