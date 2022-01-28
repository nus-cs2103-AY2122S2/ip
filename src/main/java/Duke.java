import java.time.LocalDate;
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
                    try {
                        if (id > tasks.size()) throw new DukeException("Invalid task id, sir.");
                        if (tasks.get(id - 1).isDone) throw new DukeException("This task was already completed, sir.");
                        tasks.get(id - 1).markAsDone();
                        System.out.println("Good job sir, this task is marked done:");
                        System.out.println("  " + tasks.get(id - 1));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
                case "unmark" :
                    id = sc.nextInt();
                    try {
                        if (id > tasks.size()) throw new DukeException("Invalid task id, sir.");
                        if (!tasks.get(id - 1).isDone) throw new DukeException("It wasn't even done yet, sir.");
                        tasks.get(id - 1).markAsNotDone();
                        System.out.println("Slacking off, sir? This task is marked not done:");
                        System.out.println("  " + tasks.get(id - 1));
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
                case "todo" :
                    sc.reset();
                    try {
                        input = sc.nextLine().strip();
                        if (input.isEmpty()) throw new DukeException("Do what, sir?");
                        tasks.add(new Todo(input));
                        System.out.println("I've added this to your tasks sir: " + tasks.getLast());
                        System.out.println("There are " + tasks.size() + " tasks on your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    finally {
                        break;
                    }
                case "deadline" :
                    sc.reset();
                    input = sc.nextLine().strip();
                    String[] deadline = input.split(" /by ");
                    try {
                        if (deadline.length < 2) throw new DukeException("Invalid deadline task, sir.");
                        LocalDate date = LocalDate.parse(deadline[1]);
                        tasks.add(new Deadline(deadline[0], date));
                        System.out.println("I've added this to your tasks sir: " + tasks.getLast());
                        System.out.println("There are " + tasks.size() + " tasks on your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
                case "event" :
                    sc.reset();
                    input = sc.nextLine().strip();
                    String[] event = input.split(" /at ");
                    try {
                        if (event.length < 2) throw new DukeException("Invalid event task, sir.");
                        tasks.add(new Event(event[0], event[1]));
                        System.out.println("I've added this to your tasks sir: " + tasks.getLast());
                        System.out.println("There are " + tasks.size() + " tasks on your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
                case "remove" :
                    id = sc.nextInt();
                    try {
                        if (id > tasks.size()) throw new DukeException("Invalid task id, sir.");
                        System.out.println("Too weak to handle it? This task have been removed sir: \n" + "  " + tasks.remove(id - 1));
                        System.out.println("There are " + tasks.size() + " tasks on your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
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

