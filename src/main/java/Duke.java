import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        ArrayList<Task> itemList = new ArrayList<>();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String reply = sc.nextLine();
            if (reply.split(" ")[0].equals("mark") && reply.split(" ").length == 2) {
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       [X] " + itemList.get(index - 1).description + "\n" +
                        "    ____________________________________________________________\n");
        } else if (reply.split(" ")[0].equals("unmark") && reply.split(" ").length == 2) {
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsUndone();
                System.out.println("    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet: \n" +
                        "       [ ] " + itemList.get(index - 1).description + "\n" +
                        "    ____________________________________________________________\n");
            } else if (reply.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                break;
            } else if (reply.equals("list")) {
                String totalString = "    ____________________________________________________________\n" +
                        "    Here are the tasks in your list:\n";
                for(int i = 0; i < itemList.size(); i++) {
                    totalString += "    " + (i + 1) + ". " + "[" + itemList.get(i).getStatusIcon() + "] " + itemList.get(i).description + "\n";
                }
                totalString += "    ____________________________________________________________\n";
                System.out.println(totalString);
            } else {
                itemList.add(new Task(reply));
                System.out.println("    ____________________________________________________________\n" +
                        "     " + "added: " + reply + "\n" +
                        "    ____________________________________________________________");
            }
        }
    }
}
