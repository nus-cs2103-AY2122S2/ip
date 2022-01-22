package duke;

import duke.exceptions.RequiredInformationMissingException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.exceptions.UnknownCommandException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Main duke.UI interface class.
 */
public class UI {
    TaskManager taskManager;
    Scanner scanner;

    /**
     * Creates instance of duke.Duke duke.UI.
     */
    public UI() {
        try {
            this.taskManager = new TaskManager();
        } catch (IOException e) {
            System.out.println("An error occured while reading task file.");
        } catch (duke.exceptions.UnknownFileEntry e) {
            System.out.println(e);
        }
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the chat between duke.Duke and the User.
     */
    public void start() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        while (true) {
            String input = scanner.nextLine();
            try {
                ArrayList<String> parsedInput = Parser.parseInput(input);
                String command = parsedInput.get(0);
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("mark")) {
                    int indexOfTask = Integer.parseInt(parsedInput.get(1));
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskManager.completeTask(indexOfTask));
                } else if (command.equals("unmark")) {
                    int indexOfTask = Integer.parseInt(parsedInput.get(1));
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskManager.removeCompletedStatusOfTask(indexOfTask));
                } else if (command.equals("todo")) {
                    Task task = new Task(parsedInput.get(1));
                    taskManager.addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks in the list.");
                } else if (command.equals("event")) {
                    Event task = new Event(parsedInput.get(1), parsedInput.get(2));
                    taskManager.addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks in the list.");
                } else if (command.equals("deadline")) {
                    Deadline task = new Deadline(parsedInput.get(1), parsedInput.get(2));
                    taskManager.addTask(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks in the list.");
                } else if (command.equals("delete")) {
                    int indexOfTask = Integer.parseInt(parsedInput.get(1));
                    Task task = taskManager.removeTask(indexOfTask);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks left in the list.");
                }
            } catch (RequiredInformationMissingException e){
                System.out.println("ERROR " + e.getMessage());
            } catch (UnknownCommandException e) {
                System.out.println("ERROR " + e.getMessage());
            }

            try {
                taskManager.saveTasks();

            } catch (IOException e) {
                System.out.println("An error occured with the duke.tasks file while saving duke.tasks.");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
