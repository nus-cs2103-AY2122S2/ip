package duke.duke;
import java.util.Scanner;

public class Ui {

    String line = "------------------------------------";

    public Ui() {
        this.line = line;
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you today?");
        System.out.println(line);
    }

    public void showLine() {
        System.out.println(line);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
