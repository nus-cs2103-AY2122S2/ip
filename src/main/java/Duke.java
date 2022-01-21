import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private static final String br = "\t____________________________________________________________\n";
    private static List<Task> lst = new ArrayList<Task>();

    /**
     * Print out the greeting words.
     */
    private static void greeting() {
        String sentences = "\t Hello! I'm Duke\n\t What can I do for you?\n";
        System.out.println(Duke.br + sentences + Duke.br);
    }

    /**
     * Print out the goodbye words.
     */
    private static void bye() {
        String sentences = "\t Bye. Hope to see you again soon!\n";
        System.out.println(Duke.br + sentences + Duke.br);
    }

	/**
	 * List out the current tasks as well as their status.
	 */
    private static void list() {
        System.out.print(Duke.br);
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println("\t " + i + "." + lst.get(i-1).print());
        }
        System.out.println(Duke.br);
    }

	/**
	 * Add a task to the todo list.
	 *
	 * @param description The description of the task to be added.
	 */
	private static void add(String description) {
		Task t = new Task(description);
		Duke.lst.add(t);
        System.out.println(Duke.br + "\t added: " + description + "\n" + Duke.br);
	}

	/**
	 * Mark a particular task as done.
	 *
	 * @param num The number of the task to be marked.
	 */
	private static void mark(int num) {
		lst.get(num).markAsDone();
		System.out.println(Duke.br + "\t Nice! I've marked this task as done:\n\t   " + lst.get(num).print() + "\n" + Duke.br); 
	}

	/**
	 * Unmark a particular task as done.
	 *
	 * @param num The number of the task to be unmarked.
	 */
	private static void unmark(int num) {
		lst.get(num).unmarkAsDone();
		System.out.println(Duke.br + "\t OK, I've marked this task as not done yet:\n\t   " + lst.get(num).print() + "\n" + Duke.br);
	}

    public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
        String input;

        Duke.greeting();
        input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Duke.list();
                input = sc.nextLine();
                continue;
            }
            String[] splitted = input.split("\\s+");
            if (splitted.length == 2 && splitted[0].equals("mark")) {
				try {
					int num = Integer.parseInt(splitted[1]);
					Duke.mark(num-1);
					input = sc.nextLine();
					continue;
				} catch (NumberFormatException e) {
					Duke.add(input);
				}
            } else if (splitted.length == 2 && splitted[0].equals("unmark")) {
				try {
					int num = Integer.parseInt(splitted[1]);
					Duke.unmark(num-1);
					input = sc.nextLine();
					continue;
				} catch (NumberFormatException e) {
					Duke.add(input);
				}
			}
			Duke.add(input);
            input = sc.nextLine();
        }
        Duke.bye();
    }
}
