import java.util.Scanner;

public class Ui {

    private final String logo;
    private final String line;
    private static final Scanner scanner = new Scanner(System.in);

    public Ui() {
        this.logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        this.line = "\t____________________________________________________________________";
    }

    public void showWelcome() {
        showLine();
        System.out.println(logo + "\t Hello! I'm Duke\n" + "\t What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMsg(String msg) {
        System.out.println(msg);
    }
}
