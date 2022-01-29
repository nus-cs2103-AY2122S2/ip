package duke;

import java.util.Scanner;

public class Ui {

    private Scanner userInput;
    private String nextInput;


    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
    }

    public String getNextInput() {
         nextInput = userInput.nextLine();
         return nextInput;
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
