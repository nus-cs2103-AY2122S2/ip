import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String LINE = "*~*~*~*~*~*~*~*~*~*~*~*~*~*~*";
    public static ArrayList<Task> tasks = new ArrayList<>(100);
    public static int tasksAdded_index = 0;

    public static boolean list_isEmpty() {
        return tasksAdded_index == 0;
    }

    private static void message_list() {
        if (list_isEmpty()) {
            System.out.println(LINE + "\nYour list is empty~\n" + LINE);
        } else {
            System.out.println(LINE + "\nHere is your task list!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    private static void message_mark(String user_message) throws DukeException {
        //list is empty
        if (list_isEmpty()) {
            throw new DukeException(LINE
                    + "\nOops... your list is empty! :(\n" + LINE);
        }

        String clean = user_message.replaceAll("\\D+", "");
        //empty message after mark
        if (clean.equals("")) {
            throw new DukeException(LINE
                    + "\nWhich task do you want to mark? Add the number in the end to tell me~\n"
                    + LINE);
        }

        int taskIndex = Integer.parseInt(clean) - 1;
        //user trying to mark a non-existing task
        if (taskIndex >= tasksAdded_index || taskIndex < 0) {
            throw new DukeException(LINE
                    + "\nThere is no number " + (taskIndex + 1) + " in your task list!\n" + LINE);
        }

        tasks.get(taskIndex).markAsDone();
        System.out.println(LINE + "\nYay! I've marked this task as done:\n"
                + tasks.get(taskIndex).toString() + "\n" + LINE);
    }

    private static void message_unmark(String user_message) throws DukeException {
        //list is empty
        if (list_isEmpty()) {
            throw new DukeException(LINE
                    + "\nOops... your list is empty! :(\n" + LINE);
        }

        String clean = user_message.replaceAll("\\D+", "");
        //empty message after mark
        if (clean.equals("")) {
            throw new DukeException(LINE
                    + "\nWhich task do you want to unmark? Add the number in the end to tell me~\n"
                    + LINE);
        }

        int taskIndex = Integer.parseInt(clean) - 1;
        //user trying to mark a non-existing task
        if (taskIndex >= tasksAdded_index || taskIndex < 0) {
            throw new DukeException(LINE
                    + "\nThere is no number " + (taskIndex + 1) + " in your task list!\n"
                    + LINE);
        }

        tasks.get(taskIndex).unmark();
        System.out.println(LINE + "\nAw man..I've marked this task as not done yet:\n"
                + tasks.get(taskIndex).toString() + "\n" + LINE);
    }

    private static void message_todo(String user_message) throws DukeException {
        if (user_message.substring(4).replaceAll(" ", "").equals("")) {
            throw new DukeException(LINE + "\nOh no..did you forget to put what you need to do? :(\n" + LINE);
        } else {
            String task = user_message.substring(5);
            tasks.add(new Todo(task));

            System.out.println(LINE + "\nWokay! I've added this task:\n"
                    + tasks.get(tasksAdded_index).toString()
                    + "\nNow you have " + (tasksAdded_index + 1) + " task(s) in the list\n" + LINE);
            tasksAdded_index++;
        }
    }

    private static void message_deadline(String user_message) throws DukeException {
        //empty body
        if (user_message.substring(8).replaceAll(" ", "").equals("")) {
            throw new DukeException(LINE + "\nOh no..did you forget to put what you need to do?\n" + LINE);
        }
        //no slash
        if (!user_message.contains("/")) {
            throw new DukeException(LINE
                    + "\nWhen is the deadline? Add '/' and the due date after your task to make me record ;)\n"
                    + LINE);
        }

        String[] taskDetails = user_message.substring(9).split("/", 2);
        //deadline is empty
        if (taskDetails[1].replaceAll(" ", "").equals("")) {
            throw new DukeException(LINE
                    + "\nOops! You forgot to add the due date after '/'.. try again? :)\n"
                    + LINE);
        }

        tasks.add(new Deadline(taskDetails[0], taskDetails[1]));
        System.out.println((LINE + "\nWokay! I've added this task:\n"
                + tasks.get(tasksAdded_index).toString()
                + "\nNow you have " + (tasksAdded_index + 1) + " task(s) in the list\n" + LINE));
        tasksAdded_index++;
    }

    private static void message_event(String user_message) throws DukeException {
        //empty body
        if (user_message.substring(5).replaceAll(" ", "").equals("")) {
            throw new DukeException(LINE + "\nOh no..did you forget to put what you need to do?\n" + LINE);
        }
        //no slash
        if (!user_message.contains("/")) {
            throw new DukeException(LINE
                    + "\nWhen is the event happening? Add '/' and the date after the event to make me record ;)\n"
                    + LINE);
        }

        String[] taskDetails = user_message.substring(6).split("/", 2);
        //deadline is empty
        if (taskDetails[1].replaceAll(" ", "").equals("")) {
            throw new DukeException(LINE
                    + "\nOops! You forgot to add the due date after '/'.. try again? :)\n"
                    + LINE);
        }

        tasks.add(new Event(taskDetails[0], taskDetails[1]));
        System.out.println((LINE + "\nWokay! I've added this task:\n"
                + tasks.get(tasksAdded_index).toString()
                + "\nNow you have " + (tasksAdded_index + 1) + " task(s) in the list\n" + LINE));
        tasksAdded_index++;
    }

    private static void message_delete(String user_message) throws DukeException {
        //list is empty
        if (list_isEmpty()) {
            throw new DukeException(LINE
                    + "\nOops... your list is empty! :(\n" + LINE);
        }

        String clean = user_message.replaceAll("\\D+", "");
        //empty message after delete
        if (clean.equals("")) {
            throw new DukeException(LINE
                    + "\nWhich task do you want to delete? Add the number in the end to tell me~\n"
                    + LINE);
        }

        int taskIndex = Integer.parseInt(clean) - 1;
        //user trying to delete a non-existing task
        if (taskIndex >= tasksAdded_index || taskIndex < 0) {
            throw new DukeException(LINE
                    + "\nThere is no number " + (taskIndex + 1) + " in your task list!\n"
                    + LINE);
        }

        System.out.println(LINE + "\nDone! I've removed this task:\n"
                + tasks.get(taskIndex).toString() + "\n" + LINE);
        tasks.remove(taskIndex);
        tasksAdded_index--;
    }


    private static void messageProcess() {
        Scanner user_input = new Scanner(System.in).useDelimiter("\n");
        String user_message = user_input.next();

        while (!user_message.equals("bye")) {
            String[] split = user_message.split(" ");
            try {
                if (user_message.equals("list")) {
                    message_list();

                } else if (user_message.startsWith("mark") && split[0].equals("mark")) {
                    message_mark(user_message);

                } else if (user_message.startsWith("unmark") && split[0].equals("unmark")) {
                    message_unmark(user_message);

                } else if (user_message.startsWith("todo") && split[0].equals("todo")) {
                    message_todo(user_message);

                } else if (user_message.startsWith("deadline") && split[0].equals("deadline")) {
                    message_deadline(user_message);

                } else if (user_message.startsWith("event") && split[0].equals("event")) {
                    message_event(user_message);

                } else if (user_message.startsWith("delete") && split[0].equals("delete")) {
                    message_delete(user_message);

                } else {
                    System.out.println(LINE + "\n" + user_message + "? Sorry, I don't understand what that means.. :(\n" + LINE);
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }

            user_message = user_input.next();
        }
    }


    public static void main(String[] args) {
        String logo = "  *    ,---.    ,-----.  ,--.  ,--.   ,---.      *\n"
                + "      '   .-'  '  .-.  ' |  ,'.|  |  /  O  \\    *\n"
                + " *    `.  `-.  |  | |  | |  |' '  | |  .-.  |\n"
                + "    * .-'    | '  '-'  ' |  | `   | |  | |  |      *\n"
                + "*     `-----'   `-----'  `--'  `--' `--' `--'   *";
        System.out.println(logo);
        System.out.println("\nHi! You can call me Sona ^^~\nHow can I help you?");

        messageProcess();

        System.out.println(LINE + "\nBye-bye :')  Hope to see you soon!\n" + LINE);
    }
}
