import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(generateIntro());
        // Starting input loop
        Scanner sc = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>();
        boolean done = false;
        while(!done) {
            String input = sc.nextLine();
            if (input.equals("quit") || input.equals("exit")) {
                done = true;
                System.out.println(generateGoodbye());
            } else if (input.equals("list")) { // List
                String listOutput = "";
                for (int i = 0; i < toDoList.size(); i++) {
                    if (i != 0) {
                        listOutput += "\n" + (i + 1) + ". " + toDoList.get(i);
                    } else {
                        listOutput += (i + 1) + ". " + toDoList.get(i);
                    }
                }
                System.out.println(generateResponse(listOutput));
            } else { // Add to list
                toDoList.add(input);
                String message = "added " + input;
                System.out.println(generateResponse(message));
            }
        }
    }

    public static String generateResponse(String input) {
        String temp = "<---------------------------------------------------------->\n";
        return temp + input + "\n" + temp;
    }

    public static String generateIntro() {
        String welcomeMessage = "Hello! I'm Duke, your personal assistant\nWhat can I do for you?";
        return generateResponse(welcomeMessage);
    }

    public static String generateGoodbye() {
        String goodbyeMessage = "Goodbye!";
        return generateResponse(goodbyeMessage);
    }
}
