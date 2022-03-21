package duke;

/**
 * Handles the visual interaction with the user
 */
public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String lineBreak;

    Ui(String lineBreak) {
        this.lineBreak = lineBreak;

    }

    public void initUi() {
        System.out.println("Hello from\n" + LOGO);
        printLineBreak();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private final void printLineBreak() {
        System.out.println(this.lineBreak);
    }


}
