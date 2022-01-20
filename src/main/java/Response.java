public class Response {
    public static final
    String LOGO = " ____        _        \n"
                  + "|  _ \\ _   _| | _____ \n"
                  + "| | | | | | | |/ / _ \\\n"
                  + "| |_| | |_| |   <  __/\n"
                  + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String SEPARATOR = "--------------------------------------\n";
    public static final String WELCOME = "Hello! I'm Duke\n"
                                         + "What can I do for you?";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static void wrapPrint (String msg) {
        String[] splitted = msg.split("\n");
        String wrapee = "";
        for (String line : splitted) {
            wrapee = wrapee.concat("    " + line + "\n");
        }
        System.out.println(SEPARATOR + wrapee + SEPARATOR);


    }


}
