import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.io.IOException;
import java.util.Scanner;
import exceptions.*;

/**
 * Main Chat interface class.
 */
public class Chat {
    TaskManager taskManager;
    Scanner scanner;

    /**
     * Creates instance of Duke Chat.
     */
    public Chat() {
        try {
            this.taskManager = new TaskManager();
        } catch (IOException e) {
            System.out.println("An error occured while reading task file.");
        } catch (UnknownFileEntry e) {
            System.out.println(e);
        }
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the chat between Duke and the User.
     */
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String command = getCommandFromInput(input);
            if (command.equals("list")) {
                //print out all the tasks
                System.out.println("Here are the tasks in your list:\n" + taskManager.getPrintableListOfTasks());
            } else if (command.equals("mark")) {
                try {
                    if (input.length() < 6) {
                        throw new RequiredInformationMissingException1("missing mark index");
                    }
                    int indexOfTask = Integer.parseInt(input.substring(5));
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskManager.completeTask(indexOfTask));
                } catch (RequiredInformationMissingException1 e) {
                    System.out.println("OOPS!!! The index of the task to complete cannot be missing.");
                }
            } else if (command.equals("unmark")) {
                try {
                    if (input.length() < 8) {
                        throw new RequiredInformationMissingException1("missing unmark index");
                    }
                    int indexOfTask = Integer.parseInt(input.substring(7));
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskManager.removeCompletedStatusOfTask(indexOfTask));
                } catch (RequiredInformationMissingException1 e) {
                    System.out.println("OOPS!!! The index of the task to uncomplete cannot be missing.");
                }
            } else if (command.equals("todo")) {
                try {
                    if (input.length() < 6) {
                        throw new RequiredInformationMissingException1("empty todo description");
                    }
                    Task task = new Task(input.substring(5));
                    taskManager.addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks in the list.");
                } catch (RequiredInformationMissingException1 e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }

            } else if (command.equals("event")) {
                try {
                    if (input.length() < 7) {
                        throw new RequiredInformationMissingException1("empty event description");
                    }
                    int indexOfStartDate = input.indexOf(" /") + 5;
                    Event task = new Event(input.substring(6, input.indexOf(" /")), input.substring(indexOfStartDate));
                    taskManager.addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks in the list.");
                } catch (RequiredInformationMissingException1 e) {
                    System.out.println("OOPS!!! The description of a event cannot be empty.");
                }
            } else if (command.equals("deadline")) {
                try {
                    if (input.length() < 10) {
                        throw new RequiredInformationMissingException1("empty deadline description");
                    }
                    int indexOfStartDate = input.indexOf(" /") + 5;
                    Deadline task = new Deadline(input.substring(9, input.indexOf(" /")), input.substring(indexOfStartDate));
                    taskManager.addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks in the list.");
                } catch (RequiredInformationMissingException1 e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }

            } else if (command.equals("delete")) {
                try {
                   if (input.length() < 8) {
                       throw new RequiredInformationMissingException1("missing delete index");
                   }
                   int index = Integer.parseInt(input.substring(7));
                   Task task = taskManager.removeTask(index);
                   System.out.println("Noted. I've removed this task:");
                   System.out.println("  " + task);
                   System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks left in the list.");
                } catch (RequiredInformationMissingException1 e) {
                   System.out.println("OOPS!!! Index of the task to remove cannot be missing!");
                }
            } else {
                try {
                    throw new UnknownCommandException("unknown command entered");
                }
                catch (UnknownCommandException e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            //save all the tasks
            try {
                taskManager.saveTasks();

            } catch (IOException e) {
                System.out.println("An error occured with the tasks file while saving tasks.");
            }
            //get next command
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Extracts the command from a given input.
     * @param input
     * @return the command
     */
    private String getCommandFromInput(String input) {
        int indexOfWhitespace = input.indexOf(" ");
        if (indexOfWhitespace == -1) {
            //1 word input
            return input;
        } else {
            return input.substring(0, indexOfWhitespace);
        }
    }
}
