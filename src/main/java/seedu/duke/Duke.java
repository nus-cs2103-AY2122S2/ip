package seedu.duke;

import seedu.duke.exceptions.NoCommandException;
import seedu.duke.exceptions.NoDateException;

import java.util.Scanner;

/**
 * Functions as the chatbot by taking in inputs.
 * Also helps in giving out specificed outputs.
 */
class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Hello " + name);

        TaskList taskList = new TaskList();
        String command = in.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.startsWith("list")) {
                    //if equal list, print all in commandTracker
                    taskList.printTasks();
                } else if (command.startsWith("mark")) {
                    //if command mark is used,
                    // then we mark the specified task as done
                    int indexAfterCommand = 5;
                    taskList = taskList
                            .mark(Integer
                                    .parseInt(command
                                            .substring(indexAfterCommand, indexAfterCommand + 1)) - 1);
                    //command.substring(6) cuts out the command "mark "
                } else if (command.startsWith("unmark")) {
                    //if command unmark is used,
                    // then we mark the specified task as undone
                    int indexAfterCommand = 7;
                    taskList = taskList.unmark(Integer
                            .parseInt(command
                                    .substring(indexAfterCommand)) - 1);
                    //command.substring(6) cuts out the command "unmark "
                } else if (command.startsWith("todo")) {
                    //if not, just add task to taskList
                    //command is given as "todo <taskIndex>"
                    int indexTaskName = 5;
                    String taskName = command.substring(indexTaskName);
                    Task newTask = new ToDo(taskName);
                    //command.substring(5) cuts out the command
                    taskList = taskList.add(newTask);
                } else if (command.startsWith("deadline")) {
                    int indexTaskName = 9;
                    //command is given as e.g. "deadline return book /by Sunday"
                    int slashIndex = command.indexOf("/");
                    if (slashIndex == -1) {
                        throw new NoDateException();
                    }
                    String taskName = command
                            .substring(indexTaskName, slashIndex);
                    String date = command.substring(slashIndex + 1);
                    Task newTask = new Deadline(taskName, date);
                    taskList = taskList.add(newTask);
                } else if (command.startsWith("event")) {
                    int indexTaskName = 6;
                    //event project meeting /at Mon 2-4pm
                    int slashIndex = command.indexOf("/");
                    if (slashIndex == -1) {
                        throw new NoDateException();
                    }
                    String taskName = command
                            .substring(indexTaskName, slashIndex);
                    String date = command.substring(slashIndex + 3);
                    //+2 is because of "at " that occurs before the date
                    Task newTask = new Event(taskName, date);
                    taskList = taskList.add(newTask);
                } else if (command.startsWith("delete")) {
                    int indexTaskName = 7;
                    //command is given as "delete <taskIndex>"
                    int index = Integer
                            .parseInt(command
                                    .substring(indexTaskName, indexTaskName + 1));
                    taskList = taskList.delete(index);
                } else {
                    throw new NoCommandException();
                }
            } catch (NoCommandException e) {
                System.out.println("Sorry I don't understand :(");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Oh no! You didn't give me a task.");
            } catch (NoDateException e) {
                System.out.println("I don't you gave me a valid date");
            }
            command = in.nextLine();
        }
        System.out.println(String.format("Bye %s. See you again soon!", name));
    }
}
