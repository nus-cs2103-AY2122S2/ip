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

        String logo =   indentationText +
                        "    _                  _     \n" + indentationText +
                        "   |_|                |_|    \n" + indentationText +
                        "    _  __ _ _ ____   ___ ___ \n" + indentationText +
                        "   | |/ _` | '__\\ \\ / / / __|\n" + indentationText +
                        "   | | (_| | |   \\ V /| \\__ \\\n" + indentationText +
                        "   | |\\__,_|_|    \\_/ |_|___/\n" + indentationText +
                        "  _/ |                       \n" + indentationText +
                        " |__/                        \n";
        System.out.println(String.format("%sHello, I'm\n%s%syour personal assistant.", indentationText, logo, indentationText));

        Scanner sc = new Scanner(System.in);
        System.out.println(String.format("%sHow should I address you?", indentationText));
        String userName = sc.nextLine();
        System.out.println(String.format("%sSplendid! My pleasure to serve you %s ", indentationText, userName));

        while(true) {
            try {
                System.out.println(String.format("%sWhat %smay I assist you with today, %s? \n\t%s", indentationText, (firstUserChat ? "" : "else "), userName, splitter));
                firstUserChat = (firstUserChat==true) ? false : firstUserChat;

                String userInput = sc.nextLine();
                userInput = userInput.trim();

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
                else if (userInput.toLowerCase().matches("^mark \\d+") || userInput.toLowerCase().matches("^unmark \\d+")) {

                    try {
                        String[] stringArray = userInput.toLowerCase().split(" ");
                        int taskIndex = Integer.valueOf(stringArray[1]) - 1;
                        
                        // if user-specified task index is out of the list 
                        if (taskIndex >= taskList.size() || taskIndex < 0) {
                            throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
                        }

                        // Mark 
                        if (stringArray[0].equals("mark")) {
                            
                            Task task = taskList.get(taskIndex);
                            task.markAsDone();
                            System.out.println(String.format("%sOkay, marking this task as done:", indentationText));
                            System.out.println(String.format("%s%s", indentationTaskStatus, task));
                        
                        // Unmark 
                        } else if (stringArray[0].equals("unmark")) {
                            
                            // taskList.get(taskIndex).markAsUndone();
                            Task task = taskList.get(taskIndex);
                            task.markAsDone();
                            System.out.println(String.format("%sOkay, marking this task as not done yet:", indentationText));
                            System.out.println(String.format("%s%s", indentationTaskStatus, task));
                        }
                    } catch (DukeException e) {
                        System.out.println(String.format("%s%s", indentationText, e.getMessage())); 
                    }

                }
                
                // Add - adds Task to list 
                // 3 types of Tasks: ToDo's, Events, Deadlines 
                else if (userInput.matches("^todo .*|^todo") || userInput.toLowerCase().matches("^event .*|^event") || userInput.toLowerCase().matches("^deadline .*|^deadline")) {
                    String[] textStrings = userInput.split(" "); 
                    String taskType = textStrings[0].toLowerCase(); 

                    if (userInput.toLowerCase().matches("^todo .+") || userInput.toLowerCase().matches("^event .+") || userInput.toLowerCase().matches("^deadline .+")) {
                        
                        String taskDescription = String.join(" ", Arrays.copyOfRange(textStrings, 1, textStrings.length));

                        // TODO - only printed when a task has been added 
                        System.out.println(String.format("%sNoted. I've added this task:", indentationText));

                        Task task = new Task("");
                        
                        // ToDo's 
                        if (taskType.equals("todo")) {

                            task = new Todo(taskDescription); 
                            taskList.add(task);
                        
                        // Events & Deadlines 
                        } else {

                            if (!taskDescription.matches(".+ /by .+|.+ /at .+")) {
                                throw new DukeException("Your " + taskType + " command is incorrectly formatted. Please try again.");
                            }

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
                    } else {
                        throw new DukeException("I'm sorry, your " + taskType + " command is missing a task description. Please try again.");
                    }
                } else {
                    throw new DukeException("I'm sorry, you've input a command I don't recognize. Please try again.");
                }

            }
            
            catch(DukeException e) {
                System.out.println(String.format("%s%s", indentationText, e.getMessage()));
            }
        }

        sc.close();
    }
}
