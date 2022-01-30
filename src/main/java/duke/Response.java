package duke;

/**
 * set of constant string values to respond to the user.
 */
public class Response {
    public static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String RESPONSE_WELCOME = "Hello! I'm Duke\n" + "What can I do for you?";
    public static final String RESPONSE_GOODBYE = "Bye. Call me again later!";
    public static final String RESPONSE_MARKDONE = "Good job! Keep goin!: ";
    public static final String RESPONSE_ADDED = "Oh no! More work to do!: ";
    public static final String RESPONSE_DELETED = "Yay! I've removed this task: ";
    public static final String RESPONSE_MISSINGDATE = "please specify the date";
    public static final String RESPONSE_MISSINGTIME = "please specify the time";
    public static final String RESPONSE_CANTUNDERSTAND = "OOPS!!! I'm sorry, but I don't know what that means :d";
    public static final String RESPONSE_SEARCH = "This is what I found from the List!";

    public static String taskNo(int i) {
        return "Now you have " + i + " tasks in the list.";
    }

}
