import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    public static void main(String[] args) {
        String name = "Enkel";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String frame = "_____________________________________________";
        System.out.println(frame + "\nHello! I\'m " + name + "\nWhat can I do for you?\n" + frame + "\n");

        Scanner sc = new Scanner(System.in);
        String command;
        List<Task> tasks = new ArrayList<Task>();
        Pattern p_mark = Pattern.compile(" *(un)?mark +\\d*([1-9]+\\d*) *", Pattern.CASE_INSENSITIVE);
        while (true) {
            command = sc.nextLine();
            String[] type_desc = command.split(" ", 2);
            String taskType = type_desc[0];
            String desc = "";
            if (type_desc.length == 2) {
                desc = type_desc[1];
            }
            Matcher m_mark = p_mark.matcher(command);
            if (command.equals("bye")) {
                System.out.println(frame + "\nBye. Hope to see you again soon!\n" + frame + "\n");
                break;
            } else if (command.equals("list")) {
                System.out.println(frame);
                if (tasks.size() == 0) {
                    System.out.println("There are no tasks in your list~");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
                System.out.println(frame + "\n");
            } else if (m_mark.matches()) {
                int i = Integer.parseInt(m_mark.group(2));
                if (m_mark.group(1) != null) {
                    if (i > 0 && i <= tasks.size()) {
                        tasks.get(i - 1).markNotDone();
                        System.out.println(frame + "\nOK, I've marked this task as not done yet:\n"
                                + tasks.get(i - 1) + "\n" + frame + "\n");
                    } else {
                        System.out.println(frame + "\nIndex is invalid\n" + frame + "\n");
                    }
                } else {
                    if (i > 0 && i <= tasks.size()) {
                        tasks.get(i - 1).markAsDone();
                        System.out.println(frame + "\nNice! I've marked this task as done:\n"
                                + tasks.get(i - 1) + "\n" + frame + "\n");
                    } else {
                        System.out.println("Index is invalid");
                    }
                }
            } else if (desc.equals("")) {
                System.out.println(frame + "\nNo description entered\n" + frame + "\n");
            } else if (taskType.equals("todo")) {
                Task t = new Todo(desc);
                tasks.add(t);
                System.out.println(frame + "\nGot it. I've added this task:\n" + t
                        + "\nNow you have " + tasks.size() + " tasks in your list.\n"
                        + frame + "\n");
            } else if (taskType.equals("deadline")) {
                String[] desc_by = desc.split(" /by ", 2);
                String by = "";
                if (desc_by.length == 2) {
                    by = desc_by[1];
                }
                Task t = new Deadline(desc_by[0], by);
                tasks.add(t);
                System.out.println(frame + "\nGot it. I've added this task:\n" + t
                        + "\nNow you have " + tasks.size() + " tasks in your list.\n"
                        + frame + "\n");
            } else if (taskType.equals("event")) {
                String[] desc_at = desc.split(" /at ", 2);
                String at = "";
                if (desc_at.length == 2) {
                    at = desc_at[1];
                }
                Task t = new Event(desc_at[0], at);
                tasks.add(t);
                System.out.println(frame + "\nGot it. I've added this task:\n" + t
                        + "\nNow you have " + tasks.size() + " tasks in your list.\n"
                        + frame + "\n");
            }
        }
    }

}
