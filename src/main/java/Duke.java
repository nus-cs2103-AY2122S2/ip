import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> objList = new ArrayList<>();
        System.out.println("Hello! I'm Whey\n" + "What can I do for you today?");
        Scanner userInput = new Scanner(System.in);
        try {
            while (true) {
                String nextLine = userInput.nextLine();
                if (nextLine.equals("bye")) {
                    System.out.println("  " + "Bye beautiful! hope to see you again hehe");
                    break;
                } else if (nextLine.equals("list")) {
                    int counter = 1;
                    for (String s : objList) {
                        System.out.println("  " + counter + ". " + s);
                        counter++;
                    }
                } else if (nextLine.isBlank()) {
                    System.out.println("You didn't key in anything!! Feed me with a task!");
                } else {
                    objList.add(nextLine);
                    System.out.println("  " + "added: " + nextLine);
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Error detected, Whey is shutting down!");
        }
    }
}
