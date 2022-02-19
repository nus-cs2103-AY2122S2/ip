package duke;


/**
 * The UI class contains methods that deals with interactions with the user.
 */
public class Ui {
    /**
     * Greeting message.
     */
    public static String welcome() {
        //Greetings
        String msg = "Greetings! I'm Duke" + "\n" + "What can I do for you today?\n";
        return msg;
    }

    /**
     * Goodbye message.
     */
    public static String bye() {
        //goodbye msg
        return "Sad to see you leave, come back soon!";
    }
}
