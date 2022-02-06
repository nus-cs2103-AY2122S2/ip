package spark;

import java.util.ArrayList;
import java.util.List;

import spark.commandresponse.CommandResponse;
import spark.commandresponse.ErrorResponse;
import spark.exceptions.SparkException;
import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.parser.Parser;
import spark.parser.commands.commandtypes.Command;
import spark.storage.Storage;
import spark.tasks.TaskList;

public class Spark {
    private static final String DEFAULT_FILE_PATH_STRING = "spark_save_file.txt";
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
            this.storage = new Storage(DEFAULT_FILE_PATH_STRING);
            this.taskList = new TaskList(storage.readTasksFile());
        } catch (FileException | TaskDecodingException e) {
            ui.printException(e);
            ui.printMessageWithDivider("Note that any changes will not be saved to your save-file.");
            this.taskList = new TaskList();
        }
    }

    /**
     * Executes the command given by the user and returns a list
     * of messages to be displayed to the user on the GUI
     *
     * @param userInput what the user has typed in
     * @return          a list of messages to be displayed to the user on
     *                  the GUI
     */
    public List<CommandResponse> executeCommand(String userInput) {
        List<CommandResponse> responses = new ArrayList<>();

        try {
            Command command = Parser.parseInput(userInput);
            responses.addAll(command.execute(taskList, ui, storage));
        } catch (SparkException e) {
            responses.add(new ErrorResponse(e));
        }

        return responses;
    }
}
