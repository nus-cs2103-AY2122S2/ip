
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {



        String logo =         "\n" +
                "       _       _        \n" +
                "      | |     | |       \n" +
                "      | |_   _| | _____ \n" +
                "  _   | | | | | |/ / _ \\\n" +
                " | |__| | |_| |   <  __/\n" +
                "  \\____/ \\__,_|_|\\_\\___|\n" +
                "                        \n" +
                "                        \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Juke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        ArrayList<Task> itemList = new ArrayList<>();
        while(true) {


            Scanner sc = new Scanner(System.in);
            String reply = sc.nextLine();
            String[] splittedString = reply.split(" ");

            File file1 = new File("data/duke.txt");
            file1.delete();

            File file = new File("data/duke.txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (splittedString[0].equals("mark") && splittedString.length == 2) { // check for mark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + itemList.get(index - 1).getDescription() + "\n" +
                        "    ____________________________________________________________\n");


            } else if (splittedString[0].equals("unmark") && splittedString.length == 2) { // check for unmark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsUndone();
                System.out.println("    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet: \n" +
                        "       " + itemList.get(index - 1).getDescription() + "\n" +
                        "    ____________________________________________________________\n");

            } else if (splittedString[0].equals("todo")) { // check for todo tag
                if (splittedString.length == 1) { //invalid todo command
                    System.out.println("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "    ____________________________________________________________");
                } else { //valid todo command
                    Task todoTask = new Todo(reply);
                    itemList.add(todoTask);
                    System.out.println("    ____________________________________________________________\n" +
                            "     Got it. I've added this task: \n" +
                            "       " + todoTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            "    ____________________________________________________________");
                }
            } else if (splittedString[0].equals("deadline")) { // check for deadline tag
                if (splittedString.length == 1) { //invalid deadline command
                    System.out.println("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                            "    ____________________________________________________________");
                } else { //valid deadline command
                    Task deadlineTask = new Deadline(reply);
                    itemList.add(deadlineTask);
                    System.out.println("    ____________________________________________________________\n" +
                            "     Got it. I've added this task: \n" +
                            "       " + deadlineTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            "    ____________________________________________________________");
                }
            } else if (splittedString[0].equals("event")) { // check for event tag
                if (splittedString.length == 1) { //invalid event command
                    System.out.println("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a event cannot be empty.\n" +
                            "    ____________________________________________________________");
                } else { //valid event command
                    Task eventTask = new Event(reply);
                    itemList.add(eventTask);
                    System.out.println("    ____________________________________________________________\n" +
                            "     Got it. I've added this task: \n" +
                            "       " + eventTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            "    ____________________________________________________________");
                }
            } else if (reply.equals("bye")) { // check for bye
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                break;
            } else if (reply.equals("list")) { // check for list
                String totalString = "    ____________________________________________________________\n" +
                        "    Here are the tasks in your list:\n";
                for (int i = 0; i < itemList.size(); i++) {
                    totalString += "    " + (i + 1) + ". " + itemList.get(i).getDescription() + "\n";
                }
                totalString += "    ____________________________________________________________\n";
                System.out.println(totalString);
            } else if (splittedString[0].equals("delete")) { //check for delete
                try {
                    int index = Integer.valueOf(splittedString[1]);

                    String toRemove = itemList.remove(index - 1).getDescription();
                    System.out.println("    ____________________________________________________________\n" +
                            "     Noted. I've removed this task: \n" +
                            "       " + toRemove + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            "    ____________________________________________________________");
                } catch (NumberFormatException n) {
                    System.out.println("Invalid input, please enter a valid index number instead");
                }
            } else { //check non-existing commands
                System.out.println("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________");
            }

            String file2 = "data/duke.txt";
            try {
                for (Task t : itemList) {
                    writeToFile(file2, t.getDescription() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

    }
}
