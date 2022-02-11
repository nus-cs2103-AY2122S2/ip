package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidArgumentException;
import duke.storage.Storage;
import duke.tasks.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskList;
    private static Scanner getUserInput;
    private static Storage storage;

    private static void initialize() {
        try {
            taskList = Storage.loadTasklist();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        getUserInput = new Scanner(System.in);
        storage = new Storage();
    }

    public static void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("Your current task list is empty");
            return;
        }
        int taskCounter = 1;
        System.out.println("These are the current tasks in your list:");
        for(Task task:taskList) {
            System.out.printf("%d. %s \n", taskCounter, task.toString());
            taskCounter++;
        }
    }

    public static void displayTaskAdd(Task addedTask){
        System.out.println("This task has been added as requested:");
        System.out.println(addedTask.toString());
        System.out.printf("You now have %d item(s) in your list\n", taskList.size());
    }

    public static void displayTaskDelete(Task deletedTask){
        System.out.println("As you wish. The following task has been removed");
        System.out.println(deletedTask.toString());
        System.out.printf("You now have %d item(s) in your list\n", taskList.size());
    }

    public static String[] parseArguments(String[] arguments) throws DukeInvalidArgumentException {
        if (arguments.length < 2) {
            throw new DukeInvalidArgumentException("There appears to be invalid arguments");
        }

        return arguments[1].split(" /([Aa][Tt]|[Bb][Yy]) ", 2);
    }

    private static LocalDateTime parseDateTime(String datetime) {
        DateTimeFormatter datetimePattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(datetime, datetimePattern);
    }

    public static void commandProcessor() {
        String userInput = getUserInput.nextLine();
        String[] parsedUserInput = userInput.split(" ", 2);
        switch (parsedUserInput[0].toLowerCase()) {
        case "" :
            break;
        case "bye":
            System.out.println("Till we meet again");
            return;
        case "list":
            displayTaskList();
            break;
        case "mark":
            int taskToMarkNumber = Integer.parseInt(parsedUserInput[1].trim());
            if (taskToMarkNumber > taskList.size()) {
                System.out.println("I am afraid that's an invalid task! Please check your task number");
                break;
            }
            Task taskToMark = taskList.get(taskToMarkNumber - 1);
            taskToMark.markAsDone();
            System.out.println("Duly noted. The following task has been marked as done");
            System.out.println(taskToMark.toString());
            break;
        case "unmark":
            int taskToUnmarkNumber = Integer.parseInt(parsedUserInput[1]);
            if (taskToUnmarkNumber > taskList.size()) {
                System.out.println("I am afraid that's an invalid task! Please check your task number");
                break;
            }
            Task taskToUnmark = taskList.get(taskToUnmarkNumber - 1);
            taskToUnmark.markAsNotDone();
            System.out.println("Very well. The following task has been marked as undone");
            System.out.println(taskToUnmark.toString());
            break;
        case "todo":
            try {
                String[] parsedArguments = parseArguments(parsedUserInput);
                Todo newTodo = new Todo(parsedArguments[0]);
                taskList.add(newTodo);
                displayTaskAdd(newTodo);
            } catch (DukeInvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "deadline":
            try {
                String[] parsedArguments = parseArguments(parsedUserInput);
                Deadline newDeadline = new Deadline(parsedArguments[0], parseDateTime(parsedArguments[1]));
                taskList.add(newDeadline);
                displayTaskAdd(newDeadline);
            } catch (DukeInvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "event":
            try {
                String[] parsedArguments = parseArguments(parsedUserInput);
                Event newEvent = new Event(parsedArguments[0], parseDateTime(parsedArguments[1]));
                taskList.add(newEvent);
                displayTaskAdd(newEvent);
            } catch (DukeInvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "delete":
            int taskToDeleteNumber = Integer.parseInt(parsedUserInput[1]);
            if (taskToDeleteNumber > taskList.size()) {
                System.out.println("I am afraid that's an invalid task! Please check your task number");
                break;
            }
            Task taskToDelete = taskList.get(taskToDeleteNumber - 1);
            taskList.remove(taskToDeleteNumber - 1);
            displayTaskDelete(taskToDelete);

            break;
        default:
            System.out.println("I am unable to comprehend your request. Please try again");

        }
        try {
            storage.saveTasklist(taskList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        commandProcessor();

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I assist you?");
        initialize();
        commandProcessor();
    }
}
