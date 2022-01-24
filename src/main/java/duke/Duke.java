package duke;

import java.io.IOException;

/**
 * The Duke class is a chatbot that controls and manages your todo list application
 */
public class Duke {

    TaskList tasks;

    Duke(TaskList tasks) {
        this.tasks = tasks;
    }

    public void run() throws DukeException, IOException {
        Ui ui = new Ui(tasks);
        ui.run();
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke(Storage.readFile());
        duke.run();
    }
}
