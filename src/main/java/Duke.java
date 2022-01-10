import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String splitter = "______________________________________";
        String indentationBase = "\t";
        String indentationText = "\t  ";
        String indentationTaskStatus = "\t    ";
        ArrayList<Task> list = new ArrayList<>();
        boolean firstUserChat = true;

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println(String.format("%sHow should I address you?", indentationText));
        String userName = sc.nextLine();
        System.out.println(String.format("%sSplendid! My pleasure to serve you %s ", indentationText, userName));

        while(true) {
            try {
                System.out.println(String.format("%sWhat %smay I assist you with today, %s? \n\t%s", indentationText, (firstUserChat ? "" : "else "), userName, splitter));
                firstUserChat = (firstUserChat==true) ? false : firstUserChat;

                String userInput = sc.nextLine();

                // Start of Duke's text block 
                System.out.println(String.format("%s%s", indentationBase, splitter));

                // Exit - "bye", exits the program 
                if (userInput.toLowerCase().equals("bye")) {
                    System.out.println(String.format("%sGoodbye for now. \n", indentationText));
                    break;
                }

                // List - "list", lists all of the user's inputs 
                else if (userInput.toLowerCase().equals("list")) {
                    if (list.size() == 0) System.out.println(String.format("\tYour list is empty %s", userName));
                    else {
                        for (int i = 0; i < list.size(); i++) {
                            Task task = list.get(i);
                            System.out.println(String.format("%s%d. [%s] %s", indentationText, i+1, task.getStatusIcon(), task));
                        }
                    }
                }

                // Mark, Unmark - "mark itemIndexNumber", "unmark itemIndexNumber", marks an item as done/undone accordingly 
                else if (userInput.toLowerCase().contains("mark") || userInput.toLowerCase().contains("unmark")) {
                    String[] stringArray = userInput.toLowerCase().split(" ");
                    int taskIndex = Integer.valueOf(stringArray[1]) - 1;

                    // Mark 
                    if (stringArray[0].equals("mark")) {
                        System.out.println(String.format("%sOkay, marking this task as done:", indentationText));
                        list.get(taskIndex).markAsDone();
                    
                    // Unmark 
                    } else if (stringArray[0].equals("unmark")) {
                        System.out.println(String.format("%sOkay, marking this task as not done yet:", indentationText));
                        list.get(taskIndex).markAsUndone();
                    }

                    // Print out Task 
                    Task task = list.get(taskIndex);
                    System.out.println(String.format("%s[%s] %s", indentationTaskStatus, task.getStatusIcon(), task));

                }
                
                // Add - adds user input to a list 
                else {
                    Task task = new Task(userInput);
                    list.add(task);
                    System.out.println(String.format("%sTask added: %s", indentationText, userInput));
                }

            }
            // TODO - catching all Exceptions
            catch(Exception e) {
                System.out.println("\tException: " + e.getMessage());
            }
        }

        sc.close();
    }
}
