import java.util.Arrays;
import java.util.Scanner;

public class CommandParser {
    private final Scanner input;

    public CommandParser(Scanner input) {
       this.input = input;
    }

    /**
     * Reads user input from previously specified input Scanner
     * Parses command if the first token is a valid command (delimiter = " ")
     * Else, treats the whole input line as a command term
     *
     * @return Parsed command
     */
    public Command readAndParse() {
        String commandLine = this.input.nextLine();
        String[] tokens = commandLine.split(" ");

        if (Command.isValidCommand(tokens[0])) {
            //
            if (tokens.length > 1)
                return new Command(tokens[0], Arrays.copyOfRange(tokens, 1, tokens.length));

            else
                return new Command(tokens[0]);
        }
        else {
           return new Command(commandLine);
        }
    }
}
