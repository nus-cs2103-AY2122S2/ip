import java.util.Scanner;

public class Duke {

    public static String LINE = "*~*~*~*~*~*~*~*~*~*~*~*~*~*~*";
    public static Task[] tasks = new Task[100];
    public static int tasksAdded_index = 0;

    public static boolean list_isEmpty() {
        return tasksAdded_index == 0;
    }

    private static void messageProcess() {
        Scanner user_input = new Scanner(System.in).useDelimiter("\n");
        String user_message = user_input.next();

        while (!user_message.equals("bye")) {
            try {
                if (user_message.equals("list")) {
                    if (list_isEmpty()) {
                        System.out.println(LINE + "\nYour list is empty~\n" + LINE);
                    } else {
                        int i = 0;
                        System.out.println(LINE + "\nHere is your task list!");
                        while(tasks[i] != null) {
                            System.out.println((i + 1) + "." + tasks[i].toString());
                            i++;
                        }
                        System.out.println(LINE);
                    }

                } else if (user_message.startsWith("mark")) {
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
                    if (taskIndex >= tasksAdded_index || taskIndex <= 0) {
                        throw new DukeException(LINE
                                + "\nThere is no number " + (taskIndex + 1) + " in your task list!\n"
                                + LINE);
                    }

                    tasks[taskIndex].markAsDone();
                    System.out.println("Yay! I've marked this task as done:\n"
                            + tasks[taskIndex].toString());

                } else if (user_message.startsWith("unmark")) {
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
                    if (taskIndex >= tasksAdded_index || taskIndex <= 0) {
                        throw new DukeException(LINE
                                + "\nThere is no number " + (taskIndex + 1) + " in your task list!\n"
                                + LINE);
                    }

                    tasks[taskIndex].unmark();
                    System.out.println("Aw man..I've marked this task as not done yet:\n"
                            + tasks[taskIndex].toString());

                } else if (user_message.startsWith("todo")) {
                    if (user_message.substring(4).replaceAll(" ", "").equals("")) {
                        throw new DukeException(LINE + "\nOh no..did you forget to put what you need to do? :(\n" + LINE);
                    } else {
                        String task = user_message.substring(5);
                        tasks[tasksAdded_index] = new Todo(task);

                        System.out.println(LINE + "\nWokay! I've added this task:\n"
                                + tasks[tasksAdded_index].toString()
                                + "\nNow you have " + (tasksAdded_index + 1) + " tasks in the list\n" + LINE);
                        tasksAdded_index++;
                    }

                } else if (user_message.startsWith("deadline")) {
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
                    tasks[tasksAdded_index] = new Deadline(taskDetails[0], taskDetails[1]);

                    //deadline is empty
                    if (taskDetails[1].replaceAll(" ", "").equals("")) {
                        throw new DukeException(LINE
                                + "\nOops! You forgot to add the due date after '/'.. try again? :)\n"
                                + LINE);
                    }

                    System.out.println((LINE + "\nWokay! I've added this task:\n"
                            + tasks[tasksAdded_index].toString()
                            + "\nNow you have " + (tasksAdded_index + 1) + " tasks in the list\n" + LINE));
                    tasksAdded_index++;

                } else if (user_message.startsWith("event")) {
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
                    tasks[tasksAdded_index] = new Event(taskDetails[0], taskDetails[1]);

                    //deadline is empty
                    if (taskDetails[1].replaceAll(" ", "").equals("")) {
                        throw new DukeException(LINE
                                + "\nOops! You forgot to add the due date after '/'.. try again? :)\n"
                                + LINE);
                    }

                    System.out.println((LINE + "\nWokay! I've added this task:\n"
                            + tasks[tasksAdded_index].toString()
                            + "\nNow you have " + (tasksAdded_index + 1) + " tasks in the list\n" + LINE));
                    tasksAdded_index++;

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
                + " *    `.  `-.  |  | |  | |  |' '  | |  .-.  |   \n"
                + "    * .-'    | '  '-'  ' |  | `   | |  | |  |      *\n"
                + "*     `-----'   `-----'  `--'  `--' `--' `--'   *";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHi! You can call me Sona ^^~\nHow can I help you?\n");

        messageProcess();


        System.out.println(LINE + "\nBye-bye :')  Hope to see you soon! \n" + LINE);
    }
}
