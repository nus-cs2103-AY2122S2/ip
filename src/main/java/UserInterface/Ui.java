package UserInterface;

/**
 * Class Ui that represents the interaction messages that user will have with Lucifer chatbot.
 * @author Yu Meng
 */
public class Ui {
    /** line after each command for ui purposes **/
    protected static final String UNDERSCORE = "____________________________________________________________";

    /**
     * Constructor for Class Ui.
     */
    public Ui() {

    }
    /**
     * Prints the farewell message when user inputs "bye".
     */
    public void farewell() {
        System.out.println(UNDERSCORE);
        System.out.println("（￣ｗ￣）Ψ Oh no! I hate to see you go:(");
        System.out.println("Have I granted your desires?\nPlease come back again!");
        System.out.println(UNDERSCORE);
    }

    /**
     * Prints the opening message when chatbot runs.
     */
    public void greeting() {
        System.out.println(UNDERSCORE);
        System.out.println("↜(╰ •ω• )╯ψ Hello there! I'm Lucifer.\nWhat is it you desire today?");
        System.out.println(UNDERSCORE);
    }

    /**
     * Prints the list of desires Lucifer chatbot can receive as input should user provide invalid input.
     */
    public static void helpCommands() {
        System.out.println(UNDERSCORE);
        System.out.println("Alright love, here are the list of desires I can grant:");
        System.out.println("\tlist\t :I will show you what your current desires are.");
        System.out.println("\tdelete\t :I will remove this desires from your current list.");
        System.out.println("\ttodo (desire)\t :I will add this desire to your todo list.");
        System.out.println("\tevent (desire) /at 31-12-2022 1800\t :I will add this desire to your list with the date & time.");
        System.out.println("\tdeadline (desire) /by 31-12-2022 1800\t :I will add this desire to your list with its deadline.");
        System.out.println("\tmark (number)\t :I can mark this desire in your list as done.");
        System.out.println("\tunmark (number)\t :I can unmark this desire in your list as not done.");
        System.out.println("\tbye\t: I will end our lovely little conversation for now");
        System.out.println("Now let's let back to what we are doing now shall we? ψ\uD83D\uDC7F\uD83D\uDD31⸸");
        System.out.println(UNDERSCORE);
    }
}
