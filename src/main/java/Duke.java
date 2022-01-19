import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean isPolling = true;
        DukeEngine dukeEngine = new DukeEngine();

        System.out.println(chatBox(dukeEngine.greetingMessage()));

        while(isPolling) {
            String input = sc.nextLine();
            String[] commandArgs = input.split(" ", 2);
            String command = commandArgs[0];
            String commandDetails = commandArgs.length == 2 ? commandArgs[1] : null;

            String replyMessage = "";
            if (command.equals("bye")) {
                isPolling = false;
                replyMessage = dukeEngine.byeMessage();

            } else if (command.equals("list")){
                replyMessage = dukeEngine.listItems();
            
            } else if (command.equals("mark")) {

                int itemNumber = Integer.parseInt(commandDetails);
                replyMessage = dukeEngine.markItem(itemNumber);

            } else if (command.equals("unmark")) {

                int itemNumber = Integer.parseInt(commandArgs[1]);
                replyMessage = dukeEngine.unmarkItem(itemNumber);

            } else if (command.equals("todo")) {
                replyMessage = dukeEngine.addTask(new ToDo(commandDetails));
                
            } else if (command.equals("deadline")) {
                String[] taskArgs = commandDetails.split(" /by ");
                String taskName = taskArgs[0], taskDate = taskArgs[1];
                replyMessage = dukeEngine.addTask(new Deadline(taskName, taskDate));

            } else if (command.equals("event")) {
                String[] taskArgs = commandDetails.split(" /at ");
                String taskName = taskArgs[0], taskDate = taskArgs[1];
                replyMessage = dukeEngine.addTask(new Event(taskName, taskDate));
            } else {
                replyMessage = dukeEngine.echoInput(input);
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
