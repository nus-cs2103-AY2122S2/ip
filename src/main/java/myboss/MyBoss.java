package myboss;

import java.time.format.DateTimeParseException;

public class MyBoss {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MyBoss(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskListFromFile());
    }

    public void run() {
        ui.outputGreeting();

        while (true) {
            String userCmd = ui.getUserCmd();
            String[] splitUserCmd = Parser.splitUserCmd(userCmd);
            String command = splitUserCmd[0];

            String remainingUserInput = Parser.getRemainingUserCmd(splitUserCmd);

            try {
                switch(command) {
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
                        int currTaskIndex = Parser.getTaskIndex(splitUserCmd);
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
                        Deadline newDl = new Deadline(deadlineName, timeBy);
                        tasks.addTask(newDl);
                        ui.addTaskOutput(newDl);
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
                Ui.myBossOutput(" OOPS!!! Argument after missing /at or /by!!!");
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.myBossOutput(" OOPS!!! I think you're missing some arguments!");
            } catch (MyBossException e) {
                Ui.myBossOutput(e.getMessage());
            } catch (DateTimeParseException e) {
                Ui.myBossOutput("Date must be of format yyyy-mm-dd!");
            }
        }
    }

    public static void main(String[] args) {
        new MyBoss("./data/tasks.txt").run();
    }
}
