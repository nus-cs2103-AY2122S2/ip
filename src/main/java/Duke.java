import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");

        ArrayList<String> chatHistory = new ArrayList<String>();
        Scanner userInput = new Scanner(System.in);
        String nextInput = userInput.nextLine();

        while (!nextInput.equals("bye")) {
            switch (nextInput) {
                case "list":
                    int count = 1;
                    for (String record : chatHistory) {
                        System.out.println(count + ". " + record);
                        count++;
                    }
                    break;

                default:
                    chatHistory.add(nextInput);
                    System.out.println("added: " + nextInput);
                
            }
            nextInput = userInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
