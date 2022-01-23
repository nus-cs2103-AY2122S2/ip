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
    String userName = "";

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
        userName = sc.nextLine();
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
                Command commandType = Command.getCommand(command);
                String description = userInputString.length > 1 ? userInputString[1] : "" ;

                // unrecognised input by user 
                if (commandType == null) {
                    throw new DukeException("I'm sorry, you've input a command I don't recognize. Please try again.");
                }

                // Start of Duke's text block 
                System.out.println(String.format("%s%s", indentationBase, splitter));

                switch (commandType) {
                    
                    // Exit - "bye", exits the program 
                    case BYE:
                        System.out.println(String.format("%sGoodbye for now. \n", indentationText));
                        active = false;
                        break;

                    // List - "list", lists all of the user's inputs 
                    case LIST:
                        listTasks();
                        break;
                    
                    // Mark, "mark itemIndexNumber", marks an item as done 
                    case MARK:
                        markTask(description, Command.MARK.getRegex());
                        break;
                    
                    // Unmark, "unmark itemIndexNumber", marks an item as undone 
                    case UNMARK:
                        unmarkTask(description, Command.UNMARK.getRegex());
                        break;

                    // Delete - removes Tasks from the list 
                    case DELETE:
                        deleteTask(description);
                        break;

                    // Adds a todo task
                    case TODO:
                        addTodo(description);
                        break;

                    // Adds an event task 
                    case EVENT:
                        addEventOrDeadline(description, Command.EVENT.getRegex());
                        break;

                    // Adds a deadline task 
                    case DEADLINE:
                        addEventOrDeadline(description, Command.DEADLINE.getRegex());
                        break;
                }
            }
            
            catch(DukeException e) {
                System.out.println(String.format("%s%s", indentationText, e.getMessage()));
            }
        }

        sc.close();
    }

    private void addTodo(String taskDescription) throws DukeException {
        // check if the user's input is just whitespaces
        if (taskDescription.trim().length() == 0) {
            throw new DukeException("I'm sorry, your event command is missing a task description. Please try again."); 
        }
        Task task = new Todo(taskDescription); 
        taskList.add(task);
        System.out.println(String.format("%sNoted. I've added this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));
    }

    private void addEventOrDeadline(String taskDescription, String regex) throws DukeException {
        // check if the user's input is correctly formatted
        if (!taskDescription.contains(regex)) {
            throw new DukeException("Your event command is incorrectly formatted. Please try again.");
        }

        String[] taskDescriptionStrings = taskDescription.split(regex);
        String taskDescriptionText = taskDescriptionStrings[0].strip();
        String taskDescriptionTime = taskDescriptionStrings[1].strip();

        // check if the user's input is just whitespace 
        if (taskDescriptionText.strip().length() == 0) {
            throw new DukeException("I'm sorry, your event command is missing a task description. Please try again.");
        } else if (taskDescriptionTime.strip().length() == 0) {
            throw new DukeException("I'm sorry, your event command is missing a time. Please try again.");
        }

        Task task = new Event(taskDescriptionText, taskDescriptionTime);
        taskList.add(task);
        System.out.println(String.format("%sNoted. I've added this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));
    }

    private void markTask(String id, String regex) throws DukeException { 
        // when the user input has the "mark" command & an integer 
        if (id.toLowerCase().matches(regex)) {
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

    private void unmarkTask(String id, String regex) throws DukeException {
        
        // when the user input has the "unmark" command & an integer 
        if (id.toLowerCase().matches(regex)) {
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

    private void listTasks() {
        if (taskList.size() == 0) {
            System.out.println(String.format("%sYour list is empty %s", indentationText, userName));
        }
        else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(String.format("%s%d. %s", indentationText, i+1, task)); 
            }
        }
    }

    private void deleteTask(String id) throws DukeException {
        // check if the user input an int 
        if (!id.trim().matches("\\d+")) {
            throw new DukeException("I'm sorry, you missed out the task index");
        }
        
        int taskIndex = Integer.valueOf(id.trim()) - 1;

        // if user-specified task index is out of the list 
        if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
        }

        Task task = taskList.remove(taskIndex);
        System.out.println(String.format("%sOkay, I've deleted this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));
    }

}
