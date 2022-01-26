import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.HashMap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private boolean isRunning;

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.runDuke();
    }

    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        isRunning = true;

        // init storage
        Function<String, Task> taskFactory = (String info) -> {
            Task newTask = null;
            char type = info.charAt(0);
            if (type == 'T') {
                newTask = new Todo();
            } else if (type == 'E') {
                newTask = new Event();
            } else if (type == 'D') {
                newTask = new Deadline();
            }

            return newTask;
        };

        try {
            storage = new Storage("data.txt", "data/");
            storage.loadFromSave(taskList.getTaskList(), taskFactory);
        } catch (DukeException exception) {
            // TODO:: issues loading from storage
        }

        // init parser
        HashMap<String, Command> commands = new HashMap<String, Command>();
        commands.put("bye", new ByeCommand("bye"));
        commands.put("list", new ListCommand("list"));
        commands.put("mark", new EditTaskMarkCommand("mark", true));
        commands.put("unmark", new EditTaskMarkCommand("unmark", false));
        commands.put("todo", new TodoCommand("todo"));
        commands.put("deadline", new DeadlineCommand("deadline"));
        commands.put("event", new EventCommand("event"));
        commands.put("delete", new DeleteCommand("delete"));

        parser = new Parser(commands);
    }

    /* Run Duke default behavior */
    public void runDuke() {
        ui.startGreeting();
        Scanner sc = new Scanner(System.in);

        while (this.isRunning) {
            String userResponse = sc.nextLine();

            try {
                Command command = parser.parse(userResponse);
                command.execute(userResponse, taskList, storage, ui);
                if (command.getKey().equals("bye")) {
                    break;
                }
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
        }

        sc.close();
    }

    /* Return true if command is successfully parsed */
    public void commandsParsed(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input, " ");
        String firstCommand = st.nextToken();

        // quit application
        if (firstCommand.equals("bye")) {
            ui.printResponse("See ya!");
            this.isRunning = false;
            return;
        }

        // print task list
        if (firstCommand.equals("list")) {
            ui.printResponse(("Here are the tasks in your list: \n" + taskList.getTaskListStr()));
            return;
        }

        // mark task
        if (firstCommand.equals("mark") || firstCommand.equals("unmark")) {
            boolean markTask = firstCommand.equals("mark");
            int taskIndex = 0;

            try {
                taskIndex = Integer.parseInt(st.nextToken()) - 1;
            } catch (NumberFormatException error) {
                throw new DukeException("Invalid input, you need to give a number/integer");
            }

            Task updatedTask = taskList.markTask(taskIndex, markTask);
            String cmdDescription = markTask ? "Nice I've marked this task as done: \n"
                    : "Alright, I've unmarked the task: \n ";

            ui.printResponse(cmdDescription + updatedTask.toString());
            return;
        }

        // for adding the diff types of tasks
        if (firstCommand.equals("todo") || firstCommand.equals("deadline") || firstCommand.equals("event")) {
            Task newTask = null;

            String exceptionErrPrint = "The description of your task cannot be empty.";
            try {
                String taskDescription = input.substring(input.indexOf(firstCommand) + firstCommand.length() + 1);

                if (taskDescription.length() == 0) {
                    throw new DukeException(exceptionErrPrint);
                }

                // init the correct task type
                if (firstCommand.equals("deadline")) {
                    exceptionErrPrint = "Did you remember to put in the deadline after /by? Or did u remember to add /by?";
                    String statement = taskDescription.substring(0, taskDescription.indexOf(" /by"));
                    String[] dateTime = taskDescription.substring(taskDescription.indexOf("/by") + 4).split(" ");

                    exceptionErrPrint = "Date format maybe wrong. yy-mm-dd";
                    LocalDate localDate = LocalDate.parse(dateTime[0]);

                    exceptionErrPrint = "Time format wrong. HHmm";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                    LocalTime localTime = LocalTime.parse("2359", formatter);
                    if (dateTime.length > 1) {
                        localTime = LocalTime.parse(dateTime[1], formatter);
                    }

                    newTask = new Deadline(false, statement, localDate, localTime);
                } else if (firstCommand.equals("event")) {
                    exceptionErrPrint = "Did you remember to put in the timing after /at? Or did u remember to add /at?";
                    String statement = taskDescription.substring(0, taskDescription.indexOf(" /at"));
                    String[] dateTime = taskDescription.substring(taskDescription.indexOf("/at") + 4).split(" ");

                    exceptionErrPrint = "Date format maybe wrong. yy-mm-dd";
                    LocalDate localDate = LocalDate.parse(dateTime[0]);

                    exceptionErrPrint = "Time format wrong. HHmm";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
                    LocalTime localTime = LocalTime.parse("2359", formatter);
                    if (dateTime.length > 1) {
                        localTime = LocalTime.parse(dateTime[1], formatter);
                    }

                    newTask = new Event(false, statement, localDate, localTime);

                } else {
                    newTask = new Todo(taskDescription);
                }

            } catch (StringIndexOutOfBoundsException error) {
                throw new DukeException(
                        "Something is wrong. " + exceptionErrPrint);
            } catch (DateTimeParseException error) {
                throw new DukeException(
                        "Something is wrong. " + exceptionErrPrint);
            }

            taskList.addTask(newTask);
            storage.saveList(taskList.getTaskList());
            String printStr = "Gotcha. Added the task: \n   " + newTask.toString()
                    + "\nNow you have " + String.valueOf(taskList.getTaskListSize()) + " tasks in your list.";

            ui.printResponse(printStr);
            return;
        }

        if (firstCommand.equals("delete")) {
            int taskIndex = 0;
            try {
                taskIndex = Integer.parseInt(st.nextToken()) - 1;
            } catch (NumberFormatException error) {
                throw new DukeException("Invalid input, you need to give a number/integer");
            }

            Task task = taskList.removeTask(taskIndex);
            storage.saveList(taskList.getTaskList());
            ui.printResponse("Got it, task has been removed: \n" + task.toString() + "\nNow you have "
                    + String.valueOf(taskList.getTaskListSize()) + " tasks in your list.");

            return;
        }

        throw new DukeException("HEY! I don't know what this mean, command doesn't exist.");
    }
}
