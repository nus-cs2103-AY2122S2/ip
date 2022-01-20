import java.util.ArrayList;
public class Response {
    public static final
    String LOGO = " ____        _        \n"
                  + "|  _ \\ _   _| | _____ \n"
                  + "| | | | | | | |/ / _ \\\n"
                  + "| |_| | |_| |   <  __/\n"
                  + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String WELCOME = "Hello! I'm Duke\n"
                                         + "What can I do for you?";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MARKDONE = "Nice! I've marked this task as done: ";
    public static final String ADDED = "Got it. I've added this task:";
    public static String taskNo(int i) {
        return "Now you have " + i + " tasks in the list.";
    }

}
