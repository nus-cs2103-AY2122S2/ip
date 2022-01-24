import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void hello() {
        output(Constants.GREET);
    }

    public void output(String output) {
        String result =
                "____________________________________________________________\n\n"
                + output
                + "\n____________________________________________________________\n\n";
        System.out.printf("%s", result);
    }

    public void showLoadingError() {
        output(Constants.STORAGE_READ_MSG);
    }

    public void bye() {
        output(Constants.BYE);
    }

    public String readCommand() {
        String command = sc.nextLine().trim();

        return command;
    }

    public void showGenericError(String errorMsg) {
        output(errorMsg);
    }
}
