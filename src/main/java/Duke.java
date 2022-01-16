import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        String intro = "    Hello! I'm Duke! \n"
                + "    What can I do for you? \n";

        String line = "    ―――――――――――――――――――――――――――――――――― \n";

        String indent = "    ";

        System.out.print(logo + "\n" + intro + line);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.print(line + indent + input + "\n" + line);
            input = scanner.nextLine();
        }

        System.out.print(line + indent + "Bye. Hope to see you again soon!" + "\n" + line);
    }
}
