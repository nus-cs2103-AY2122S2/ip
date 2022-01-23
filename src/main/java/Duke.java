import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

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

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        
        System.out.println(String.format("%sHello, I'm\n%s%syour personal assistant.", indentationText, logo, indentationText));

        Scanner sc = new Scanner(System.in);
        System.out.println(String.format("%sHow should I address you?", indentationText));
        String userName = sc.nextLine();
        System.out.println(String.format("%sSplendid! My pleasure to serve you %s ", indentationText, userName));

        boolean active = true;

        while(active) {
            try {
                System.out.println(String.format("%sWhat %smay I assist you with today, %s? \n\t%s", indentationText, (firstUserChat ? "" : "else "), userName, splitter));
                firstUserChat = (firstUserChat==true) ? false : firstUserChat;

                String userInput = sc.nextLine();
                userInput = userInput.trim();

                String[] userInputString = userInput.split(" ", 2);
                String command = userInputString[0];
                String description = userInputString.length > 1 ? userInputString[1] : "" ;

                // Start of Duke's text block 
                System.out.println(String.format("%s%s", indentationBase, splitter));

                switch (command) {
                    
                    // Exit - "bye", exits the program 
                    case "bye":
                        System.out.println(String.format("%sGoodbye for now. \n", indentationText));
                        active = false;
                        break;

                    // List - "list", lists all of the user's inputs 
                    case "list":
                        if (taskList.size() == 0) System.out.println(String.format("%sYour list is empty %s", indentationText, userName));
                        else {
                            for (int i = 0; i < taskList.size(); i++) {
                                Task task = taskList.get(i);
                                System.out.println(String.format("%s%d. %s", indentationText, i+1, task)); 
                            }
                        }
                        break;
                    
                    // Mark, "mark itemIndexNumber", marks an item as done 
                    // ^mark \\d+|^mark
                    case "mark":
                        markTask(description);
                        break;
                    
                    // Unmark, "unmark itemIndexNumber", marks an item as undone 
                    // ^unmark \\d+|^unmark
                    case "unmark":
                        unmarkTask(description);
                        break;

                    // Delete - removes Tasks from the list 
                    // "^delete \\d+|^delete"
                    case "delete":
                        // when the user input has the "delete" command & an integer 
                        if (userInput.matches("^delete \\d+")) {
                            String[] textStrings = userInput.split(" "); 
                            int taskIndex = Integer.valueOf(textStrings[1]) - 1;

                            // if user-specified task index is out of the list 
                            if (taskIndex >= taskList.size() || taskIndex < 0) {
                                throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
                            }

                            Task task = taskList.remove(taskIndex);
                            System.out.println(String.format("%sOkay, I've deleted this task:", indentationText));
                            System.out.println(String.format("%s%s", indentationTaskStatus, task));
                        } else {
                            throw new DukeException("I'm sorry, you missed out the task index");
                        }
                        break;

                    // "^todo .*|^todo"
                    case "todo":
                        //
                        addTodo(description);
                        break;

                    // "^event .*|^event", "/at"
                    case "event":
                        //
                        addEvent(description, "event");
                        break;

                    // "^deadline .*|^deadline", "/by"
                    case "deadline":
                        //
                        addDeadline(description, "deadline");
                        break;

                    default:
                        throw new DukeException("I'm sorry, you've input a command I don't recognize. Please try again.");
                        // break;
                }
                
            }
            
            catch(DukeException e) {
                System.out.println(String.format("%s%s", indentationText, e.getMessage()));
            }
        }

        sc.close();
    }

    private void addTodo(String taskDescription) {
        //
        Task task = new Todo(taskDescription); 
        taskList.add(task);
        System.out.println(String.format("%sNoted. I've added this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));
    }

    private void addEvent(String taskDescription, String taskType) throws DukeException {
        //
        if (!taskDescription.matches(".+ /at .+")) {
            throw new DukeException("Your " + taskType + " command is incorrectly formatted. Please try again.");
        }

        String[] taskDescriptionStrings = taskDescription.split("/");
        String taskDescriptionText = taskDescriptionStrings[0].strip();
        String[] taskDescriptionTimeStrings = taskDescriptionStrings[1].split(" ");
        String taskDescriptionTime = String.join(" ", Arrays.copyOfRange(taskDescriptionTimeStrings, 1, taskDescriptionTimeStrings.length)).strip();

        // Events 
        Task task = new Event(taskDescriptionText, taskDescriptionTime);
        taskList.add(task);
        System.out.println(String.format("%sNoted. I've added this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));

        // throw new DukeException("I'm sorry, your " + taskType + " command is missing a task description. Please try again.");
    }

    private void addDeadline(String taskDescription, String taskType) throws DukeException {
        //
        //
        if (!taskDescription.matches(".+ /by .+")) {
            throw new DukeException("Your " + taskType + " command is incorrectly formatted. Please try again.");
        }

        String[] taskDescriptionStrings = taskDescription.split("/");
        String taskDescriptionText = taskDescriptionStrings[0].strip();
        String[] taskDescriptionTimeStrings = taskDescriptionStrings[1].split(" ");
        String taskDescriptionTime = String.join(" ", Arrays.copyOfRange(taskDescriptionTimeStrings, 1, taskDescriptionTimeStrings.length)).strip();
        
        // Deadline 
        Task task = new Deadline(taskDescriptionText, taskDescriptionTime);
        taskList.add(task);
        System.out.println(String.format("%sNoted. I've added this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));

        // throw new DukeException("I'm sorry, your " + taskType + " command is missing a task description. Please try again.");
    }


    private void markTask(String id) throws DukeException { 
        // when the user input has the "mark" command & an integer 
        if (id.toLowerCase().matches("\\d+")) {
            try {
                int taskIndex = Integer.valueOf(id) - 1;
                
                // if user-specified task index is out of the list 
                if (taskIndex >= taskList.size() || taskIndex < 0) {
                    throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
                }

                Task task = taskList.get(taskIndex);
                task.markAsDone();
                System.out.println(String.format("%sOkay, marking this task as done:", indentationText));
                System.out.println(String.format("%s%s", indentationTaskStatus, task));
                
            } catch (DukeException e) {
                System.out.println(String.format("%s%s", indentationText, e.getMessage())); 
            }
        } else {
            throw new DukeException("I'm sorry, you missed out the task index");
        }
    }

    private void unmarkTask(String id) throws DukeException {
        
        // when the user input has the "unmark" command & an integer 
        if (id.toLowerCase().matches("\\d+")) {
            try {
                int taskIndex = Integer.valueOf(id) - 1;
                
                // if user-specified task index is out of the list 
                if (taskIndex >= taskList.size() || taskIndex < 0) {
                    throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
                }

                Task task = taskList.get(taskIndex);
                task.markAsDone();
                System.out.println(String.format("%sOkay, marking this task as not done yet:", indentationText));
                System.out.println(String.format("%s%s", indentationTaskStatus, task));
                
            } catch (DukeException e) {
                System.out.println(String.format("%s%s", indentationText, e.getMessage())); 
            }
        } else {
            throw new DukeException("I'm sorry, you missed out the task index");
        }
    }

}
