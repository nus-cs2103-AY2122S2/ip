import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcomeMessage();
<<<<<<< HEAD
        Storage storage = new Storage();
        storage.load();
=======
>>>>>>> branch-Level-8

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            Parser p = new Parser(input);
            ParsedAnswer pa = p.parse();
<<<<<<< HEAD
            ParsedAnswerHandler parsedAnswerHandler = new ParsedAnswerHandler(pa);
            parsedAnswerHandler.execute();
=======
>>>>>>> branch-Level-8
        }
    }
}
