package lucifer.ui;

/**
 * Class Ui that represents the interaction messages that user will have with Lucifer chatbot.
 * @author Yu Meng
 */
public class Ui {
    /**
     * Constructor for Class Ui.
     */
    public Ui() {

    }
    /**
     * Prints the farewell message when user inputs "bye".
     */
    public static String farewell() {
        return "\uD83D\uDC7F\uD83D\uDD31 Oh no! I hate to see you go:(\n"
                + "Have I granted your desires?\nPlease come back again!\n";
    }

    /**
     * Prints the opening message when chatbot runs.
     */
    public static String greeting() {
        return "\uD83D\uDC7F\uD83D\uDD31 Hello there! I'm Lucifer.\nWhat is it you desire today?";
    }

    /**
     * Prints the list of commands Lucifer chatbot can receive as input should user provide invalid input.
     */
    public static String helpCommands() {
        return "\uD83D\uDC7F\uD83D\uDD31 Alright love, here are the list of desires I can grant:\n"
                + "list:  I will show you what your current desires are."
                + "\ndelete (number):  I will remove this desires from your current list."
                + "\ntodo (desire):  I will add this desire to your todo list."
                + "\nevent (desire) /at 31-12-2022 1800:  I will add this desire to your list with the date & time."
                + "\ndeadline (desire) /by 31-12-2022 1800:  I will add this desire to your list with its deadline."
                + "\nmark (number):  I can mark this desire in your list as done."
                + "\nunmark (number):  I can unmark this desire in your list as not done."
                + "\nbye: I will end our lovely little conversation for now"
                + "\nfind: I will find the desire you are looking for in your current desires"
                + "\nclear: I will remove all your current desires"
                + "\nNow let's let back to what we are doing now shall we?";
    }
}
