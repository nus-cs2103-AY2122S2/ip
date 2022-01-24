import java.util.Scanner;

public class Ui {
    private final String poogie =
            "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠉⠉⠉⠉⠉⠉⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
            "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⢀⣠⣶⣶⣶⣶⣤⡀⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿\n" +
            "    ⣿⣿⣿⣿⣿⣿⣿⣿⡏⠄⠄⣾⡿⢿⣿⣿⡿⢿⣿⡆⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿\n" +
            "    ⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄⠄⢿⣇⣸⣿⣿⣇⣸⡿⠃⠄⠄⠸⣿⣿⣿⣿⣿⣿⣿\n" +
            "    ⣿⣿⣿⣿⣿⡿⠋⠄⠄⠄⠄⠄⠉⠛⠛⠛⠛⠉⠄⠄⠄⠄⠄⠄⠙⣿⣿⣿⣿⣿\n" +
            "    ⣿⣿⣿⣿⡟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿⣿\n" +
            "    ⣿⣿⣿⡟⠄⠄⠄⠠⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿\n" +
            "    ⣿⣿⡟⠄⠄⠄⢠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣧⠄⠄⠄⠈⢿⣿\n" +
            "    ⣿⣿⡇⠄⠄⠄⣾⣿⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢰⣿⣧⠄⠄⠄⠘⣿\n" +
            "    ⣿⣿⣇⠄⣰⣶⣿⣿⣿⣦⣀⡀⠄⠄⠄⠄⠄⠄⠄⢀⣠⣴⣿⣿⣿⣶⣆⠄⢀⣿\n" +
            "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⢸⣿⠇⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
            "    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣴⣾⣿⣶⣤⣤⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n";
    String divider = "    ============================================================\n";

    public String tab(String s) {
        return "    " + s;
    }

    public void replyUser(String reply) {
        String s = reply.replace("\n", "\n    ");
        System.out.println(divider + tab(s) + "\n" + divider);
    }

    public void greetUser() {
        System.out.println(poogie);
        replyUser("Hello! My name is Poogie.\n" + "How may I be of service to you?");
    }

    public void showLine() {
        System.out.println(divider);
    }

    public String readCommand() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }
}
