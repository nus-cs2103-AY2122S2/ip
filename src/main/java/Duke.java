import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHow may I help you today?\n");

        ArrayList<Task> inputList = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input.toLowerCase(), "bye")) {
                System.out.println("\nGoodbye.\nHave a nice day!");
                break;
            } else if (Objects.equals(input.toLowerCase(), "list")) {
                System.out.println("Here are the tasks in your list: " + "\n");
                if (!inputList.isEmpty()) {
                    for (int i = 0; i < inputList.size(); i++) {
                        System.out.println( (i + 1) + ". "
                                + "[" + inputList.get(i).getStatusIcon() + "] "
                                + inputList.get(i).description + "\n");
                    }
                }
            } else {
                Task t = new Task(input);
                System.out.println("added: " + input + "\n");
                inputList.add(t);
            }
        }
    }
}
