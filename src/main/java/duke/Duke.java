package duke;

import java.util.ArrayList;
import java.util.List;
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

        Outputs op = new Outputs();



        System.out.println(op.greeting + op.logo);
        System.out.println(op.border +
                op.firstPrompt +
                op.instructions + op.border);
        ArrayList<Task> itemList = new ArrayList<>();
        while(true) {


            Scanner sc = new Scanner(System.in);
            String reply = sc.nextLine();
            String[] splittedString = reply.split(" ");

            File file = new File("data/duke.txt");
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(" ");
            }

            if (splittedString[0].equals("mark") && splittedString.length == 2) { // check for mark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsDone();
                System.out.println(op.border +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + itemList.get(index - 1).getDescription() + "\n" +
                        op.instructions +
                        op.border);


            } else if (splittedString[0].equals("unmark") && splittedString.length == 2) { // check for unmark tag
                int index = Integer.valueOf(reply.split(" ")[1]);
                itemList.get(index - 1).markAsUndone();
                System.out.println(op.border +
                        "     OK, I've marked this task as not done yet: \n" +
                        "       " + itemList.get(index - 1).getDescription() + "\n" +
                        op.instructions +
                        op.border);

            } else if (splittedString[0].equals("todo")) { // check for todo tag
                if (splittedString.length == 1) { //invalid todo command
                    System.out.println(op.border +
                            "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            op.instructions +
                            op.border);
                } else { //valid todo command
                    Task todoTask = new Todo(reply);
                    itemList.add(todoTask);
                    System.out.println(op.border +
                            "     Got it. I've added this task: \n" +
                            "       " + todoTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            op.instructions +
                            op.border);
                }
            } else if (splittedString[0].equals("deadline")) { // check for deadline tag
                if (splittedString.length == 1) { //invalid deadline command
                    System.out.println(op.border +
                            "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                            op.instructions +
                            op.border);
                } else { //valid deadline command
                    Task deadlineTask = new Deadline(reply);
                    itemList.add(deadlineTask);
                    System.out.println(op.border +
                            "     Got it. I've added this task: \n" +
                            "       " + deadlineTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            op.instructions +
                            op.border);
                }
            } else if (splittedString[0].equals("event")) { // check for event tag
                if (splittedString.length == 1) { //invalid event command
                    System.out.println(op.border +
                            "     ☹ OOPS!!! The description of a event cannot be empty.\n" +
                            op.instructions +
                            op.border);
                } else { //valid event command
                    Task eventTask = new Event(reply);
                    itemList.add(eventTask);
                    System.out.println(op.border +
                            "     Got it. I've added this task: \n" +
                            "       " + eventTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            op.instructions +
                            op.border);
                }
            } else if (reply.equals("bye")) { // check for bye
                System.out.println(op.border +
                        "     Bye. Hope to see you again soon!\n" +
                        op.border);
                break;
            } else if (reply.equals("list")) { // check for list
                String totalString = op.border +
                        "    Here are the tasks in your list:\n";
                for (int i = 0; i < itemList.size(); i++) {
                    totalString += "    " + (i + 1) + ". " + itemList.get(i).getDescription() + "\n";
                }
                totalString += op.instructions +
                        op.border;
                System.out.println(totalString);
            } else if (splittedString[0].equals("delete")) { //check for delete
                try {
                    int index = Integer.valueOf(splittedString[1]);

                    String toRemove = itemList.remove(index - 1).getDescription();
                    System.out.println(op.border +
                            "     Noted. I've removed this task: \n" +
                            "       " + toRemove + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" + op.instructions +
                            op.border);
                } catch (NumberFormatException n) {
                    System.out.println("Invalid input, please enter a valid index number instead");
                }
            } else if (splittedString[0].equals("find")) {
                    String keyword = splittedString[1];
                    List<Task> filteredItemList = new ArrayList<>();
                    for (Task t : itemList) {
                        filteredItemList.add(t);
                    }
                    filteredItemList.removeIf(s -> !s.getDescription().contains(keyword));
                    String relevantTasks = "";
                    int count = 1;
                    for (Task t : filteredItemList) {
                        relevantTasks += "     " + count + "." + t.getDescription() + "\n";
                        count++;
                    }
                    System.out.println(op.border
                                       + "     Here are the matching tasks in your list:\n" + relevantTasks
                                       + op.instructions + op.border);
            } else { //check non-existing commands
                System.out.println(op.border +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        op.instructions +
                        op.border);
            }

            String file2 = "data/duke.txt";
            try {
                for (Task t : itemList) {
                    writeToFile(file2, t.getDescription() + "\n");
                }
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }
}
