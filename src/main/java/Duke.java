/**
 * Duke Chatbot main method class.
 */

public class Duke {
    public static void main(String[] args) {
        Chat chat = new Chat();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        chat.start();
    }
}
