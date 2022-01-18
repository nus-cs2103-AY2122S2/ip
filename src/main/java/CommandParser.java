import java.util.Arrays;
import java.util.Scanner;

public class CommandParser {
    private final Scanner input;

    public CommandParser(Scanner input) {
       this.input = input;
    }

    public Command readAndParse() {
        String commandLine = this.input.nextLine();
        String[] tokens = commandLine.split(" ");

        if (tokens.length > 1)
            return new Command(tokens[0], Arrays.copyOfRange(tokens, 1, tokens.length));

        else
            return new Command(tokens[0]);
    }
}
