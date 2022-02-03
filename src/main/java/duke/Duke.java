package duke;

import java.util.Scanner;

/**
 * Main executable for the Duke program.
 * Helps user record the tasks to be completed and their deadlines.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            System.out.println(e);
            tasks = new TaskList();
        }

    }

    /**
     * Runs the Duke program by starting the UI interface and awaits for input from user.
     * When "bye" command is used, saves the current list of tasks and exits the program
     */
    public void run() {

        ui.start();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            ui.nextInput(input, tasks);
            input = sc.nextLine();
        }

        sc.close();
        storage.save(tasks);
        ui.exit();

    }

    public void exitDuke() {
        storage.save(tasks);
    }

    public String getResponse(String input) {
        return ui.getDukeOutput(input, tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
