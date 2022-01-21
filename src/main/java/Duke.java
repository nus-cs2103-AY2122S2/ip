import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private static void message_deadline(String user_message) throws DukeException, DateTimeParseException {
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

        //processing date & time
        String dateTime = taskDetails[1].replaceAll(" ", "");
        LocalDateTime dateTimeComplete;
        boolean timeEntered = false;

        if (dateTime.length() == 14) { //date & time
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            int hour = Integer.parseInt(dateTime.substring(10, 12));
            int min = Integer.parseInt(dateTime.substring(12));
            dateTimeComplete = date.atTime(hour, min);
            timeEntered = true;

        } else if (dateTime.length() == 10) { //date only
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            dateTimeComplete = date.atTime(23, 59);

        //date & time invalid
        } else {
            throw new DukeException(LINE
                    + "\nOh no... the format or the date/time you entered is wrong! The correct format should be YYYY-MM-DD HHMM\n"
                    + "It is not necessary to put in time!\n"
                    + LINE);
        }

        tasks.add(new Deadline(taskDetails[0], dateTimeComplete));

        if (timeEntered) {
            System.out.println((LINE + "\nWokay! I've added this task:\n"
                    + tasks.get(tasksAdded_index).toString()
                    + "\nNow you have " + (tasksAdded_index + 1) + " task(s) in the list\n" + LINE));
        } else {
            System.out.println((LINE + "\nSince you did not enter the due time for this task, I will help you set it at 23:59!\n"
                    + tasks.get(tasksAdded_index).toString()
                    + "\nNow you have " + (tasksAdded_index + 1) + " task(s) in the list\n" + LINE));
        }

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

        //processing date & time
        String dateTime = taskDetails[1].replaceAll(" ", "");
        LocalDateTime dateTimeComplete;
        boolean timeEntered = false;

        if (dateTime.length() == 14) { //date & time
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            int hour = Integer.parseInt(dateTime.substring(10, 12));
            int min = Integer.parseInt(dateTime.substring(12));
            dateTimeComplete = date.atTime(hour, min);
            timeEntered = true;

        } else if (dateTime.length() == 10) { //date only
            LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
            dateTimeComplete = date.atTime(0, 0);

            //date & time invalid
        } else {
            throw new DukeException(LINE
                    + "\nOh no... the format or the date/time you entered is wrong! The correct format should be YYYY-MM-DD HHMM\n"
                    + "It is not necessary to put in time!\n"
                    + LINE);
        }

        tasks.add(new Event(taskDetails[0], dateTimeComplete));

        if (timeEntered) {
            System.out.println((LINE + "\nWokay! I've added this task:\n"
                    + tasks.get(tasksAdded_index).toString()
                    + "\nNow you have " + (tasksAdded_index + 1) + " task(s) in the list\n" + LINE));
        } else {
            System.out.println((LINE + "\nSince you did not enter what time is this event happening, I will help you set it at 00:00!\n"
                    + tasks.get(tasksAdded_index).toString()
                    + "\nNow you have " + (tasksAdded_index + 1) + " task(s) in the list\n" + LINE));
        }

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

    private static void message_schedule(String user_message) throws DukeException {
        //list is empty
        if (list_isEmpty()) {
            throw new DukeException(LINE
                    + "\nThere is nothing happening on this day! Hooray~\n" + LINE);
        }

        String dateString = user_message.substring(8).replaceAll(" ", "");
        //no date given
        if (dateString.equals("")) {
            throw new DukeException(LINE
                    + "\nWhich day would you like to check? Input format: schedule YYYY-MM-DD\n" + LINE);
        }

        //date is invalid
        LocalDate date = LocalDate.parse(dateString);
        ArrayList<Task> scheduleList = new ArrayList<>(100);

        for (int i = 0; i < tasksAdded_index; i++) {
            if (tasks.get(i) instanceof Event) {
                Event task = (Event) tasks.get(i);
                if (date.isEqual(task.getAt().toLocalDate())) {
                    scheduleList.add(task);
                }
            }
            if (tasks.get(i) instanceof Deadline) {
                Deadline task = (Deadline) tasks.get(i);
                if (date.isEqual((task.getBy().toLocalDate()))) {
                    scheduleList.add(task);
                }
            }
        }

        if (scheduleList.isEmpty()) {
            System.out.println(LINE
                    +  "\nThere is nothing happening on this day! Hooray~\n" + LINE);
        } else {
            System.out.println(LINE
                    + "\nThese are the events/deadlines happening on " + date.toString() + ":");
            for (int i = 0; i < scheduleList.size(); i++) {
                System.out.println((i + 1) + "." + scheduleList.get(i).toString());
            }
            System.out.println(LINE);
        }

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

                }  else if (user_message.startsWith("schedule") && split[0].equals("schedule")) {
                    message_schedule(user_message);
                }


                else {
                    System.out.println(LINE + "\n" + user_message + "? Sorry, I don't understand what that means.. :(\n" + LINE);
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            } catch (DateTimeParseException err) {
                System.out.println(LINE
                        + "\nOh no... the format or the date/time you entered is wrong! The correct format should be YYYY-MM-DD HHMM\n"
                        + "It is not necessary to put in time!\n"
                        + LINE);
            } catch (DateTimeException err) {
                System.out.println(LINE
                        + "\nOops... did you put in the wrong date or time? :(\n"
                        + LINE);
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
