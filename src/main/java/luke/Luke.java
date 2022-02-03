package luke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import luke.commands.Command;
import luke.commands.CommandResult;
import luke.commands.Result;
import luke.data.TaskList;
import luke.parser.Parser;
import luke.storage.StorageFile;

/**
 * Implements the Luke chat bot.
 */
public class Luke {

    private static final String DATABASE_SUCCESS_MESSAGE = "Database initialized successfully!";

    private TaskList taskList;
    private StorageFile storageFile;

    /**
     * Constructs a new Luke chat bot with the specified file path as the data file.
     * If unable to read/write file or create a new file at the specified file path,
     * the system will print the error message and exit.
     *
     * @param filePath The specified file path to the data file.
     */
    Luke(String filePath) {
        taskList = new TaskList();
        try {
            storageFile = new StorageFile(filePath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        initializeStorage();
    }

    /**
     * Initializes the storage with the data found from the data file if it is not empty.
     */
    private void initializeStorage() {
        try {
            List<String> data = storageFile.load();
            if (!data.isEmpty()) {
                for (int i = 0; i < data.size(); i++) {
                    String str = data.get(i);
                    String[] inputs = str.split("\\|");
                    Command cmd = Parser.parse(inputs[0]);
                    cmd.execute(taskList);
                    if (Integer.parseInt(inputs[1].strip()) == 1) {
                        taskList.get(i).markAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the response from the bot based on the user input.
     *
     * @param input The user input to interpret.
     * @return The result from the bot.
     */
    public Result getResponse(String input) {
        Command cmd = Parser.parse(input);
        CommandResult response = cmd.execute(taskList);
        try {
            storageFile.save(taskList);
        } catch (IOException e) {
            return new CommandResult(e.getMessage());
        }
        return response;
    }
}
