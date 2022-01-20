import java.util.Scanner;

public class Duke {

    /**
     * Convert the plain output text to chat-box style with indentation for output
     *
     * @param text : String (output text)
     * @return chat-box style output text : String
     */
    private static String output_chat_box(String text) {
        return "    ____________________________________________________________\n"
                + text.replaceAll("(?m)^", "     ") + "\n"
                + "    ____________________________________________________________\n";
    }

    /**
     * The main body of the chat box. Will receive commands and do things accordingly
     **/
    private static class process_IO {
        static void run() {
            Scanner scanner = new Scanner(System.in);
            String farewell_words = "Bye. Hope to see you again soon!";

            while(true) {
                String command = scanner.nextLine();
                if (command.equals("bye") || command.equals("Bye")) {
                    System.out.print(output_chat_box(farewell_words));
                    break;
                } else {
                    System.out.print(output_chat_box(command));
                }
            }

            return ;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Pyke\n" + "What can I do for you?";
        System.out.print(output_chat_box(greeting));
        process_IO.run();
    }
}
