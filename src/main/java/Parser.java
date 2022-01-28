public class Parser {

    public static int parseInt(String strToIntValue) throws DukeException {
        try {
            return Integer.parseInt(strToIntValue);
        } catch (Exception e) {
            throw new DukeException("Please provide the item NUMBER to remove.");
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        Command cmd = null;
        try {
            String[] input = fullCommand.split(" ", 2);
            switch (input[0].toLowerCase()) {
            case "exit":
                cmd = new ExitCommand();
                break;
            case "list":
                cmd = new ListCommand();
                break;
            case "mark":
            case "unmark":
                cmd = new ToggleCommand(input[0], input[1]);
                break;
            case "todo":
            case "event":
            case "deadline":
                cmd = new AddCommand(input[0], input[1]);
                break;
            case "remove":
            case "delete":
                cmd = new DeleteCommand(input[1]);
                break;
            default:
                cmd = new InvalidCommand();
                break;
            }
        } catch (Exception e) {
            throw new DukeException("Please ensure the proper formatting of commands.");
        }
        return cmd;
    }

    public static Task stringToTask(String entry) throws DukeException {
        String[] input = entry.split(" \\| ");
        Task newTask;
        switch (input[0]) {
        case "T":
            newTask = new ToDo(input[2],input[1]);
            break;
        case "D":
            newTask = new Deadline(input[2], input[3], input[1]);
            break;
        case "E":
            newTask = new Event(input[2], input[3], input[1]);
            break;
        default:
            throw new DukeException("Invalid keyword from source file.");
        }

        return newTask;
    }
}