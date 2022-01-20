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
                    System.out.println("Your tasks list, sir:");
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
                case "todo" :
                    sc.reset();
                    input = sc.nextLine().strip();
                    tasks.add(new Todo(input));
                    System.out.println("I've added this to your tasks sir: " + tasks.getLast());
                    System.out.println("There are " + tasks.size() + " tasks on your list.");
                    break;
                case "deadline" :
                    sc.reset();
                    input = sc.nextLine().strip();
                    String[] deadline = input.split(" /by ");
                    tasks.add(new Deadline(deadline[0], deadline[1]));
                    System.out.println("I've added this to your tasks sir: " + tasks.getLast());
                    System.out.println("There are " + tasks.size() + " tasks on your list.");
                    break;
                case "event" :
                    sc.reset();
                    input = sc.nextLine().strip();
                    String[] event = input.split(" /at ");
                    tasks.add(new Deadline(event[0], event[1]));
                    System.out.println("I've added this to your tasks sir: " + tasks.getLast());
                    System.out.println("There are " + tasks.size() + " tasks on your list.");
                    break;
                default :
                    sc.reset();
                    sc.nextLine();
                    System.out.println("Unknown command, sir.");
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

