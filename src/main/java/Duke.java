import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String indent = "    ";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        ArrayList<String> list = new ArrayList<String>(); // arraylist to hold inputs

        dukeOutput("Hello! I'm YourBoss.\n" +
                indent +
                "What can you do for me?");

        String userInput = scanner.nextLine();

        while(true) {
            if (userInput.equals("bye")) { // exit loop
                dukeOutput("Bye. Hope I never see you again!");
                break;
            } else if (userInput.equals("list")) { // list out everything in list
                String tempOut = "1. " + list.get(0);
                for (int i = 1; i<list.size();i++) {
                    tempOut = tempOut + "\n" + indent + (i+1) + ". " + list.get(i);
                }
                dukeOutput(tempOut);

            } else { // add input to list
                list.add(userInput);
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
