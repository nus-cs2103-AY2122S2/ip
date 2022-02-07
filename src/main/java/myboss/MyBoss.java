package myboss;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents the Personal Assistant Chatbot that helps a person to keep track of various things.
 * A MyBoss Object corresponds to a Personal Assistant Chatbot.
 */
public class MyBoss {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a MyBoss Object with the pre-specified file path for saving tasks.
     */
    public MyBoss() {
        String filePath = "./data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskListFromFile());
    }

    /**
     * Function to generate a response to user input.
     *
     * @param input user input
     * @return returns the chatbot reponse to user input
     */
    public String getResponse(String input) {
        String[] userCmdSplit = Parser.splitUserCmd(input);
        String command = userCmdSplit[0];

        String remainingUserInput = Parser.getRemainingUserCmd(userCmdSplit);

        try {
            switch (command) {
            case "bye":
                storage.updateFile(tasks.getTaskList());
                Main.exitPlatform();
            case "list":
                return ui.outputTaskList(tasks.getTaskList());
            case "mark":
                //fallthrough
            case "unmark":
                // fallthrough
            case "delete":
                int currTaskIndex = Parser.getTaskIndex(userCmdSplit);
                Task currTask = tasks.get(currTaskIndex);
                if (command.equals("mark")) {
                    return ui.outputMarked(currTask);
                } else if (command.equals("unmark")) {
                    return ui.outputUnmarked(currTask);
                } else {
                    storage.updateFile(tasks.getTaskList());
                    return ui.outputDeleteTask(tasks.deleteTask(currTaskIndex));
                }
            case "todo":
                ToDo newToDo = new ToDo(remainingUserInput);
                tasks.addTask(newToDo);
                storage.appendTaskToFile(newToDo);
                return ui.addTaskOutput(newToDo);
            case "deadline":
                String[] parsedDeadline = Parser.parseDeadlineUserCmd(remainingUserInput);
                String deadlineName = parsedDeadline[0];
                String timeBy = parsedDeadline[1];
                Deadline newDeadline = new Deadline(deadlineName, timeBy);
                tasks.addTask(newDeadline);
                storage.appendTaskToFile(newDeadline);
                return ui.addTaskOutput(newDeadline);
            case "event":
                String[] parsedEvent = Parser.parseEventUserCmd(remainingUserInput);
                String eventName = parsedEvent[0];
                String timeRange = parsedEvent[1];
                Event newEvent = new Event(eventName, timeRange);
                tasks.addTask(newEvent);
                storage.appendTaskToFile(newEvent);
                return ui.addTaskOutput(newEvent);
            case "find":
                return ui.outputFoundTasks(tasks.findTasks(remainingUserInput));
            default:
                throw new MyBossException(" OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        } catch (StringIndexOutOfBoundsException ex) {
            return "OOPS!!! Argument after missing /at or /by!!!";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! I think you're missing some arguments!";
        } catch (MyBossException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Date must be of format yyyy-mm-dd!";
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! Index out of bounds!";
        }
    }
}

