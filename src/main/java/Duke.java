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

        ArrayList<String> inputList = new ArrayList<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input.toLowerCase(), "bye")) {
                System.out.println("\nGoodbye.\nHave a nice day!");
                break;
            } else if (Objects.equals(input.toLowerCase(), "list")) {
                if (!inputList.isEmpty()) {
                    inputList.forEach(System.out::println);
                }
            } else {
                System.out.println("added: " + input + "\n");
                inputList.add(input);
            }
        }
    }
}
