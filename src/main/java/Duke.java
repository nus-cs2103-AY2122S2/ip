import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \n" + "What do you need me to note down for you? Type it below! \n" +
                "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the " +
                "task id! ");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(true) {
            String input = sc.nextLine();
            // if there is a command to mark or unmark, this will be the item's id in the list
            int id = Character.getNumericValue(input.charAt(input.length() - 1));

            if (input.equals("bye")) {
                System.out.println("------------------------------------------------------");
                System.out.println("Bye. Hope your memory gets better!!");
                System.out.println("------------------------------------------------------");
                break;
            }
            else if (input.equals("list")) {
                System.out.println("------------------------------------------------------");
                System.out.println("Here are your tasks:");
                int count = 0;
                while (count < list.size()) {
                    int t = count + 1;
                    System.out.println(t + ". " + list.get(count));
                    count++;
                }
                System.out.println("------------------------------------------------------");
            }
            else if (input.contains("unmark")) {
                System.out.println("------------------------------------------------------");
                Task current_task = list.get(id - 1);
                System.out.println(current_task.unmark());
                System.out.println("------------------------------------------------------");
            }
            else if (input.contains("mark")) {
                System.out.println("------------------------------------------------------");
                Task current_task = list.get(id - 1);
                System.out.println(current_task.mark());
                System.out.println("------------------------------------------------------");
            }
            else {
                System.out.println("------------------------------------------------------");
                Task t = new Task(input);
                list.add(t);
                System.out.println("NOTED DOWN: " + input);
                System.out.println("------------------------------------------------------");
            }
        }
    }
}
