import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  "      _          _          ____       __     __      ___       ____      \n"
                + "     | |        / \\        |  _ \\      \\ \\   / /     |_ _|     / ___|     \n"
                + "  _  | |       / _ \\       | |_) |      \\ \\ / /       | |      \\___ \\     \n"
                + " | |_| |  _   / ___ \\   _  |  _ <   _    \\ V /    _   | |   _   ___) |  _ \n"
                + "  \\___/  (_) /_/   \\_\\ (_) |_| \\_\\ (_)    \\_/    (_) |___| (_) |____/  (_)\n"
                + "                                                                          \n";
        System.out.println("Starting up...\n"
                + "Online and ready.\n"
                + logo
                + "At your service.\n");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye"))
        {
            echo(command);
            command = scanner.nextLine();
        }

        System.out.println("Goodbye. J.A.R.V.I.S. systems powering off...");

    }

    public static void echo(String str)
    {
        System.out.println("\t\t------------------------------");
        System.out.println("\t\t" + str);
        System.out.println("\t\t------------------------------");
    }
}
