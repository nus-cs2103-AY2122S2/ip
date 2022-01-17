import ui.ChatBot;

import java.util.Scanner;

/**
 * @author Jiaaa-yang
 *
 * Main entry class to run ChatBot instance.
 */
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot chatBot = new ChatBot();
        chatBot.initialise();

        while (!chatBot.isTerminated()) {
            String input = scanner.nextLine();
            chatBot.runCommand(input);
        }
    }
}
