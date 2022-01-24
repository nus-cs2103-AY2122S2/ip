import java.util.StringTokenizer;

public class Parser {

    Parser() {
    }

    static Command parse(String fullCommand) throws DukeException {
        StringTokenizer st = new StringTokenizer(fullCommand);
        switch (st.nextToken()) {
            case "list":
                return new ListCommand(fullCommand);
            case "help":
                return new HelpCommand(fullCommand);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(fullCommand);
            case "mark":
            case "unmark":
                return new MarkCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "bye":
                return new ExitCommand(fullCommand);
        }

        throw new DukeException("That is not a valid command\nPlease type 'help' to see a list of valid commands");
    }
}
