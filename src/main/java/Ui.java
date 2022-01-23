import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = "   _____       _ _                        \n" +
                "  / ____|     (_) |                       \n" +
                " | (___   __ _ _| |_ __ _ _ __ ___   __ _ \n" +
                "  \\___ \\ / _` | | __/ _` | '_ ` _ \\ / _` |\n" +
                "  ____) | (_| | | || (_| | | | | | | (_| |\n" +
                " |_____/ \\__,_|_|\\__\\__,_|_| |_| |_|\\__,_|\n";

        System.out.println(logo);
        showLine();
        System.out.println("I'm Saitama, a hero for fun.");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showSave() {
        System.out.println("OK...");
        showLine();
        System.out.println("Saving...");
    }
}
