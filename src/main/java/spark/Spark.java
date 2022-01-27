package spark;

import spark.commands.commandtypes.Command;
import spark.commands.commandtypes.ListCommand;
import spark.exceptions.SparkException;
import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.storage.Storage;
import spark.tasks.TaskList;

public class Spark {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private static final String defaultFilePathString = "spark_save_file.txt";

    /**
     * Starts an instance of Spark that stores saved Tasks in
     * the default relative file-path on the user's hard-disk.
     */
    public Spark() {
        this.ui = new Ui();

        try {
            this.storage = new Storage(defaultFilePathString);
            this.taskList = new TaskList(storage.readTasksFile());
        } catch (FileException | TaskDecodingException e) {
            ui.printException(e);
            ui.printMessageWithDivider("Note that any changes will not be saved to your save-file.");
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts an instance of Spark that stores saved Tasks in
     * the specified relative file-path on the user's hard-disk.
     */
    public Spark(String filePathString) {
        this.ui = new Ui();

        try {
            this.storage = new Storage(filePathString);
            this.taskList = new TaskList(storage.readTasksFile());
        } catch (FileException | TaskDecodingException e) {
            ui.printException(e);
            this.taskList = new TaskList();
        }
    }

    public void run() {
        new ListCommand().execute(taskList, ui, storage);
        ui.printWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getInput();
                Command command = Parser.parseInput(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (SparkException e) {
                ui.printException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Spark().run();
    }
}
