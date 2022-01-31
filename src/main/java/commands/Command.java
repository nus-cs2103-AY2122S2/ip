package commands;

import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Command {
    private String command;
    private String argument;
    private String extraInfo;

    public Command(String input) {
        String[] inputArgs = input.trim().split(" ", 2);
        String[] extraArgs = input.trim().split("/", 2);
        this.command = inputArgs[0];
        if (inputArgs.length > 1) {
            this.argument = inputArgs[1];
        }
        if (extraArgs.length > 1) {
            this.argument = inputArgs[1].substring(0, inputArgs[1].indexOf("/"));
            if (extraArgs[1].trim().split(" ", 2).length > 1) {
                this.extraInfo = extraArgs[1].trim().split(" ", 2)[1];
            }
        }
    }

    public void run(Scanner sc) {
        if (command.equals("")) {
            return;
        }

        switch (command) {
        case "bye":
            Task.saveTasks();
            System.out.println(" See you again! :)");
            sc.close();
            System.exit(0);
        case "list":
            Task.printAllTasks();
            break;
        case "mark":
            if (argument == null) {
                System.out.println(" Please enter the task number as well!");
                System.out.println(" Command format: mark task-number");
                break;
            }
            Task.markAsDone(Integer.parseInt(argument) - 1);
            break;
        case "unmark":
            if (argument == null) {
                System.out.println(" Please enter the task number as well!");
                System.out.println(" Command format: unmark task-number");
                break;
            }
            Task.markAsNotDone(Integer.parseInt(argument) - 1);
            break;
        case "todo":
            Todo todo = new Todo(argument);
            Task.addToList(todo);
            break;
        case "event":
            Event event = new Event(argument, extraInfo);
            Task.addToList(event);
            break;
        case "deadline":
            Deadline deadline = new Deadline(argument, extraInfo);
            Task.addToList(deadline);
            break;
        case "delete":
            if (argument == null) {
                System.out.println(" Please enter the task number as well!");
                System.out.println(" Command format: delete task-number");
                break;
            }
            Task.removeFromList(Integer.parseInt(argument) - 1);
            break;
        default:
            System.out.println(" Sorry, I don't understand what that means.");
            break;
        }
        System.out.println("__________________________________________________________");
    }
}
