package duke;

import myTasks.TaskList;

import java.util.Scanner;

/**
 * An application that tracks the tasks that you must complete.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Method Duke initializes the application by loading files and checking for errors.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Method run starts the application.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        String inputText = "";

        while (!inputText.equals("bye")) {
            inputText = input.nextLine();

            ui.allTasks(inputText, tasks);
            storage.saveTasks(tasks.list);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}