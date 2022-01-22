import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcomeMessage();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            Parser p = new Parser(input);
            ParsedAnswer pa = p.parse();

        }
    }
}
