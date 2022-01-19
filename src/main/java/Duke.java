import java.util.Scanner;

public class Duke {
    static String indent = "    ";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        dukeOutput("Hello! I'm YourBoss.\n" +
                indent +
                "What can you do for me?");

        String userInput = "";
        userInput = scanner.nextLine();

        while(!userInput.equals("bye")) {
            dukeOutput(userInput);
            userInput = scanner.nextLine();
        }

        dukeOutput("Bye. Hope I never see you again!");

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
