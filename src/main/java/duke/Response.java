package duke;

public class Response {
    public static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String RESPONSE_WELCOME = "Hello! I'm Duke\n" + "What can I do for you?";
    public static final String RESPONSE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String RESPONSE_MARKDONE = "Nice! I've marked this task as done: ";
    public static final String RESPONSE_ADDED = "Got it. I've added this task: ";
    public static final String RESPONSE_DELETED = "Noted. I've removed this task: ";
    public static final String RESPONSE_MISSINGDATE = "please specify the date";
    public static final String RESPONSE_MISSINGTIME = "please specify the time";
    public static final String RESPONSE_CANTUNDERSTAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static String taskNo(int i) {
        return "Now you have " + i + " tasks in the list.";
    }

}
