import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String indent = "    ";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        ArrayList<Task> list = new ArrayList<>(); // arraylist to hold inputs

        dukeOutput("Hello! I'm YourBoss.\n" +
                indent +
                "What can you do for me?");

        String userInput = scanner.nextLine();

        while(true) {
            String[] splitUserInput = userInput.split(" ");
            String firstWord = splitUserInput[0];

            if (userInput.equals("bye")) { // exit loop
                dukeOutput("Bye. Hope I never see you again!");
                break;
            } else if (userInput.equals("list")) { // list out everything in list
                StringBuilder tempOut = new StringBuilder(list.get(0).toString());
                for (int i = 1; i<list.size();i++) {
                    tempOut.append(indent).append(list.get(i).toString());
                }
                dukeOutput(tempOut.toString());

            } else if (firstWord.equals("mark")) {
            } else if (firstWord.equals("unmark")) {
            } else { // add input to list
                list.add(new Task(userInput));
                dukeOutput("added: " + userInput);
            }
            userInput = scanner.nextLine();
        }

        scanner.close();
    }

    static void hLineBreak() {
        System.out.println(indent+"____________________________________________________________");
    }

    static void printlnWithIndent(String input) {
        System.out.print(indent);
        System.out.println(input);
    }

    static void dukeOutput(String output) {
        hLineBreak();
        printlnWithIndent(output);
        hLineBreak();
    }
}
