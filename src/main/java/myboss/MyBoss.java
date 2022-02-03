package myboss;

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
     * Creates a MyBoss Object with the specified file path for saving tasks.
     *
     * @param filePath path to file where tasks will be saved.
     */
    public MyBoss(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskListFromFile());
    }

    /**
     * Logic of the Personal Assistant Chatbot that receives input and acts accordingly.
     */
    public void run() {
        ui.outputGreeting();

        while (true) {
            String userCmd = ui.getUserCmd();
            String[] userCmdSplit = Parser.splitUserCmd(userCmd);
            String command = userCmdSplit[0];

            String remainingUserInput = Parser.getRemainingUserCmd(userCmdSplit);

            try {
                switch (command) {
                case "bye":
                    storage.updateFile(tasks.getTaskList());
                    ui.outputFarewell();
                    System.exit(0);
                case "list":
                    ui.outputTaskList(tasks.getTaskList());
                    break;
                case "mark":
                    //fallthrough
                case "unmark":
                    // fallthrough
                case "delete":
                    int currTaskIndex = Parser.getTaskIndex(userCmdSplit);
                    Task currTask = tasks.get(currTaskIndex);
                    if (command.equals("mark")) {
                        ui.outputMarked(currTask);
                    } else if (command.equals("unmark")) {
                        ui.outputUnmarked(currTask);
                    } else {
                        ui.outputDeleteTask(tasks.deleteTask(currTaskIndex));
                    }
                    break;
                case "todo":
                    ToDo newToDo = new ToDo(remainingUserInput);
                    tasks.addTask(newToDo);
                    ui.addTaskOutput(newToDo);
                    break;
                case "deadline":
                    String[] parsedDeadline = Parser.parseDeadlineUserCmd(remainingUserInput);
                    String deadlineName = parsedDeadline[0];
                    String timeBy = parsedDeadline[1];
                    Deadline newDeadline = new Deadline(deadlineName, timeBy);
                    tasks.addTask(newDeadline);
                    ui.addTaskOutput(newDeadline);
                    break;
                case "event":
                    String[] parsedEvent = Parser.parseEventUserCmd(remainingUserInput);
                    String eventName = parsedEvent[0];
                    String timeRange = parsedEvent[1];
                    Event newEvent = new Event(eventName, timeRange);
                    tasks.addTask(newEvent);
                    ui.addTaskOutput(newEvent);
                    break;
                case "find":
                    ui.outputFoundTasks(tasks.findTasks(remainingUserInput));
                    break;
                default:
                    throw new MyBossException(" OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
            } catch (StringIndexOutOfBoundsException ex) {
                Ui.outputMyBoss(" OOPS!!! Argument after missing /at or /by!!!");
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.outputMyBoss(" OOPS!!! I think you're missing some arguments!");
            } catch (MyBossException e) {
                Ui.outputMyBoss(e.getMessage());
            } catch (DateTimeParseException e) {
                Ui.outputMyBoss("Date must be of format yyyy-mm-dd!");
            }
        }
    }

    public static void main(String[] args) {
        new MyBoss("./data/tasks.txt").run();
    }
}
