package duke;

/**
 * Class to get response from user inputs
 */
public class Duke {
    private final TaskList list = DataStore.loadData();

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        // Parse input
        String[] inputs = input.split(" ", 2);
        Command actionType;
        String[] parsedInputs = new String[2];
        String output = "";
        try {
            actionType = Parser.parseCommand(inputs[0]);
        } catch (CommandNotFoundException e) {
            return e.getMessage();
        }
        if (inputs.length != 1) {
            parsedInputs = Parser.parseInput(inputs[1]);
        } else {
            parsedInputs[0] = "";
        }

        int indexOfList;
        Boolean wasAddSuccess = null;


        switch (actionType) {
        case LIST:
            output += list.toString();
            break;
        case MARK:
            try {
                indexOfList = Integer.parseInt(parsedInputs[0]);
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
            break;
        case UNMARK:
            try {
                indexOfList = Integer.parseInt(parsedInputs[0]);
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
            break;
        case EVENT:
            try {
                wasAddSuccess = list.addTask(TaskType.EVENT, parsedInputs);
            } catch (EmptyDescriptionException e) {
                output += (e.getMessage() + "\n");
                wasAddSuccess = false;
            }
            if (wasAddSuccess) {
                output += ("added: " + parsedInputs[0].strip() + "\n");
            } else {
                output += ("Failed to add " + parsedInputs[0].strip() + "\n");
            }
            output += "Number of tasks: ";
            output += list.getLength();
            break;
        case DEADLINE:
            try {
                wasAddSuccess = list.addTask(TaskType.DEADLINE, parsedInputs);
            } catch (EmptyDescriptionException e) {
                output += (e.getMessage() + "\n");
                wasAddSuccess = false;
            }
            if (wasAddSuccess) {
                output += ("added: " + parsedInputs[0].strip() + "\n");
            } else {
                output += ("Failed to add " + parsedInputs[0].strip() + "\n");
            }
            output += "Number of tasks: ";
            output += list.getLength();
            break;
        case TODO:
            try {
                wasAddSuccess = list.addTask(TaskType.TODO, parsedInputs);
            } catch (EmptyDescriptionException e) {
                output += (e.getMessage() + "\n");
                wasAddSuccess = false;
            }
            if (wasAddSuccess) {
                output += ("added: " + parsedInputs[0].strip() + "\n");
            } else {
                output += ("Failed to add " + parsedInputs[0].strip() + "\n");
            }
            output += ("Number of tasks: ");
            output += (list.getLength());
            break;
        case DELETE:
            indexOfList = Integer.parseInt(parsedInputs[0]);
            output += "Noted. I've removed this task: \n";
            output += (list.toString(indexOfList));
            list.deleteTask(indexOfList);
            output += ("Now you have " + list.getLength() + " tasks in the list.");
            break;
        case FIND:
            output += list.findTask(parsedInputs[0]);
            break;
        default:
            break;
        }

        DataStore.saveData(list);


        // Return String
        return output;
    }



}
