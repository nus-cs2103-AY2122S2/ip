package kidsnd274.duke;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

// Handles Terminal Input and Output of the program
public class TextUi {
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke, your personal assistant\nWhat can I do for you?";
    private static final String MESSAGE_GOODBYE = "Goodbye!";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this.in = new Scanner(System.in);
        this.out = System.out;
        printLogo();
    }

    public String getInput() {
        return in.nextLine();
    }

    public void printIntro() {
        printMessage(generateIntro());
    }

    public void printLogo() {
        printMessage("Hello from\n" + LOGO);
    }

    public void printGoodbye() {
        printMessage(generateGoodbye());
    }

    public void printResults(String result) { // CHange this to CommandResult in the future
        printMessage(generateResponse(result));
    }

    private void printMessage(String input) {
        out.println(input);
    }

    private static String generateResponse(String input) {
        String barrier = "<---------------------------------------------------------->\n";
        return barrier + input + "\n" + barrier;
    }

    private static String generateIntro() {
        return generateResponse(MESSAGE_WELCOME);
    }

    private static String generateGoodbye() {
        return generateResponse(MESSAGE_GOODBYE);
    }

}
