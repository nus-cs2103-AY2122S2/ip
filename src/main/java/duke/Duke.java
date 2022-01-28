package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskMaster taskMaster;

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskMaster = new TaskMaster(storage.loadTasks());
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] userCommand = ui.readCommand();
                Command command = Parser.parse(userCommand[0], userCommand[1]);
                command.execute(this.taskMaster, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

}