package duke.ui;

public class Ui {

    private String hLine = "____________________________________________________________";

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(hLine + "\n");
        System.out.println(logo + "\nHello! I'm Duke.Main.Duke\nWhat can I do for you?");
        System.out.println(hLine + "\n");
    }

    public void goodbye(){
        echo("Bye. Hope to see you again soon!");
    }

    public void echo(String input) {
        showLine();
        System.out.println(input);
        showLine();
    }

    public void showLine() {
        System.out.println(hLine + "\n");
    }
}
