import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean isPolling = true;
        System.out.println(chatBox(greetingMessage()));

        while(isPolling) {
            String command = sc.nextLine();
            String replyMessage;
            if (command.equals("bye")) {
                isPolling = false;
                replyMessage = byeMessage();
            } else {
                replyMessage = echoCommand(command);
            }

            System.out.println(chatBox(replyMessage));
        }
    }

    public static String greetingMessage() {
        StringBuilder greeting = new StringBuilder();
        greeting.append("Wow! Hello! I'm Duke.\n");
        greeting.append("What can I do for you?\n");

        return greeting.toString();
    }

    public static String echoCommand(String msg) {
        return msg + "\n";
    }

    public static String byeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    //wraps a given text in a box to be printed
    public static String chatBox(String text) {
        StringBuilder box = new StringBuilder();
        box.append("----------------------------------------\n");
        box.append(text);
        box.append("----------------------------------------\n");

        return box.toString();
    }

}
