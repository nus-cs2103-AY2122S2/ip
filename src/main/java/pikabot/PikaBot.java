package pikabot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import pikabot.command.Command;
import pikabot.task.Task;

/**
 * Runs the whole application PikaBot, an application
 * that serves as a ChatBot to keep track of your tasks.
 */
public class PikaBot {

    private static final String FILEPATH = "data/tasks.txt";
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for PikaBot.
     */
    public PikaBot() {
        this.storage = new Storage(FILEPATH);
        this.taskList = new TaskList(new ArrayList<Task>());
        if (storage.doesFileExist()) {
            try {
                taskList = Storage.fileToTaskList(new File(FILEPATH));
            } catch (FileNotFoundException e) {
                Ui.printExceptionMessage(e);
            }
        }
    }

    /**
     * Gets PikaBot's response when user types in an input.
     *
     * @param input String input given by user.
     * @return PikaBot's response.
     */
    public String getResponse(String input) {
        String[] strInputArr = input.split(" ", 2);
        String response = "";

        if (!strInputArr[0].equals("bye")) {
            Command command = Parser.parseCommand(strInputArr);
            response = command.execute(taskList, storage);
        } else {
            response = Ui.printClosingText();
        }

        return response;
    }
}
