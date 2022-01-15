import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int count = 1;

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
        String input = scanner.nextLine();

        while (!input.equals("bye"))
        {
            if (input.equals("list"))
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < count; i++)
                {
                    sb.append(i).append(". ").append(tasks[i]).append(i + 1 == count ? "" : "\n");
                }
                echo(sb.toString());
            }
            else
            {
                tasks[count] = input;
                count++;
                echo("Added: " + input);
            }
            input = scanner.nextLine();
        }

        System.out.println("Goodbye. J.A.R.V.I.S. systems powering off...");
    }

    public static void echo(String str)
    {
        System.out.println("------------------------------");
        System.out.println(str);
        System.out.println("------------------------------");
    }
}
