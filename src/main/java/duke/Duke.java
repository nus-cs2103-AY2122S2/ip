package duke;

import java.util.Scanner;

import mytasks.TaskList;

/**
 * An application that tracks the tasks that you must complete.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the application by loading files and checking for errors.
     */
    public Duke(String filePath) {
        ui = new Ui();
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
    public void run() {
        Scanner input = new Scanner(System.in);
        String inputText = "";

        while (!inputText.equals("bye")) {
            inputText = input.nextLine();

            ui.allTasks(inputText, tasks);
            storage.saveTasks(tasks.getList());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
