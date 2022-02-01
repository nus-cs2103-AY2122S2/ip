package duke;

/**
 * Class to get response from user inputs
 */
public class Duke {
    private TaskList list = DataStore.loadData();

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        // Parse input
        String[] inputs = input.split(" ", 2);
        Command actionType = null;
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

        int indexOfList = -1;
        Boolean wasAddSuccess = null;


        switch (actionType) {
        case LIST:
            output += list.toString();
            break;
        case MARK:
            indexOfList = Integer.parseInt(parsedInputs[0]);
            list.markComplete(indexOfList);
            output += "Marked as complete \n";
            output += list.toString(indexOfList);
            break;
        case UNMARK:
            indexOfList = Integer.parseInt(parsedInputs[0]);
            list.markIncomplete(indexOfList);
            output += "Marked as incomplete \n";
            output += list.toString(indexOfList);
            break;
        case EVENT:
            try {
                wasAddSuccess = list.add(TaskType.EVENT, parsedInputs);
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
                wasAddSuccess = list.add(TaskType.DEADLINE, parsedInputs);
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
                wasAddSuccess = list.add(TaskType.TODO, parsedInputs);
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
            list.delete(indexOfList);
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
