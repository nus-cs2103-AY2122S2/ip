package batman.ui;

import java.util.Scanner;

public class Ui {

    private final String line = "+" + String.valueOf('-').repeat(50) + "+";
    private final Scanner sc;
    private final String botName;

    public Ui(String botName) {
        sc = new Scanner(System.in);
        this.botName = botName;
    }

    public void greeting() {
        String logo = "          .  .\n"
                + "          |\\_|\\\n"
                + "          | a_a\\\n"
                + "          | | \"]\n"
                + "      ____| '-\\___\n"
                + "     /.----.___.-'\\\n"
                + "   /   .-. (~v~) /|\n"
                + "  |'|  \\:  .--  / \\\n"
                + " / |-/  \\_/____/\\/~|\n"
                + "|/  \\ |  []_|_|_] \\ |\n"
                + "| \\  | \\ |___   _\\ ]_}\n"
                + "| |    |  /  /  |    \\\n"
                + "\\ |    |/\\|  |/|/\\    \\\n"
                + " \\|\\ |\\|  |  | / /\\/\\__\\\n"
                + "  \\ \\| | /   | |__\n"
                + "       / |   |____)\n"
                + "       |_/";
        StringBuilder sb = new StringBuilder();
        sb.append(logo).append("\n").append("Hello! I'm ").append(botName)
                .append("\n").append("What can I do for you?\n");
        printOutput(sb);
    }

    public String read() {
        return sc.nextLine();
    }

    public void printOutput(StringBuilder input) {
        System.out.println(line + "\n" + input + line);
    }

    public void exit() {
        sc.close();
        StringBuilder sb = new StringBuilder();
        String logo = "          _,     _   _     ,_\n"
                + "      .-'` /     \\'-'/     \\ `'-.\n"
                + "     /    |      |   |      |    \\\n"
                + "    ;      \\_  _/     \\_  _/      ;\n"
                + "   |         ``         ``         |\n"
                + "   |                               |\n"
                + "    ;    .-.   .-.   .-.   .-.    ;\n"
                + "     \\  (   '.'   \\ /   '.'   )  /\n"
                + "      '-.;         V         ;.-'\n"
                + "          `                 `\n";
        sb.append("Bye. This city needs me. na na na na na na BATMAN\n").append(logo);
        printOutput(sb);
        System.exit(0);
    }

    public void showLoadingError(String error) {
        StringBuilder sb = new StringBuilder();
        sb.append(error);
        printOutput(sb);
    }
}
