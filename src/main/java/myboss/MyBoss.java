package myboss;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents the Personal Assistant Chatbot that helps a person to keep track of various things.
 * A MyBoss Object corresponds to a Personal Assistant Chatbot.
 */
public class MyBoss {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a MyBoss Object with the pre-specified file path for saving tasks.
     */
    public MyBoss() {
        String filePath = "./data/tasks.txt";
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTaskListFromFile());
        } catch (MyBossException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to generate a response to user input.
     *
     * @param input user input
     * @return returns the chatbot reponse to user input
     */
    public String getResponse(String input) {
        String[] userCmdSplit = Parser.splitUserCmd(input);
        String command = Parser.getUserCmd(userCmdSplit);

        String remainingUserInput = Parser.getRemainingUserCmd(userCmdSplit);

        try {
            switch (command) {
            case "bye":
                storage.updateFile(tasks.getTaskList());
                Main.exitPlatform();
            case "list":
                return ui.outputTaskList(tasks.getTaskList());
            case "mark":
                // fallthrough
            case "unmark":
                // fallthrough
            case "delete":
                int currTaskIndex = Parser.getTaskIndex(userCmdSplit);
                return getMarkOrDeleteOutput(command, currTaskIndex);
            case "todo":
                ToDo newToDo = new ToDo(remainingUserInput);
                return handleTaskEvent(newToDo);
            case "deadline":
                String[] parsedDeadline = Parser.parseDeadlineUserCmd(remainingUserInput);
                String deadlineName = parsedDeadline[0];
                String timeBy = parsedDeadline[1];
                Deadline newDeadline = new Deadline(deadlineName, timeBy);
                return handleTaskEvent(newDeadline);
            case "event":
                String[] parsedEvent = Parser.parseEventUserCmd(remainingUserInput);
                String eventName = parsedEvent[0];
                String timeRange = parsedEvent[1];
                Event newEvent = new Event(eventName, timeRange);
                return handleTaskEvent(newEvent);
            case "find":
                return ui.outputFoundTasks(tasks.findTasks(remainingUserInput));
            default:
                throw new MyBossException(Ui.unknownCommandExceptionMsg);

            }
        } catch (StringIndexOutOfBoundsException ex) {
            return Ui.missingTimeArgumentExceptionMsg;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.missingArgumentExceptionMsg;
        } catch (MyBossException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormatExceptionMsg;
        } catch (IndexOutOfBoundsException e) {
            return Ui.indexOutOfBoundsExceptionMsg;
        }
    }

    String getMarkOrDeleteOutput(String command, int currTaskIndex) throws MyBossException {
        Task currTask = tasks.get(currTaskIndex);

        if (command.equals("mark")) {
            return ui.outputMarked(currTask);
        } else if (command.equals("unmark")) {
            return ui.outputUnmarked(currTask);
        } else {
            storage.updateFile(tasks.getTaskList());
            return ui.outputDeleteTask(tasks.deleteTask(currTaskIndex));
        }
    }

    String handleTaskEvent(Task task) throws MyBossException {
        tasks.addTask(task);
        storage.appendTaskToFile(task);
        return ui.addTaskOutput(task);
    }

}

