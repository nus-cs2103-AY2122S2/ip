package spark;

import spark.exceptions.SparkException;
import spark.parser.Parser;
import spark.parser.commands.commandtypes.ExitCommand;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.parser.commands.commandtypes.Command;
import spark.parser.commands.commandtypes.ListCommand;
import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.TaskDecodingException;

public class Spark {
    private static final String defaultFilePathString = "spark_save_file.txt";
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

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

    /** DEPRECATED
     * No longer need a CLI interface for Spark after
     * creating a GUI.
     *
     * Todo: Remove this, along with all CLI-related code.
     */
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

    public String executeCommand(String userInput) {
        try {
            Command command = Parser.parseInput(userInput);
            String response = command.execute(taskList, ui, storage);
            boolean isExit = command.isExit();
            return response;
        } catch (SparkException e) {
            ui.printException(e);

            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Spark().run();
    }
}
