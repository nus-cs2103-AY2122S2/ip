package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains the main method to run Duke.
 */
public class Duke {
    /**
     * Gets duke response.
     */
    String getResponse(String input) throws IOException {
        return reply(input);
    }

    /**
     * Replies to user commands.
     *
     * @param userInput
     * @return String representation of duke reply.
     * @throws IOException
     */
    public String reply(String userInput) throws IOException {

        // initializing variable
        boolean isBye = false;

        // array container
        ArrayList<Task> todoLists = new ArrayList<Task>();
        TaskList taskLists = new TaskList(todoLists);

        // initializing storage
        Storage storage = new Storage();

        // load data when duke starts up
        try {
            todoLists = storage.load();
            taskLists = new TaskList(todoLists);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return Duke.runDukeCommand(isBye, userInput, taskLists, storage);
    }

    /**
     * Runs duke commands based on user input.
     *
     * @param isBye whether the command is bye.
     * @param userInput user input to duke.
     * @param taskLists list of task in the hard disk.
     * @param storage hard disk.
     * @return String result of executing command.
     * @throws IOException
     */
    public static String runDukeCommand(boolean isBye, String userInput,
                                        TaskList taskLists, Storage storage) throws IOException {
        while (!isBye) {
            if (userInput.equals("bye")) {
                return "Bye. See you again next time! Have a nice day 😊!";
            } else {
                // storing input task in todoLists
                String[] userInputs = userInput.split(" ");
                String userCommand = userInputs[0];
                String userInputTask = String.join(" ",
                        Arrays.copyOfRange(userInputs, 1, userInputs.length));

                switch (userCommand) {
                case "list":
                    return (new ListCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "todo":
                    return (new TodoCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "deadline":
                    return (new DeadlineCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "event":
                    return (new EventCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "mark":
                    return (new MarkCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "unmark":
                    return (new UnmarkCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "delete":
                    return (new DeleteCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "find":
                    return (new FindCommand()).execute(taskLists, userInputTask, userInputs, storage);
                case "tag":
                    return (new TagCommand()).execute(taskLists, userInputTask, userInputs, storage);
                default:
                    return "OOPS!!! I'm sorry, but I don't know what that means.";
                }
            }
        }
        return userInput;
    }
}
