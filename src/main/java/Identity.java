public class Identity {
    //Greeting
    //Logo
    //Goodbye
    private final static String GREETING = "Hello! I'm ";
    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String GOODBYE = "Bye. Hope to see you again soon!";
    private final static String EXITKEY = "bye";

    public static boolean exitMatch(String key) {
        return key.equals(EXITKEY);
    }

    public static void exitProg() {
        System.out.println("--------------------------------" +
                "------------------------\n"
                + GOODBYE
                + "\n"
                + "--------------------------------------------------------");
    }
}
