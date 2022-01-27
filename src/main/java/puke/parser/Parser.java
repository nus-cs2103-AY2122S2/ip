package puke.parser;

import puke.exception.PukeException;
import puke.task.TaskList;
import puke.ui.Ui;

public class Parser {
    public String processInput(String s, TaskList tasks) throws PukeException {
        String[] inputs = s.split(" ", 2);
        String command = inputs[0]; // get the first word of the input

        String argument = null;
        if (inputs.length > 1) {
            argument = inputs[1]; // remaining input (if any)
        }

        switch (command) {
        case "bye":
            return null;
        case "list":
            if (argument == null) {
                return tasks.listTasks();
            } else {
                throw new PukeException("I don't need any argument for list..");
            }
        case "mark":
        case "unmark":
            try {
                int taskNo = Integer.parseInt(argument);
                if (command.equals("mark")) {
                    return tasks.markTask(taskNo);
                } else {
                    return tasks.unmarkTask(taskNo);
                }
            } catch (NumberFormatException e) {
                throw new PukeException("I'll need a valid task number for it..");
            }
        case "todo":
        case "deadline":
        case "event":
            return tasks.addTask(command, argument);
        case "delete":
            return tasks.deleteTask(Integer.parseInt(argument));
        default:
            throw new PukeException("Are you sure you're making sense?");
        }
    }
}
