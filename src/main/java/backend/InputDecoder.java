package backend;

public class InputDecoder {
    private Commands currentCommand = null;

    public InputDecoder() {
    }

    /**
     * Seperates the task command from the rest of the user input, and sets it to currentCommand
     *
     * @param input User String
     * @return String array
     * @throws IllegalArgumentException if command is invalid
     */
    private String[] parseInput(String input) throws IllegalArgumentException {
        String[] commandSections = input.split(" ", 2);
        currentCommand = Commands.valueOf(commandSections[0].toUpperCase());
        return commandSections;
    }

    /**
     * Calls on the logic in tasklist to perform actions based on user input and
     * return an output string to show to the user.
     *
     * @param input task generating string that follows standard format
     * @return String to be returned to user
     * @throws ArrayIndexOutOfBoundsException if only command and no input behind is given
     */
    public String decode(String input) {
        String[] commandSections = null;

        // parse input supplied
        try {
            commandSections = this.parseInput(input);
        } catch (IllegalArgumentException e) {
            return Ui.errorInput();
        }

        //do a certain action based on the parsed input
        switch (currentCommand) {
        case LIST:
            return TaskList.list();

        case MARK:
            int indexMarked;
            try {
                indexMarked = Integer.parseInt(commandSections[1]) - 1;
            } catch (NumberFormatException e){
                return Ui.invalidIndex();
            }
            return TaskList.mark(indexMarked);

        case UNMARK:
            int indexUnmarked;
            try {
                indexUnmarked = Integer.parseInt(commandSections[1]) - 1;
            } catch (NumberFormatException e){
                return Ui.invalidIndex();
            }
            return TaskList.unmark(indexUnmarked);

        case DELETE:
            int indexDelete;
            try {
                indexDelete = Integer.parseInt(commandSections[1]) - 1;
            } catch (NumberFormatException e) {
                return Ui.invalidIndex();
            }
            return TaskList.delete(indexDelete);

        case TODO:
            String todoDescription = commandSections[1];
            return TaskList.addTodo(todoDescription);

        case DEADLINE:
            String deadlineContent = commandSections[1];
            String[] deadlineSegments = deadlineContent.split(" /by ");
            String deadlineDescription = deadlineSegments[0];
            String deadlineTime = deadlineSegments[1];
            return TaskList.addDeadline(deadlineDescription, deadlineTime);

        case EVENT:
            String eventContent = commandSections[1];
            String[] eventSegments = eventContent.split(" /at ");
            String eventDescription = eventSegments[0];
            String eventTime = eventSegments[1];
            return TaskList.addEvent(eventDescription, eventTime);

        case FIND:
            String keyword = commandSections[1];
            return TaskList.find(keyword);

        case WITHIN:
            String withinContent = commandSections[1];
            String[] withinSegments = withinContent.split(" /within ");
            String withinDescription = withinSegments[0];
            String[] withinStartEnd = withinSegments[1].split(" ");
            String withinStart = withinStartEnd[0];
            String withinEnd = withinStartEnd[1];
            return TaskList.addWithin(withinDescription, withinStart, withinEnd);


        default:
            return "Sorry I didnt understand you!";
        }
    }
}