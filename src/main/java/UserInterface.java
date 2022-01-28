import java.util.Scanner;

public class UserInterface {
    private static final String LINE = "_______________________________________________";
    private static final String INTRO = "\nHello! I'm YQ, your assistant\n" + "What can I do for you?\n";
    private static final String BYE = "\nBye. Hope to see you again soon!\n";
    protected Scanner sc;

    public UserInterface() {
        sc = new Scanner(System.in);
    }

    public String readInput() {
        return this.sc.nextLine();
    }

    public void introMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = LINE + INTRO + logo + LINE;
        System.out.println(intro);
    }

    public void byeMessage() {
        System.out.println(LINE + BYE + LINE);
    }

    public void errorMessage(DukeException error) {
        System.out.println(error.getMessage() + "\n" + LINE);
    }

    public void lineDivider() {
        System.out.println(LINE);
    }
}
