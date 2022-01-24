package kidsnd274.duke;

import kidsnd274.duke.exceptions.NullDateProvidedException;
import kidsnd274.duke.taskobjects.Event;
import kidsnd274.duke.taskobjects.Deadline;
import kidsnd274.duke.taskobjects.Todo;
import kidsnd274.duke.taskobjects.Task;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    // Global Variables
    private static final String FILENAME = "task.txt";

    private ArrayList<Task> toDoList;
    private Storage fh;
    private TextUi ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        // Setting up the goods
        ui = new TextUi();
        fh = new Storage(FILENAME);
        toDoList = fh.importTasks();

        ui.printIntro();

        // Starting input loop
        while (true) {
            String input = ui.getInput();
            if (!processInput(input)) {
                break;
            }
        }
        ui.printGoodbye();
    }

    private boolean processInput(String input) {
        if (input.contains("`")) {
            ui.printResults("\"`\" character is not allowed");
            return true;
        }

        String[] inputArray = input.split(" ");
        String command = inputArray[0];

        if (command.equals("quit") || command.equals("exit")) {
            return false;

        } else if (command.equals("list")) {
            String listOutput = generateList();
            ui.printResults(listOutput);
            return true;

        } else if (command.equals("mark")) {
            if (checkForInvalidIndex(inputArray)) {
                return true;
            }
            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            Task currentTask = toDoList.get(taskNo);
            currentTask.markAsDone();
            String output = "Marked as done:\n" + currentTask.getCurrentStatus();
            ui.printResults(output);

        } else if (command.equals("unmark")) {
            if (checkForInvalidIndex(inputArray)) {
                return true;
            }
            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            Task currentTask = toDoList.get(taskNo);
            currentTask.markAsUndone();
            String output = "Marked as undone:\n" + currentTask.getCurrentStatus();
            ui.printResults(output);

        } else if (command.equals("todo")) {
            String[] inputSplit = input.split(" ", 2);
            if (inputSplit.length < 2) {
                System.out.println(generateErrorMessage("The description of a todo cannot be empty"));
                return true;
            }
            String name = inputSplit[1];
            Todo newTask = new Todo(name);
            toDoList.add(newTask);
            ui.printResults(generateAddMessage(newTask)); // CHANGE THIS

        } else if (command.equals("deadline")) {
            // Checking for date
            try {
                if (!input.contains("/by ")) {
                    throw new NullDateProvidedException();
                }
            } catch (NullDateProvidedException e) {
                System.out.println(generateErrorMessage("No date provided"));
                return true;
            }

            // Splitting input to command and deadline
            String[] deadlineStringSplit = input.split("/by ");
            String deadline = deadlineStringSplit[1];
            String name = deadlineStringSplit[0].split(" ", 2)[1];
            Deadline newTask = new Deadline(name, deadline);
            toDoList.add(newTask);
            ui.printResults(generateAddMessage(newTask)); // CHANGE THIS

        } else if (command.equals("event")) {
            // Checking for date
            try {
                if (!input.contains("/at ")) {
                    throw new NullDateProvidedException();
                }
            } catch (NullDateProvidedException e) {
                System.out.println(generateErrorMessage("No date provided"));
                return true;
            }

            String[] eventStringSplit = input.split("/at ");
            String eventTime = eventStringSplit[1];
            String name = eventStringSplit[0].split(" ", 2)[1];
            Event newTask = new Event(name, eventTime);
            toDoList.add(newTask);
            ui.printResults(generateAddMessage(newTask)); // CHANGE THIS

        } else if (command.equals("delete")) {
            if (checkForInvalidIndex(inputArray)) {
                return true;
            }
            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            Task currentTask = toDoList.get(taskNo);
            String description = currentTask.getCurrentStatus();
            toDoList.remove(taskNo);
            ui.printResults(generateRemoveMessage(description)); // CHANGE THIS

        } else {
            ui.printResults("Unknown command: " + command); // CHANGE THIS
        }
        fh.exportTasks(toDoList);
        return true;
    }

    private String generateAddMessage(Task newTask) {
        String message = String.format("Added %s, as a %s\n%s\nYou currently have %d tasks",
                newTask, newTask.getType(), newTask.getCurrentStatus(), toDoList.size());
        return (message);
    }

    private String generateRemoveMessage(String des) {
        String message = String.format("Removed this task:\n%s\nYou currently have %d tasks",
                des, toDoList.size());
        return (message);
    }

    private String generateList() {
        StringBuilder newString = new StringBuilder("Tasklist:\n");
        for (int i = 0; i < toDoList.size(); i++) {
            if (i != 0) {
                newString.append("\n");
            }
            newString.append(i + 1);
            newString.append(". ");
            newString.append(toDoList.get(i).getCurrentStatus());
        }
        return newString.toString();
    }

    private String generateErrorMessage(String msg) {
        return ("ERROR: " + msg);
    }

    private boolean checkForInvalidIndex(String[] strArr) {
        try {
            int taskNo = Integer.parseInt(strArr[1]) - 1;
            if (taskNo < 0 || taskNo >= toDoList.size()) { // Check if index is out of bounds
                throw new IndexOutOfBoundsException();
            }
        } catch (NumberFormatException e) { // Incorrect number keyed
            System.out.println(generateErrorMessage("bruh that ain't a number"));
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(generateErrorMessage("Invalid number entered"));
            return true;
        }
        return false;
    }

}