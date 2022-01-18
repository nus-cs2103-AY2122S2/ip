import java.util.Scanner;
import java.util.ArrayList;
import chatbot.Task;
public class Duke {
    static String straightLine = "____________________________________________________________";
    
    public static void insertNewTask(String taskName, ArrayList<Task> taskList) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        String reply = "added: " + taskName;
        System.out.println(straightLine + "\n " + reply + "\n" + straightLine);
    }
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        // print introduction message
        String introductionMessage = "Good day Sir. My name is Dook. \n How may I be of assistance?";
        System.out.println(straightLine + "\n " + introductionMessage + "\n" + straightLine);
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (true) {
            String inputText = scanner.nextLine();
            if (inputText.equals("bye")) { // bye command
                String byeMessage = "Farewell Sir. May you have a wonderful day.";
                System.out.println(straightLine + "\n " + byeMessage + "\n" + straightLine);
                scanner.close();
                break;
            
            } else if (inputText.equals("list")) { // list command
                Integer counter = 1;
                String reply = "";
                for (Task task : taskList) {
                    String taskName = task.getTaskName();
                    reply += "\n " + counter.toString() + ".[" + task.getStatusIcon() +  "] " + taskName; // note: new line is at the start
                    counter++;
                }
                String listHeader = "This is your agenda of tasks Sir:";
                System.out.println(straightLine + "\n " + listHeader + reply + "\n" + straightLine);

            } else if (inputText.contains("mark")) { // mark / unmark command
                String[] inputStringArray = inputText.split(" ");
                if (inputStringArray.length > 1 && inputStringArray[0].contains("mark")) { // mark / unmark command
                    // check if it is actually a mark / unmark command
                    boolean markAsDone = false;
                    if (inputStringArray[0].equals("mark")) {
                        markAsDone = true;
                    } else if (inputStringArray[0].equals("unmark")) {
                        markAsDone = false;
                    } else { // not a mark / unmark command
                        insertNewTask(inputText, taskList);
                        break;
                    }
                    
                    // check if there is an integer after the text command input
                    String inputNumberString = inputStringArray[1];
                    try {
                        Integer inputNumberInteger = Integer.parseInt(inputNumberString);
                        String reply = "";
                        int taskIndex = inputNumberInteger - 1;
                        if (taskIndex >= taskList.size()) { // invalid integer input
                            reply = "I'm very sorry Sir, there is no such task you mentioned.";
                        } else {
                            Task task = taskList.get(taskIndex);
                            task.markTask(markAsDone);
                            
                            reply = "Very well Sir, I have marked this task as incomplete: \n";
                            if (markAsDone) {
                                reply = "Very well Sir, I have marked this task as complete: \n";
                            }
                            reply += "   [" + task.getStatusIcon() + "] " + task.getTaskName();
                        }
                        System.out.println(straightLine + "\n " + reply + "\n" + straightLine);

                    } catch (NumberFormatException exception) { // not a number, hence not a mark / unmark command
                        insertNewTask(inputText, taskList);
                    }
                } else { // not a mark / unmark command, hence new task
                    insertNewTask(inputText, taskList);
                }

            } else { // other text input, hence new task
                insertNewTask(inputText, taskList);
            }
        }
    }
}
