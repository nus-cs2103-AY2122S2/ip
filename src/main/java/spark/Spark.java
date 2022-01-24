package spark;

import spark.commands.commandtypes.Command;
import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;

public class Spark {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Spark() throws SparkException {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
    }

    private void quit() {
        ui.printMessage("Cool, see you around!");
        System.exit(0);
    }

    public void run() {
        boolean isExit = false;

        while (!isExit) {
            String userInput = ui.getInput();
            Command command = Parser.parseInput(userInput);
            command.execute(taskList, ui, storage);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        try {
            new Spark().run();
        } catch(SparkException e) {
            System.out.println("A fatal error occurred. Exiting now...");
        }
    }

}
