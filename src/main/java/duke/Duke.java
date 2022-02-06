package duke;

import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

/**
 *
 * A bot that helps you keep track of tasks like Todos, Dealines, and Events.
 * @author Jian Rong
 *
 */
public class Duke {
    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage("data/duke.txt", taskList);

    /**
     * This is the main method of Duke, which calls the listen method.
     * @param args Unused
     * @return void
     */
    public static void main(String[] args) {
        new Duke().listen();
    }

    private void listen() {
        Ui.printLogo();
        boolean isRunning = true;
        while (isRunning) {
            isRunning = Ui.run(taskList);
            storage.write();
        }
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

}
