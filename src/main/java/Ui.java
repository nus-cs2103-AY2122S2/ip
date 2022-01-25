import java.io.PrintStream;

import java.util.Scanner;

public class Ui {
    private static final String DELIMITER = "*******************************************************";
    
    private final Scanner in;
    private final PrintStream out;
    
    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }
    
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.println(logo);
        out.println("Hello! I'm Duke");
        out.println("What can I do for you?");
    }

    public String getCommand() {
        out.println(DELIMITER);
        out.print("Enter your command: ");
        
        String command = in.nextLine();
        return command;
    }
    
    public void sayBye() {
        out.println("Bye. Hope to see you again soon!");
    }
    
    public void showMessage(String message) {
        out.println(message);
    }

    public void showErrorMessage(String message) {
        out.println("Uh oh! " + message + " :(");
    }
}
