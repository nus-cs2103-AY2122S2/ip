import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo =
                " ------    -------    -------   ---        ---   ---------    -------\n" +
                        "|____  |  |   _   |  |   _   |  \\  \\      /  /  |___   ___|  |   ____|\n" +
                        "     | |  |  |_|  |  |  |_|  |   \\  \\    /  /       | |      |  |____ \n" +
                        " _   | |  |   _   |  |      _|    \\  \\  /  /        | |      |_____  |\n" +
                        "| |__| |  |  | |  |  |  |\\  \\      \\  \\/  /      ___| |___    ____|  |\n" +
                        "|______|  |__| |__|  |__| \\__\\      \\____/      |_________|  |_______|\n"
                ;
        System.out.println("Welcome home, sir.\n" + logo);
        read();
    }
    public static void read() {
        LinkedList<String> tasks = new LinkedList<>();
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("___________________________________________________________");
            if (input.equalsIgnoreCase("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
            System.out.println("___________________________________________________________");
            input = sc.nextLine();
        }
        System.out.println("___________________________________________________________");
        System.out.println("Good bye, sir.");
        System.out.println("___________________________________________________________");
        sc.close();
    }
}

