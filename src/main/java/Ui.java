import java.util.Scanner;

public class Ui {
    static Scanner sc = new Scanner(System.in);

    public static Command getCommand() {
        Command actionType = null;
        while (actionType == null) {
            try {
                String command = sc.next();
                actionType = Parser.parseCommand(command);
            } catch (CommandNotFoundException e) {
                System.out.println("Sorry, i don't understand what you are saying");
                actionType = null;
                sc.nextLine();
            }
        }
        return actionType;
    }

    public static String[] getInputs() {
        String input = sc.nextLine();
        return Parser.parseInput(input);
    }
}
