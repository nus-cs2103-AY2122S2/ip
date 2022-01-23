public class Parser {
    public static Command parse(String fullCommand) throws SaitamaException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String command = splitCommand[0].toUpperCase();
        splitCommand[0] = command;

        switch (command) {
        case "BYE":
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return prepareMark(splitCommand);
        case "UNMARK":
            return prepareUnmark(splitCommand);
        case "DELETE":
            return prepareDelete(splitCommand);
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            return prepareAdd(splitCommand);
        default:
            throw new InvalidCommandException();
        }
    }

    private static Command prepareMark(String[] splitCommand) throws InvalidTaskNumberException {
        if (splitCommand.length < 2 || !isNumeric(splitCommand[1])) {
            throw new InvalidTaskNumberException();
        }
        int taskNumber = Integer.parseInt(splitCommand[1]);
        return new MarkCommand(taskNumber);
    }

    private static Command prepareUnmark(String[] splitCommand) throws InvalidTaskNumberException {
        if (splitCommand.length < 2 || !isNumeric(splitCommand[1])) {
            throw new InvalidTaskNumberException();
        }
        int taskNumber = Integer.parseInt(splitCommand[1]);
        return new UnmarkCommand(taskNumber);
    }

    private static Command prepareDelete(String[] splitCommand) throws InvalidTaskNumberException {
        if (splitCommand.length < 2 || !isNumeric(splitCommand[1])) {
            throw new InvalidTaskNumberException();
        }
        int taskNumber = Integer.parseInt(splitCommand[1]);
        return new DeleteCommand(taskNumber);
    }

    private static Command prepareAdd(String[] splitCommand) throws SaitamaException {
        if (splitCommand.length < 2) {
            throw new EmptyDescriptionException();
        }

        String taskType = splitCommand[0];
        String taskArguments = splitCommand[1];
        String[] taskArgumentsList;
        String taskDescription;
        Task newTask;

        switch (taskType) {
        case "TODO":
            newTask = new ToDo(taskArguments);
            return new AddCommand(newTask);
        case "DEADLINE":
            taskArgumentsList = taskArguments.split(" /by ", 2);
            if (taskArgumentsList.length < 2) {
                throw new InvalidFormatException("You need to specify the date of the deadline! <Deadline> /by <dd/mm/yyyy>");
            }
            taskDescription = taskArgumentsList[0];
            String deadline = taskArgumentsList[1];
            newTask = new Deadline(taskDescription, deadline);
            return new AddCommand(newTask);
        case "EVENT":
            taskArgumentsList = taskArguments.split(" /at ", 2);
            if (taskArgumentsList.length < 2) {
                throw new InvalidFormatException("You need to specify event location! <Event> /at <location>");
            }
            taskDescription = taskArgumentsList[0];
            String location = taskArgumentsList[1];
            newTask = new Event(taskDescription, location);
            return new AddCommand(newTask);
        }
        throw new InvalidCommandException();
    }

    private static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
