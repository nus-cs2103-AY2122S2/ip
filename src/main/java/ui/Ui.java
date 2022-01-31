package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private String output;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message banner.
     */
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

    /**
     * Prints a string to the console.
     *
     * @param str The string to print.
     */
    public void echo(String str) {
        System.out.println("------------------------------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Reads the user input from the console.
     *
     * @return The user input in lowercase.
     */
    public String readCommand() {
        return scanner.nextLine().toLowerCase();
    }

    /**
     * Prints a shutdown message and closes the standard output.
     */
    public String shutdown() {
        scanner.close();
        return "Goodbye. J.A.R.V.I.S. systems powering off...";
    }
}
