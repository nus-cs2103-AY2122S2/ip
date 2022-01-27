package ann.parser;

import ann.commands.*;
import ann.data.InputPattern;
import ann.data.tasks.TaskType;

public class Parser {
    public static Command parse(String input) {
        if(input.toLowerCase().equals("bye")) {
            return new ExitCommand();
        } else if (input.toLowerCase().equals("list")){
            return new ListCommand();
        } else if (input.length() < 3) {
            return new IncorrectCommand("Oops! Please enter a valid command!");
        } else if(input.substring(0, 3).toLowerCase().equals("add")) {
            return handleAdd(input.substring(3));
        } else if(input.length() < 4) {
            return new IncorrectCommand("Oops! Please enter a valid command!");
        } else if(input.substring(0, 4).toLowerCase().equals("find")) {
            return handleFind(input.substring(4));
        } else if(input.substring(0, 4).toLowerCase().equals("mark")) {
            return handleMark(input.substring(4));
        } else if(input.length() < 6) {
            return new IncorrectCommand("Oops! Please enter a valid command!");
        } else if(input.substring(0, 6).toLowerCase().equals("unmark")) {
            return handleUnmark(input.substring(6));
        } else if(input.substring(0, 6).toLowerCase().equals("delete")) {
            return handleDelete(input.substring(6));
        } else {
            return new IncorrectCommand("Oops! Please enter a valid command!");
        }
    }

    private static Command handleAdd(String input) {
        if(input.isBlank()) {
            return new IncorrectCommand("Oops! Please specify the task you wish to add!");
        } else if(input.charAt(0) != ' ') {
            return new IncorrectCommand("Oops! Please enter a valid command!");
        } else {
            String taskType = input.split(" ")[1].toLowerCase();
            switch (taskType) {
                case "todo":
                    return handleTodo(input.substring(6));
                case "event":
                    return handleEvent(input.substring(7));
                case "deadline":
                    return handleDeadline(input.substring(10));
                default:
                    return new IncorrectCommand("Oops! Please enter a valid task type!");
            }
        }
    }

    private static Command handleMark(String index) {
        if(index.isBlank()) {
            return new IncorrectCommand("Oops! Please use the following format:\nmark [task number]");
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                return new MarkCommand(i);
            } catch (NumberFormatException nfe) {
                return new IncorrectCommand("Oops! Please use the following format:\nmark [task number]");
            }
        }
    }

    private static Command handleUnmark(String index) {
        if(index.isBlank()) {
            return new IncorrectCommand("Oops! Please use the following format:\nunmark [task number]");
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                return new UnmarkCommand(i);
            } catch (NumberFormatException nfe) {
                return new IncorrectCommand("Oops! Please use the following format:\nunmark [task number]");
            }
        }
    }

    private static Command handleDelete(String index) {
        if(index.isBlank()) {
            return new IncorrectCommand("Oops! Please use the following format:\ndelete [task number]");
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                return new DeleteCommand(i);
            } catch (NumberFormatException nfe) {
                return new IncorrectCommand("Oops! Please use the following format:\ndelete [task number]");
            }
        }
    }

    private static Command handleTodo(String todoContent) {
        if(todoContent.isBlank()) {
            return new IncorrectCommand("Oops! Please add a description for the todo!");
        } else {
            return new AddCommand(TaskType.TODO, new String[] {todoContent});
        }
    }

    private static Command handleEvent(String eventComponents) {
        if(eventComponents.isBlank()) {
            return new IncorrectCommand("Oops! Please use the following format:\nadd event [content] /at yyyy-MM-dd HH:mm");
        } else {
            String[] eventComponentsArray = eventComponents.split(" /at ");
            if(eventComponentsArray.length <= 1) {
                return new IncorrectCommand("Oops! Please use the following format:\nadd event [content] /at yyyy-MM-dd HH:mm");
            } else if(eventComponentsArray[0].isBlank()) {
                return new IncorrectCommand("Oops! Please add a description for the event!");
            } else if(eventComponentsArray[1].isBlank()) {
                return new IncorrectCommand("Oops! Please add a date and time for the event!");
            } else if(!InputPattern.isValidDateTimeString(eventComponentsArray[1])) {
                return new IncorrectCommand("Oops! Please use the following format:\nadd event [content] /at yyyy-MM-dd HH:mm");
            } else {
                return new AddCommand(TaskType.EVENT, eventComponentsArray);
            }
        }
    }

    private static Command handleDeadline(String deadlineComponents) {
        if(deadlineComponents.isBlank()) {
            return new IncorrectCommand("Oops! Please use the following format:\nadd deadline [content] /by yyyy-MM-dd HH:mm");
        } else {
            String[] deadlineComponentsArray = deadlineComponents.split(" /by ");
            if(deadlineComponentsArray.length <= 1) {
                return new IncorrectCommand("Oops! Please use the following format:\nadd deadline [content] /by yyyy-MM-dd HH:mm");
            } else if(deadlineComponentsArray[0].isBlank()) {
                return new IncorrectCommand("Oops! Please add a description for the deadline!");
            } else if(deadlineComponentsArray[1].isBlank()) {
                return new IncorrectCommand("Oops! Please add a deadline for the deadline!");
            } else if(!InputPattern.isValidDateTimeString(deadlineComponentsArray[1])) {
                return new IncorrectCommand("Oops! Please use the following format:\nadd deadline [content] /by yyyy-MM-dd HH:mm");
            } else {
                return new AddCommand(TaskType.DEADLINE, deadlineComponentsArray);
            }
        }
    }

    private static Command handleFind(String findKeyWords) {
        if(findKeyWords.isBlank()) {
            return new IncorrectCommand("Oops! Please use the following format:\nfind [key word(s)]");
        } else {
            return new FindCommand(findKeyWords.trim());
        }
    }
}