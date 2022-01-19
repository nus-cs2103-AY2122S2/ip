import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean isPolling = true;
        DukeEngine dukeEngine = new DukeEngine();

        System.out.println(chatBox(dukeEngine.greetingMessage()));

        while(isPolling) {
            String command = sc.nextLine();
            String[] commandArgs = command.split(" ");

            String replyMessage = "";
            if (commandArgs[0].equals("bye")) {
                isPolling = false;
                replyMessage = dukeEngine.byeMessage();

            } else if (commandArgs[0].equals("list")){
                replyMessage = dukeEngine.listItems();
            
            } else if (commandArgs[0].equals("mark")) {

                int itemNumber = Integer.parseInt(commandArgs[1]);
                replyMessage = dukeEngine.markItem(itemNumber);

            } else if (commandArgs[0].equals("unmark")) {

                int itemNumber = Integer.parseInt(commandArgs[1]);
                replyMessage = dukeEngine.unmarkItem(itemNumber);

            } else {
                replyMessage = dukeEngine.addText(command);
            }

            System.out.println(chatBox(replyMessage));
        }

        sc.close();
    }

    //wraps a given text in a box to be printed
    public static String chatBox(String text) {
        StringBuilder box = new StringBuilder();
        box.append("----------------------------------------\n");
        box.append(text);

        //appends a newline if provided text does not have one
        if (text.charAt(text.length() - 1) != '\n') box.append("\n");

        box.append("----------------------------------------\n");

        return box.toString();
    }
}
