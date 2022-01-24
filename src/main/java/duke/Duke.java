package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.command.Command;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private Duke(String dirPath, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, fileName);
        this.taskList = new TaskList();
        this.storage.loadData(this.taskList, this.ui);
    }

    private void run() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = this.ui.readCommand();
            try {
                Command command = Parser.parse(userInput, this.taskList.getLength());
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.showCommandError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}
