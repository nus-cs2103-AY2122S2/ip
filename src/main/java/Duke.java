import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String splitter = "______________________________________";
        String indentationBase = "\t";
        String indentationText = "\t  ";
        String indentationTaskStatus = "\t    ";
        ArrayList<Task> taskList = new ArrayList<>();
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
                    if (taskList.size() == 0) System.out.println(String.format("\tYour list is empty %s", userName));
                    else {
                        for (int i = 0; i < taskList.size(); i++) {
                            Task task = taskList.get(i);
                            System.out.println(String.format("%s%d. %s", indentationText, i+1, task)); 
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
                        taskList.get(taskIndex).markAsDone();
                    
                    // Unmark 
                    } else if (stringArray[0].equals("unmark")) {
                        System.out.println(String.format("%sOkay, marking this task as not done yet:", indentationText));
                        taskList.get(taskIndex).markAsUndone();
                    }

                    // Print out Task 
                    Task task = taskList.get(taskIndex);
                    System.out.println(String.format("%s%s", indentationTaskStatus, task));

                }
                
                // Add - adds Task to list 
                // 3 types of Tasks: ToDo's, Events, Deadlines 
                else {
                    String[] textStrings = userInput.split(" "); 
                    String taskType = textStrings[0].toLowerCase(); 
                    String taskDescription = String.join(" ", Arrays.copyOfRange(textStrings, 1, textStrings.length));

                    System.out.println(String.format("%sNoted. I've added this task:", indentationText));

                    Task task = new Task("");
                    
                    // ToDo's 
                    if (taskType.equals("todo")) {

                        task = new Todo(taskDescription); 
                        taskList.add(task);
                    
                    // Events & Deadlines 
                    } else {

                        String[] taskDescriptionStrings = taskDescription.split("/");
                        String taskDescriptionText = taskDescriptionStrings[0].strip();
                        String[] taskDescriptionTimeStrings = taskDescriptionStrings[1].split(" ");
                        String taskDescriptionTime = String.join(" ", Arrays.copyOfRange(taskDescriptionTimeStrings, 1, taskDescriptionTimeStrings.length)).strip();

                        // Events 
                        if (taskType.equals("event")) { 

                            task = new Event(taskDescriptionText, taskDescriptionTime);
                            taskList.add(task);
                        
                        // Deadlines 
                        } else if (taskType.equals("deadline")) {

                            task = new Deadline(taskDescriptionText, taskDescriptionTime);
                            taskList.add(task);
                        }
                        
                    }

                    System.out.println(String.format("%s%s", indentationTaskStatus, task));
                    System.out.println(String.format("%sNow you have %d tasks in the list.", indentationText, taskList.size()));
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
