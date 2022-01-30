package bobby;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public Parser() {

    }

    public static void parse(TaskList tasks, String userInput, Bobby bobby) throws IOException {
        String[] inputs = userInput.split(" ", 2);
        String command = inputs[0];
        switch (Commands.valueOf(command.toUpperCase())) {
        case BYE:
            Ui.printExit();
            bobby.terminate();
            break;
        case LIST:
            tasks.list();
            break;
        case MARK:
            int i = Integer.parseInt(inputs[1]) - 1;
            tasks.mark(i);
            break;
        case UNMARK:
            int k = Integer.parseInt(inputs[1]) - 1;
            tasks.unmark(k);
            break;
        case TODO:
            try {
                tasks.addToDo(userInput);
            } catch (BobbyException e) {
                System.err.println(e);
            }
            break;
        case DEADLINE:
            try {
                tasks.addDeadline(userInput);
            } catch (BobbyException e) {
                System.err.println(e);
            }
            break;
        case EVENT:
            try {
                tasks.addEvent(userInput);
            } catch (BobbyException e) {
                System.err.println(e);
            }
            break;
        case DELETE:
            try {
                tasks.delete(userInput);
            } catch (BobbyException e) {
                System.err.println(e);
            }
            break;
        default:
            try {
                throw new BobbyException("Bobby does not understand you. Please use valid inputs.");
            } catch (BobbyException e) {
                System.err.println(e);
            }
        }
    }
}
