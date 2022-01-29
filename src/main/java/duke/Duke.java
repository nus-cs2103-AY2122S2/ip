package duke;

/**
 * duke.Duke Chatbot main method class.
 */

public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ui.start();
    }
}
