import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();
    private int count = 0;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.take_notes();
    }

    public Duke() {

    }

    private void greet() {
        System.out.println("Hello! I'm Duke\n" + "What do you need me to note down for you? Type it below!\n" +
                "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the " +
                "task number!\n ======================================================");
    }

    private void take_notes() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            // if there is a command to mark or unmark, this will be the item's id in the list
            int id = Character.getNumericValue(input.charAt(input.length() - 1));
            if (input.equals("bye")) {
                System.out.println("------------------------------------------------------");
                System.out.println("    Bye. Have a great day!");
                System.out.println("======================================================");
                break;
            }
            else if (input.equals("list")) {
                System.out.println("------------------------------------------------------");
                System.out.println("    Here are your tasks:");
                int count = 0;
                while (count < list.size()) {
                    int t = count + 1;
                    System.out.println("    " + t + ". " + list.get(count));
                    count++;
                }
                System.out.println("======================================================");
            }
            else if (input.contains("unmark")) {
                System.out.println("------------------------------------------------------");
                Task current_task = list.get(id - 1);
                System.out.println(current_task.unmark());
                System.out.println("======================================================");
            }
            else if (input.contains("mark")) {
                System.out.println("------------------------------------------------------");
                Task current_task = list.get(id - 1);
                System.out.println(current_task.mark());
                System.out.println("======================================================");
            }
            else if (input.contains("deadline")) {
                System.out.println("------------------------------------------------------");
                String [] temp = input.split("/");
                Deadline t = new Deadline(temp[0].substring(9).trim(), temp[1].substring(3));
                add_task(t);
            }
            else if (input.contains("event")){
                System.out.println("------------------------------------------------------");
                String [] temp = input.split("/");
                Event t = new Event(temp[0].substring(6).trim(), temp[1].substring(3));
                add_task(t);
            }
            else {
                System.out.println("------------------------------------------------------");
                ToDo t = new ToDo(input.substring(5).trim());
                add_task(t);
            }
        }
    }

    private void output(Task t) {
        System.out.println(
                "   The following task has been added: \n" +
                "     " + t + "\n" +
                "   Now you have " + this.count + " tasks in the list \n" +
                "======================================================");
    }

    private void add_task (Task t) {
        this.list.add(t);
        this.count++;
        output(t);
    }

}
