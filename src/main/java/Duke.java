import DukeException.NullDateProvidedException;
import TaskObjects.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    // Global Variables
    static ArrayList<Task> toDoList;
    static String welcomeMessage = "Hello! I'm Duke, your personal assistant\nWhat can I do for you?";
    static String goodbyeMessage = "Goodbye!";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(generateIntro());
        // Starting input loop
        Scanner sc = new Scanner(System.in);
        toDoList = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            if (!processInput(input)) {
                break;
            }
        }
    }

    private static boolean processInput(String input) {
        String[] inputArray = input.split(" ");
        String command = inputArray[0];

        if (command.equals("quit") || command.equals("exit")) {
            System.out.println(generateGoodbye());
            return false;
        } else if (command.equals("list")) {
            String listOutput = generateList();
            System.out.println(generateResponse(listOutput));
        } else if (command.equals("mark")) {
            if (checkForInvalidIndex(inputArray[1])) {
                return true;
            }
            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            Task currentTask = toDoList.get(taskNo);
            currentTask.markAsDone();
            String output = "Marked as done:\n" + currentTask.getCurrentStatus();
            System.out.println(generateResponse(output));
        } else if (command.equals("unmark")) {
            if (checkForInvalidIndex(inputArray[1])) {
                return true;
            }
            int taskNo = Integer.parseInt(inputArray[1]) - 1;
            Task currentTask = toDoList.get(taskNo);
            currentTask.markAsUndone();
            String output = "Marked as undone:\n" + currentTask.getCurrentStatus();
            System.out.println(generateResponse(output));
        } else if (command.equals("todo")) {
            String[] inputSplit = input.split(" ", 2);
            if (inputSplit.length < 2) {
                System.out.println(generateErrorMessage("The description of a todo cannot be empty"));
                return true;
            }

            String name = inputSplit[1];
            Todo newTask = new Todo(name);
            toDoList.add(newTask);
            System.out.println(generateAddMessage(newTask));
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
            System.out.println(generateAddMessage(newTask));
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
            System.out.println(generateAddMessage(newTask));
        } else {
            System.out.println(generateResponse("Unknown command: " + command));
        }
        return true;
    }

    private static String generateResponse(String input) {
        String temp = "<---------------------------------------------------------->\n";
        return temp + input + "\n" + temp;
    }

    private static String generateIntro() {
        return generateResponse(welcomeMessage);
    }

    private static String generateGoodbye() {
        return generateResponse(goodbyeMessage);
    }

    private static String generateAddMessage(Task newTask) {
        String message = String.format("Added %s, as a %s\n%s\nYou currently have %d tasks",
                newTask, newTask.getType(), newTask.getCurrentStatus(), toDoList.size());
        return generateResponse(message);
    }

    private static String generateList() {
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

    private static String generateErrorMessage(String msg) {
        return generateResponse("ERROR: " + msg);
    }

    private static boolean checkForInvalidIndex(String str) {
        try {
            int taskNo = Integer.parseInt(str) - 1;
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

//    Errors to handle
//        -typing a negative number in mark/unmark
//        -typing text instead of numbers
//        -typing an incorrect /by or /at
//        -missing parameters of a command
