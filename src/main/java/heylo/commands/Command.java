package heylo.commands;

import heylo.tasks.Deadline;
import heylo.tasks.Event;
import heylo.tasks.Task;
import heylo.tasks.Todo;

/**
 * Represents a commands input by the user in the command line.
 */
public class Command {
    private String command;
    private String argument;
    private String extraInfo;

    /**
     * Parses the command input by the user.
     *
     * @param input String input in the command line
     */
    public Command(String input) {
        String[] inputArgs = input.trim().split(" ", 2);
        String[] extraArgs = input.trim().split("/", 2);
        this.command = inputArgs[0];

        boolean isArgumentGiven = inputArgs.length > 1;
        boolean isExtraInfoGiven = extraArgs.length > 1 && extraArgs[0].length() > 0;

        if (isArgumentGiven && isExtraInfoGiven) {
            this.argument = inputArgs[1].substring(0, inputArgs[1].indexOf("/"));
            boolean isExtraInfoValid = extraArgs[1].trim().split(" ", 2).length > 1;
            if (isExtraInfoValid) {
                this.extraInfo = extraArgs[1].trim().split(" ", 2)[1];
            }
        } else if (isArgumentGiven) {
            this.argument = inputArgs[1];
        }
    }

    /**
     * Runs relevant function based on parsed command
     */
    public String run() {
        if (command.equals("")) {
            return "";
        }

        StringBuilder str = new StringBuilder();

        switch (command) {
        case "bye":
            Task.saveTasks();
            str.append(" See you again! :)\n");
            System.exit(0);
            break;
        case "list":
            str.append(Task.getAllTasks());
            break;
        case "mark":
            if (argument == null) {
                str.append(" Please enter the task number as well!\n");
                str.append(" Command format: mark task-number\n");
                break;
            }
            str.append(Task.markAsDone(Integer.parseInt(argument) - 1));
            break;
        case "unmark":
            if (argument == null) {
                str.append(" Please enter the task number as well!\n");
                str.append(" Command format: unmark task-number\n");
                break;
            }
            str.append(Task.markAsNotDone(Integer.parseInt(argument) - 1));
            break;
        case "todo":
            Todo todo = new Todo(argument);
            str.append(Task.addToList(todo));
            break;
        case "event":
            Event event = new Event(argument, extraInfo);
            str.append(Task.addToList(event));
            break;
        case "deadline":
            Deadline deadline = new Deadline(argument, extraInfo);
            str.append(Task.addToList(deadline));
            break;
        case "delete":
            if (argument == null) {
                str.append(" Please enter the task number as well!\n");
                str.append(" Command format: delete task-number\n");
                break;
            }
            str.append(Task.removeFromList(Integer.parseInt(argument) - 1));
            break;
        case "find":
            if (argument == null) {
                str.append(" Please enter what you want to look for as well!\n");
                str.append(" Command format: find required-word(s)\n");
                break;
            }
            str.append(Task.findInList(argument));
            break;
        default:
            str.append(" Sorry, I don't understand what that means.\n");
            break;
        }
        return str.toString();
    }
}
