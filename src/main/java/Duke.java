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
            String[] splittedString = reply.split(" ");
            if (splittedString[0].equals("mark") && splittedString.length == 2) { // check for mark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       [X] " + itemList.get(index - 1).description + "\n" +
                        "    ____________________________________________________________\n");


            } else if (splittedString[0].equals("unmark") && splittedString.length == 2) { // check for unmark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsUndone();
                System.out.println("    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet: \n" +
                        "       [ ] " + itemList.get(index - 1).description + "\n" +
                        "    ____________________________________________________________\n");

            } else if (splittedString[0].equals("todo")) { // check for todo tag
                String newReply = reply.replace("todo ", "");
                itemList.add(new Task(newReply, "T"));
                System.out.println("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       [T][ ] " + newReply + "\n" +
                        "     Now you have " + itemList.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________");
            } else if (splittedString[0].equals("deadline")) { // check for deadline tag
                String newReply = reply.replace("deadline ", "");
                String taskAtHand = newReply.split("/")[0];
                String deadline = newReply.split("/")[1].replace("by ", "by: ");
                String finalDescription = taskAtHand + "(" + deadline + ")";
                itemList.add(new Task(finalDescription, "D"));
                System.out.println("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       [D][ ] " + finalDescription + "\n" +
                        "     Now you have " + itemList.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________");
            } else if (splittedString[0].equals("event")) { // check for event tag
                String newReply = reply.replace("event ", "");
                String taskAtHand = newReply.split("/")[0];
                String event = newReply.split("/")[1].replace("at ", "at: ");
                String finalDescription = taskAtHand + "(" + event + ")";
                itemList.add(new Task(finalDescription, "E"));
                System.out.println("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       [E][ ] " + finalDescription + "\n" +
                        "     Now you have " + itemList.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________");
            } else if (reply.equals("bye")) { // check for bye
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                break;
            } else if (reply.equals("list")) { // check for list
                String totalString = "    ____________________________________________________________\n" +
                        "    Here are the tasks in your list:\n";
                for(int i = 0; i < itemList.size(); i++) {
//                    if (itemList.get(i).getTaskType().equals("D")) { //if deadline
//                        totalString += "    " + (i + 1) + ". " + "[" + itemList.get(i).getTaskType()
//                                + "]" + "[" + itemList.get(i).getStatusIcon() + "] " + itemList.get(i).description + "\n";
//                    } else if (itemList.get(i).getTaskType().equals("E")) { //if event
//
//                    } else {
                        totalString += "    " + (i + 1) + ". " + "[" + itemList.get(i).getTaskType()
                                + "]" + "[" + itemList.get(i).getStatusIcon() + "] " + itemList.get(i).description + "\n";

                }
                totalString += "    ____________________________________________________________\n";
                System.out.println(totalString);
            } else { // check for any other inputs
//                itemList.add(new Task(reply));
//                System.out.println("    ____________________________________________________________\n" +
//                        "     " + "added: " + reply + "\n" +
//                        "    ____________________________________________________________");
            }
        }
    }
}
