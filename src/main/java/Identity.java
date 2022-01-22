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
    //command keys to enum
    private final static String EXITKEY = "bye";
    private final static String LISTKEY = "list";
    private final static String DELETEKEY = "delete";

    public static boolean exitMatch(String key) {
        return key.equals(EXITKEY);
    }

    public static boolean deleteMatch(String nextKey) {
        return nextKey.matches("delete\\s\\d");
    }

    public static boolean listMatch(String key) {
        return key.equals(LISTKEY);
    }

    public static boolean markMatch(String nextKey) {
        return nextKey.matches("mark\\s\\d");
    }

    public static boolean unmarkMatch(String nextKey) {
        return nextKey.matches("unmark\\s\\d");
    }

    public static boolean todoMatch(String nextKey) {
        return nextKey.matches("^todo.*$");
    }

    public static boolean eventMatch(String nextKey) {
        return nextKey.matches("^event.*$");
    }

    public static boolean deadlineMatch(String nextKey) {
        return nextKey.matches("^deadline.*$");
    }

    public static void exitProg() {
        System.out.println("--------------------------------" +
                "------------------------\n"
                + GOODBYE
                + "\n"
                + "--------------------------------------------------------");
    }


}
