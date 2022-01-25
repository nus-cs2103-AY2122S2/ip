package backend;

public class InputDecoder {
    private Commands currentCommand = null;

    public InputDecoder() {}

    private String[] parseInput(String input) throws IllegalArgumentException {
        String[] commandSections = input.split(" ", 2);
        currentCommand = Commands.valueOf(commandSections[0]);
        return commandSections;
    }

    public void decode(String input) {
        String[] commandSections = null;

        // parse input supplied
        try {
            commandSections = this.parseInput(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry I didnt catch that! Please make sure it is a valid command!");
        }

        //do a certain action based on the parsed input
        switch (currentCommand) {
        case LIST:
            TaskList.list();
            break;
        case MARK:
            int indexMarked = Integer.parseInt(commandSections[1]) - 1;
            TaskList.mark(indexMarked);
            break;
        case UNMARK:
            int indexUnmmarked = Integer.parseInt(commandSections[1]) - 1;
            TaskList.unmark(indexUnmmarked);
            break;
        case DELETE:
            int indexDelete = Integer.parseInt(commandSections[1]) - 1;
            TaskList.delete(indexDelete);
            break;
        case TODO:
            String todoDescription = commandSections[1];
            TaskList.addTodo(todoDescription);
            break;
        case DEADLINE:
            String deadlineContent = commandSections[1];
            String[] deadlineSegments = deadlineContent.split(" /by ");
            String deadlineDescription = deadlineSegments[0];
            String deadlineTime = deadlineSegments[1];
            TaskList.addDeadline(deadlineDescription, deadlineTime);
            break;
        case EVENT:
            String eventContent = commandSections[1];
            String[] segments = eventContent.split(" /at ");
            String eventDescription = segments[0];
            String eventTime = segments[1];
            TaskList.addEvent(eventDescription, eventTime);

        default:
            System.out.println("Sorry I didnt understand you!");
        }
    }
}