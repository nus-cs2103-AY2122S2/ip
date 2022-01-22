import java.util.Scanner;

public class Ui {
    private String greeting = "Hello! I'm TaskJamie\nWhat can i do for you?";
    private String ending =  "Bye. Hope to see you again soon!";

    public void showGreeting() {
        System.out.println(greeting);
    }

    public void showEnding() {
        System.out.println(ending);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError(String error) { System.out.println(error); }

    public String readCommand() throws BlankCommandException {
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();
        if (input.length() == 0) {
            throw new BlankCommandException();
        }
        return input;
    }
}
