import java.util.ArrayList;
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
        System.out.println(wrap(wrapee));
    }
    private static String wrap(String msg) {
        return SEPARATOR + msg + SEPARATOR;
    }
    public static void list (ArrayList<String> todoList) {
        String wrapee = "";
        for (int i = 0; i < todoList.size(); i++) {
            wrapee = wrapee + (i + 1) + ". " + todoList.get(i) + "\n";
        }
        wrapPrint(wrapee);
    }

    public static void echo (String msg) {
        wrapPrint(msg);
    }

}
