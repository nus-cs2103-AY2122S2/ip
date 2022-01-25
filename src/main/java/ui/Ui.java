package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcome() {
        String logo =
                "      _          _          ____       __     __      ___       ____\n"
                        + "     | |        / \\        |  _ \\      \\ \\   / /     |_ _|     / ___|\n"
                        + "  _  | |       / _ \\       | |_) |      \\ \\ / /       | |      \\___ \\\n"
                        + " | |_| |  _   / ___ \\   _  |  _ <   _    \\ V /    _   | |   _   ___) |  _\n"
                        + "  \\___/  (_) /_/   \\_\\ (_) |_| \\_\\ (_)    \\_/    (_) |___| (_) |____/  (_)\n\n";

        System.out.println("Starting up...\n"
                + "Online and ready.\n"
                + logo
                + "At your service.\n");
    }

    public void echo(String str) {
        System.out.println("------------------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------------------");
    }

    public String readCommand() {
        return scanner.nextLine().toLowerCase();
    }

    public void shutdown() {
        scanner.close();
        echo("Goodbye. J.A.R.V.I.S. systems powering off...");
    }
}
