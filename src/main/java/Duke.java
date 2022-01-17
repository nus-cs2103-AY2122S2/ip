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
            }

            else if (Objects.equals(input.toLowerCase(), "list")) {
                System.out.println("Here are the tasks in your list: " + "\n");
                if (!inputList.isEmpty()) {
                    for (int i = 0; i < inputList.size(); i++) {
                        System.out.println( (i + 1) + ". "  + inputList.get(i) + "\n");
                    }
                }
            }

            else if (input.toLowerCase().contains("unmark")) {
                String[] parsedInput = input.toLowerCase().split("\\s+");
                int idx = Integer.parseInt(parsedInput[1]);
                if (idx - 1 >= inputList.size()) {
                    System.out.println("Index provided is out of range.");
                } else {
                    System.out.println("OK. I've marked your task as incomplete.");
                    inputList.get(idx - 1).markAsUndone();
                }
            }

            else if (input.toLowerCase().contains("mark")) {
                String[] parsedInput = input.toLowerCase().split("\\s+");
                int idx = Integer.parseInt(parsedInput[1]);
                if (idx - 1 >= inputList.size()) {
                    System.out.println("Index provided is out of range.");
                } else {
                    System.out.println("Good job. I've marked your task as complete.");
                    inputList.get(idx - 1).markAsDone();
                }
            }

            else {
                String[] parsedString = input.toLowerCase().split(" ", 2);
                if (parsedString[0].equalsIgnoreCase("deadline")) {
                   String [] dlParseBy = parsedString[1].split("/by");
                   if (dlParseBy.length <= 1) {
                       System.out.println("Please include a date.");
                   } else {
                       Deadline dl  = new Deadline(dlParseBy[0], dlParseBy[1]);
                       inputList.add(dl);
                   }
                }
            }
        }
    }
}
