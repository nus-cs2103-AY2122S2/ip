import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();
    private int count = 0;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        try {
            duke.take_notes();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public Duke() {

    }

    private void greet() {
        System.out.println("Hello! I'm Duke\n" + "What do you need me to note down for you? Type it below!\n" +
                "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the " +
                "task number!\n ======================================================");
    }

    private void take_notes() throws DukeException {
        Scanner sc = new Scanner(System.in);
        while(true) {

                String command = sc.next();
                String details = sc.nextLine().trim();
                // if there is a command to mark or unmark, this will be the item's id in the list
                switch (command) {
                    case "bye":
                        System.out.println("------------------------------------------------------");
                        System.out.println("Bye. Have a great day!");
                        System.out.println("======================================================");
                        break;
                    case "list":
                        System.out.println("------------------------------------------------------");
                        System.out.println("Here are your tasks:");
                        for (int i = 1; i <= this.list.size(); i++) {
                            System.out.println(i + ". " + this.list.get(i-1));
                        }
                        System.out.println("======================================================");
                        break;
                    case "unmark":
                        try {
                            System.out.println("------------------------------------------------------");
                            Task unmark_task = this.list.get(Integer.parseInt(details) - 1);
                            System.out.println(unmark_task.unmark());
                            System.out.println("======================================================");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("No such task exists! Are you sure about that task number?");
                        }
                        break;
                    case "mark":
                        try {
                            System.out.println("------------------------------------------------------");
                            Task mark_test = this.list.get(Integer.parseInt(details) - 1);
                            System.out.println(mark_test.mark());
                            System.out.println("======================================================");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("No such task exists! Are you sure about that task number?");
                        }
                        break;
                    case "deadline":
                        System.out.println("------------------------------------------------------");
                        is_valid_task(details, "deadline");
                        String[] d_deets = details.split("/");
                        String dd = d_deets[1].trim().substring(3);
                        System.out.println(dd);
                        try {
                            LocalDate.parse(dd);
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter a date with the format yyyy-mm-dd");
                        }
                        Deadline deadline = new Deadline(d_deets[0].trim(), LocalDate.parse(dd));
                        add_task(deadline);
                        break;
                    case "event":
                        System.out.println("------------------------------------------------------");
                        is_valid_task(details, "event");
                        String[] e_deets = details.split("/");
                        String de = e_deets[1].trim().substring(3);
                        try {
                            LocalDate.parse(de);
                        } catch (DateTimeParseException e) {
                            System.out.println("Please enter a date with the format yyyy-mm-dd");
                        }
                        Event event = new Event(e_deets[0].trim(), LocalDate.parse(de));
                        add_task(event);
                        break;
                    case "todo":
                        System.out.println("------------------------------------------------------");
                        is_valid_task(details, "todo");
                        ToDo td = new ToDo(details);
                        add_task(td);
                        break;
                    case "delete":
                        try {
                            System.out.println("------------------------------------------------------");
                            delete_task(Integer.parseInt(details) - 1);
                            System.out.println();
                            System.out.println("======================================================");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("No such task exists! Are you sure about that task number?");
                        }
                        break;
                    default:
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private void delete_task(int i) {
        Task removed_task = this.list.remove(i);
        System.out.println("Noted. I've removed this task:\n" + removed_task +
                "\nnow you have " + this.list.size() + " tasks in the list.");
    }

    private boolean is_valid_task(String details, String type) throws DukeException{
        if (details.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        } else if ((type.equals("deadline") || type.equals("event")) & !details.contains("/")) {
            throw new DukeException("☹ OOPS!!! The date of a " + type + " cannot be empty. Use / and type the date after it");
        } else if ((type.equals("deadline") || type.equals("event")) & details.endsWith("/")) {
            throw new DukeException("☹ OOPS!!! The date of a " + type + " cannot be empty. Type the date after your /");
        } else {
            return true;
        }
    }

    private boolean is_valid_date(String date) {
        return true;
    }

    private void output(Task t) {
        System.out.println(
                "The following task has been added: \n" + t + "\n" +
                "Now you have " + this.count + " tasks in the list \n" +
                "======================================================");
    }

    private void add_task (Task t) {
        this.list.add(t);
        this.count++;
        output(t);
    }

}
