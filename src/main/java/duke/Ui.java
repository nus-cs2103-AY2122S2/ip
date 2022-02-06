package duke;

import duke.util.Constants;

import java.util.List;
import java.util.Arrays;

/**
 * Main UI logic of the bot.
 */
public class Ui {

    /**
     * Prints a GAP added text for differentiating user and app texts.
     *
     * @param text Text to be printed.
     */
    public void print(String text) {
        List<String> lines = Arrays.asList(text.split("\n"));
        for (String line : lines) {
            System.out.println(Constants.GAP + line);
        }
        System.out.println(Constants.LINE);
    }

    /**
     * Prints initial greetings when the app starts.
     */
    public void greetUser() {
        System.out.println(Constants.LINE);
        print("Hello from\n" + Constants.LOGO);
        print("I am a chat bot and I'm here to help you be productive :)\n" +
                "What can I do for you today?");
    }

    /**
     * Prints goodbye message before the app ends.
     */
    public void finalBye() {
        print("Bye. Hope to see you again soon!");
    }
}
