package duke.modules;

import java.util.ArrayList;

/**
 * Takes user inputs from the Ui class and checks their validity.
 */
public class Parser {

    private boolean isByeCommand = false;
    private TaskList taskList;

    /**
     * Constructor for a Parser.
     *
     * @param taskList The list of tasks referenced by the parser to construct output messages.
     */
    public Parser(TaskList taskList){
        this.taskList = taskList;
    }

    /**
     * Returns an output message based on the user input.
     *
     * @param userInput The input from the user.
     * @return A String to output to the user.
     */
    public String parse(String userInput) {

        // Split user input into individual words
        String[] split = userInput.split(" ");
        String outputMessage = "";
        ArrayList<Task> toDoList = taskList.getToDoList();
        switch (split[0].toLowerCase()) {
        case "bye":
            outputMessage = new CommandBye().execute();
            isByeCommand = true;
            break;
        case "list":
            outputMessage = new CommandList(toDoList).execute();
            break;
        case "help":
            outputMessage = new CommandHelp().execute();
            break;
        case "unmark":
            outputMessage = new CommandUnmark(userInput, taskList).execute();
            break;
        case "mark":
            outputMessage = new CommandMark(userInput, taskList).execute();
            break;
        case "todo":
            outputMessage = new CommandToDo(userInput, taskList).execute();
            break;
        case "deadline":
            outputMessage = new CommandDeadline(userInput, taskList).execute();
            break;
        case "event":
            outputMessage = new CommandEvent(userInput, taskList).execute();
            break;
        case "delete":
            outputMessage = new CommandDelete(userInput, taskList).execute();
            break;
        case "find":
            outputMessage = new CommandFind(userInput, toDoList).execute();
            break;
        default:
            // If user input does not match any commands
            outputMessage = new CommandUnknown().execute();
        }
        return outputMessage;
    }

    /**
     * Check if the bye command has been executed.
     *
     * @return A boolean depending on whether the bye command has been executed.
     */
    public boolean byeCommandHasExecuted() {
        return isByeCommand;
    }
}
