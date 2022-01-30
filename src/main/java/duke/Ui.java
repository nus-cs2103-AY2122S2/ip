package duke;

import java.io.IOException;
import java.util.Scanner;
/**
 * This duke.Duke program is a Personal Assistant Chatbot
 * to keep track of various things.
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Ui {
    Parser parser;
    String line = "____________________________________________________________\n";
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
        System.out.println(createWelcomeMessage());
    }

    public String createWelcomeMessage() {
        StringBuilder welcomeMessage = new StringBuilder();
        String welcome = "Welcome to duke.Duke, your personal duke.Task assistant!\n";
        welcomeMessage.append(line).append(welcome).append(line);
        return welcomeMessage.toString();
    }

    public void displayCommandMessage(TaskList tasks, Storage storage) throws DukeException, IOException {
        System.out.println(askForCommand());
        parseUserInput(tasks, storage);
    }

    public String askForCommand() {
        StringBuilder commandLine = new StringBuilder();
        String command = "What is your command: \n";
        commandLine.append(command);
        return commandLine.toString();
    }

    public void parseUserInput(TaskList tasks, Storage storage) throws DukeException, IOException {
        String userInput = scanner.nextLine();
        parser = new Parser(userInput, tasks, storage);//Calls duke.Parser to parse information of userInput
        parser.parse();//and do the respective action from duke.TaskList
    }
}
