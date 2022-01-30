import java.io.IOException;

public class Parser {


    public static Command parse(String input) {

        try {
            String[] inputWords = input.split("\\s");
            Action action = Action.valueOf(inputWords[0].toUpperCase());    //action is first word of the input
            switch (action) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case TODO:
                    return new AddCommand(Action.TODO, input);
                case DEADLINE:
                    return new AddCommand(Action.DEADLINE, input);
                case EVENT:
                    return new AddCommand(Action.EVENT, input);
                case MARK:
                    return new MarkCommand(inputWords);
                case UNMARK:
                    return new UnmarkCommand(inputWords);
                case DELETE:
                    return new DeleteCommand(inputWords);
                default:
                    return new InvalidCommand();
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }
    }
}
