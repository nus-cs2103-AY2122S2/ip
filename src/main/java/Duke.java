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
        LinkedList<Task> tasks = new LinkedList<>();
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.next();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("___________________________________________________________");
            int id;
            switch(input) {
                case "list" :
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + "." + tasks.get(i - 1));
                    }
                    break;
                case "mark" :
                    id = sc.nextInt();
                    tasks.get(id - 1).markAsDone();
                    System.out.println("Good job sir, this task is marked done:");
                    System.out.println("  " + tasks.get(id - 1));
                    break;
                case "unmark" :
                    id = sc.nextInt();
                    tasks.get(id - 1).markAsNotDone();
                    System.out.println("Slacking off, sir? This task is marked not done:");
                    System.out.println("  " + tasks.get(id - 1));
                    break;
                default :
                    sc.reset();
                    input += sc.nextLine();
                    tasks.add(new Task(input));
                    System.out.println("added: " + input);
            }
            System.out.println("___________________________________________________________");
            input = sc.next();
        }
        System.out.println("___________________________________________________________");
        System.out.println("Good bye, sir.");
        System.out.println("___________________________________________________________");
        sc.close();
    }
}

