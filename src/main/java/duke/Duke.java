package duke;

import commands.Command;
import mytasks.TaskList;

/**
 * An application that tracks the tasks that you must complete.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Command command;

    /**
     * Initializes the application by loading files and checking for errors.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks.loadList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Start the application.
     */
    public String run(String inputText) {
        command = new Command();
        String temp = command.allTasks(inputText, tasks);
        storage.saveTasks(tasks.getList());
        return temp;
    }
}

