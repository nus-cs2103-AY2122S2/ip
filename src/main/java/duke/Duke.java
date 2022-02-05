package duke;

import java.util.Arrays;

/**
 * Class to get response from user inputs
 */
public class Duke {

    static final int COMMAND_INDEX = 0;
    static final int DESCRIPTION_INDEX = 1;
    static final int TIME_INDEX = 2;

    private final TaskList list = DataStore.loadData();

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {

        String[] parsedInputs = Parser.parseInput(input);
        String output = "";
        Command actionType;

        try {
            actionType = Parser.parseCommand(parsedInputs[COMMAND_INDEX]);
        } catch (CommandNotFoundException e) {
            return e.getMessage();
        }

        assert actionType != null : "actionType should not be null";

        switch (actionType) {
        case LIST:
            output = handleList();
            break;
        case MARK:
            output = handleMark(parsedInputs[DESCRIPTION_INDEX]);
            break;
        case UNMARK:
            output = handleUnmark(parsedInputs[DESCRIPTION_INDEX]);
            break;
        case EVENT:
        case DEADLINE:
        case TODO:
            output = handleAddTask(parsedInputs);
            break;
        case DELETE:
            output = handleDelete(parsedInputs[DESCRIPTION_INDEX]);
            break;
        case FIND:
            output = handleFind(parsedInputs[DESCRIPTION_INDEX]);
            break;
        default:
            break;
        }

        DataStore.saveData(list);

        assert output != "" : "Output string should not be empty";

        // Return String
        return output;
    }

    private String handleFind(String search) {
        return list.findTask(search);
    }

    private String handleDelete(String input) {
        int indexOfList;
        String output = "";
        try {
            indexOfList = Integer.parseInt(input);
            String outputToAdd = "Noted. I've removed this task: \n";
            outputToAdd += (list.toString(indexOfList));
            list.deleteTask(indexOfList);
            output += outputToAdd;
            output += ("Now you have " + list.getLength() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            output += "Integer required for Delete command";
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            output += "Integer provided is not in the list";
        }
        return output;
    }

    private String handleAddTask(String[] parsedInputs) {
        Boolean wasAddSuccess;
        String output = "";
        TaskType task = Parser.parseTaskType(parsedInputs[COMMAND_INDEX]);
        String[] restOfInputs = Arrays.copyOfRange(parsedInputs, DESCRIPTION_INDEX, TIME_INDEX + 1);

        try {
            wasAddSuccess = list.addTask(task, restOfInputs);
        } catch (EmptyDescriptionException e) {
            output += (e.getMessage() + "\n");
            wasAddSuccess = false;
        }

        assert wasAddSuccess != null : "wasAddSuccess should not be null";

        if (wasAddSuccess) {
            output += ("added: " + parsedInputs[1].strip() + "\n");
        } else {
            output += ("Failed to add " + parsedInputs[1].strip() + "\n");
        }
        output += "Number of tasks: ";
        output += list.getLength();
        return output;
    }

    private String handleUnmark(String input) {
        int indexOfList;
        String output = "";
        try {
            indexOfList = Integer.parseInt(input);
            list.markIncomplete(indexOfList);
            output += "Marked as incomplete \n";
            output += list.toString(indexOfList);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            output += "Integer required for Unmark command";
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            output += "Integer provided is not in the list";
        }
        return output;
    }

    private String handleList() {
        return list.toString();
    }

    private String handleMark(String input) {
        int indexOfList;
        String output = "";
        try {
            indexOfList = Integer.parseInt(input);
            list.markComplete(indexOfList);
            output += "Marked as complete \n";
            output += list.toString(indexOfList);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            output += "Integer required for Unmark command";
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            output += "Integer provided is not in the list";
        }
        return output;
    }


}
