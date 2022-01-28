import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public Ui() {

    }

    public String[] readCommand() {
        String command = sc.next();
        String details = sc.nextLine().trim();
        return new String[]{command, details};
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What do you need me to note down for you? Type it below!\n" +
                "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the " +
                "task number!");
        printDoubleLine();
    }

    public void goodbye() {
        printSingleLine();
        System.out.println("Bye. Have a great day!");
        printDoubleLine();
    }

    public void printSingleLine() {
        System.out.println("------------------------------------------------------");
    }

    public void printDoubleLine() {
        System.out.println("======================================================");
    }

    public void printTasks(ArrayList<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            taskList.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        System.out.println(taskList.toString().trim());
        printDoubleLine();
    }

    public void notifyRemovedTaskMessage(Task t) {
        System.out.println("Noted. I've removed this task:\n" + t);
        printDoubleLine();
    }

    public void notifyAddedTaskMessage(Task t) {
        System.out.println("Noted. I've added this task:\n" + t);
        printDoubleLine();
    }

    public void notifyMarkedTaskMessage(Task t, boolean mark) {
        if (mark) {
            System.out.println("Task " + t + " has been marked complete.");
        } else {
            System.out.println("Task " + t + " has been marked incomplete.");
        }
        printDoubleLine();
    }

}

//    private void take_notes() throws DukeException {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//
//            String command = sc.next();
//            String details = sc.nextLine().trim();
//            // if there is a command to mark or unmark, this will be the item's id in the list
//            switch (command) {
//                case "bye":
//                    System.out.println("------------------------------------------------------");
//                    System.out.println("Bye. Have a great day!");
//                    System.out.println("======================================================");
//                    break;
//                case "list":
//                    System.out.println("------------------------------------------------------");
//                    System.out.println("Here are your tasks:");
//                    for (int i = 1; i <= this.list.size(); i++) {
//                        System.out.println(i + ". " + this.list.get(i - 1));
//                    }
//                    System.out.println("======================================================");
//                    break;
//                case "unmark":
//                    try {
//                        System.out.println("------------------------------------------------------");
//                        Task unmark_task = this.list.get(Integer.parseInt(details) - 1);
//                        System.out.println(unmark_task.unmark());
//                        save_to_file();
//                        System.out.println("======================================================");
//                    } catch (IndexOutOfBoundsException e) {
//                        throw new DukeException("No such task exists! Are you sure about that task number?");
//                    }
//                    break;
//                case "mark":
//                    try {
//                        System.out.println("------------------------------------------------------");
//                        Task mark_test = this.list.get(Integer.parseInt(details) - 1);
//                        System.out.println(mark_test.mark());
//                        save_to_file();
//                        System.out.println("======================================================");
//                    } catch (IndexOutOfBoundsException e) {
//                        throw new DukeException("No such task exists! Are you sure about that task number?");
//                    }
//                    break;
//                case "deadline":
//                    System.out.println("------------------------------------------------------");
//                    Task.is_valid_task(details, "deadline");
//                    String[] d_deets = details.split("/");
//                    String dd = d_deets[1].trim().substring(3);
//                    System.out.println(dd);
//                    try {
//                        LocalDate.parse(dd);
//                    } catch (DateTimeParseException e) {
//                        System.out.println("Please enter a date with the format yyyy-mm-dd");
//                    }
//                    Deadline deadline = new Deadline(d_deets[0].trim(), LocalDate.parse(dd));
//                    add_task(deadline, true);
//                    save_to_file();
//                    break;
//                case "event":
//                    System.out.println("------------------------------------------------------");
//                    Task.is_valid_task(details, "event");
//                    String[] e_deets = details.split("/");
//                    String de = e_deets[1].trim().substring(3);
//                    try {
//                        LocalDate.parse(de);
//                    } catch (DateTimeParseException e) {
//                        System.out.println("Please enter a date with the format yyyy-mm-dd");
//                    }
//                    Event event = new Event(e_deets[0].trim(), LocalDate.parse(de));
//                    add_task(event, true);
//                    save_to_file();
//                    break;
//                case "todo":
//                    System.out.println("------------------------------------------------------");
//                    Task.is_valid_task(details, "todo");
//                    ToDo td = new ToDo(details);
//                    add_task(td, true);
//                    save_to_file();
//                    break;
//                case "delete":
//                    try {
//                        System.out.println("------------------------------------------------------");
//                        delete_task(Integer.parseInt(details) - 1);
//                        System.out.println();
//                        save_to_file();
//                        System.out.println("======================================================");
//                    } catch (IndexOutOfBoundsException e) {
//                        throw new DukeException("No such task exists! Are you sure about that task number?");
//                    }
//                    break;
//                default:
//                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
//        }
//    }
//
//    private void output(Task t) {
//        System.out.println(
//                "The following task has been added: \n" + t + "\n" +
//                        "Now you have " + TaskMaster.count + " tasks in the list \n" +
//                        "======================================================");
//    }

