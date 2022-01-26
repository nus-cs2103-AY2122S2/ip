import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.function.Function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private boolean isRunning;

    private TaskList taskList;
    private Storage storage;

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.startGreeting();
        duke.runDuke();
    }

    public Duke() {
        this.taskList = new TaskList();
        this.isRunning = true;

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
            // issues loading from storage
        }
    }

    /* Initial greeting for Duke */
    public void startGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printDukeResponse("Sup! Name's Duke \nHow can I help you today?");
    }

    /* Run Duke default behavior */
    public void runDuke() {
        Scanner sc = new Scanner(System.in);

        while (this.isRunning) {
            String userResponse = sc.nextLine();

            try {
                commandsParsed(userResponse);
            } catch (DukeException error) {
                printDukeResponse(error.getMessage());
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
            printDukeResponse("See ya!");
            this.isRunning = false;
            return;
        }

        // print task list
        if (firstCommand.equals("list")) {
            printDukeResponse("Here are the tasks in your list: \n" + taskList.getTaskListStr());
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

            printDukeResponse(cmdDescription + updatedTask.toString());
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

            printDukeResponse(printStr);
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
            printDukeResponse("Got it, task has been removed: \n" + task.toString() + "\nNow you have "
                    + String.valueOf(taskList.getTaskListSize()) + " tasks in your list.");

            return;
        }

        throw new DukeException("HEY! I don't know what this mean, command doesn't exist.");
    }

    /* Print in the Duke response format */
    public void printDukeResponse(String response) {
        System.out.println(
                "\n--------------------------------------------------------------------------------------------");
        System.out.println("Duke Speaking:\n");
        System.out.println(response);
        System.out.println(
                "--------------------------------------------------------------------------------------------\n");
    }

    public String getListStr(ArrayList<? extends Object> list) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < list.size(); ++i) {
            sb.append(String.valueOf(i + 1)).append(". ").append(list.get(i).toString()).append("\n");
        }

        return sb.toString();
    }
}
