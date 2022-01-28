package duke;


/**
 * The UI class contains methods that deals with interactions with the user.
 */

public class Ui {

    /**
     * Greeting message.
     */
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Greetings
        System.out.println("Greetings! I'm Duke" + "\n" + "What can I do for you today?\n" + logo);
        System.out.println("What you like to do? Please enter your command: ");
    }

    /**
     * Goodbye message.
     */
    public static void bye() {
        //goodbye msg
        System.out.println("Sad to see you leave, come back soon!");
    }
}
