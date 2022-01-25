import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    String splitter = "______________________________________";
    String indentationBase = "\t";
    String indentationText = "\t  ";
    String indentationTaskStatus = "\t    ";
    ArrayList<Task> taskList = new ArrayList<>();
    boolean firstUserChat = true;
    String userName = "";
    String taskFilePath = "data/tasks.txt";

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

    private void loadFileContents(String taskFilePath) {
        // load tasklist data from save 
        try {
            File f = new File(taskFilePath);
            
            // checks if the user already has existing save data 
            if (!f.createNewFile()) {
                System.out.println(indentationText + "loading previous save data...");
                Scanner s = new Scanner(f);
                while(s.hasNext()) {
                    
                    String task = s.nextLine();
                    String[] taskStrings = task.split(" \\| ");
                    String taskType = taskStrings[0];
                    boolean taskStatus = taskStrings[1] == "1";
                    String taskDescription = taskStrings[2];

                    switch (taskType) {
                        case "T":
                            taskList.add(new Todo(taskDescription, taskStatus));
                            break;
                        case "E":
                            taskList.add(new Event(taskDescription, taskStrings[3], taskStatus));
                            break;
                        case "D":
                            taskList.add(new Deadline(taskDescription, taskStrings[3], taskStatus));
                            break;
                        default:
                            throw new DukeException("Invalid save data");
                    }
                }
                s.close();
            // else creates the save data for further use
            } else {
                System.out.println(indentationText + "creating save data...");
            }
            
        } catch (IOException e) {
            System.err.println(indentationText + e.getMessage());
        } catch (DukeException e) {
            System.err.println(indentationText + e.getMessage());
        }
    }

    private void updateFileContents(String taskFilePath) {
        // write & update save data with current tasklist 
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            for (Task t: taskList) {
                fw.write(t.toStringSaveData());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.err.print(indentationText + e.getMessage());
        }
    }

    public void run() {
        
        // Jarvis introduces himself & asks for user's name
        System.out.println(String.format("%sHello, I'm\n%s%syour personal assistant.", indentationText, logo, indentationText));
        System.out.println(String.format("%sHow should I address you?", indentationText));

        Scanner sc = new Scanner(System.in);
        userName = sc.nextLine();

        // Jarvis addresses user
        System.out.println(String.format("%sSplendid! My pleasure to serve you %s ", indentationText, userName));

        // load TaskList from existing data 
        loadFileContents(taskFilePath);

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
                        markTask(description, commandType.getRegex());
                        updateFileContents(taskFilePath);
                        break;
                    
                    // Unmark, "unmark itemIndexNumber", marks an item as undone 
                    case UNMARK:
                        unmarkTask(description, commandType.getRegex());
                        updateFileContents(taskFilePath);
                        break;

                    // Delete - removes Tasks from the list 
                    case DELETE:
                        deleteTask(description);
                        updateFileContents(taskFilePath);
                        break;

                    // Adds a todo task
                    case TODO:
                        addTodo(description);
                        updateFileContents(taskFilePath);
                        break;

                    // Adds an event task 
                    case EVENT:
                        addEventOrDeadline(description, commandType, commandType.getRegex());
                        updateFileContents(taskFilePath);
                        break;

                    // Adds a deadline task 
                    case DEADLINE:
                        addEventOrDeadline(description, commandType, commandType.getRegex());
                        updateFileContents(taskFilePath);
                        break;
                }
            }
            
            catch(DukeException e) {
                System.err.println(String.format("%s%s", indentationText, e.getMessage()));
            }
        }

        sc.close();
    }

    private void addTodo(String taskDescription) throws DukeException {
        // check if the user's input is just whitespaces
        if (taskDescription.trim().length() == 0) {
            throw new DukeException("I'm sorry, your event command is missing a task description. Please try again."); 
        }
        Task task = new Todo(taskDescription, false); 
        taskList.add(task);
        System.out.println(String.format("%sNoted. I've added this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));
    }

    private void addEventOrDeadline(String taskDescription, Command commandType, String regex) throws DukeException {
        // check if the user's input is correctly formatted
        if (!taskDescription.contains(regex)) {
            throw new DukeException("Your " + commandType + " command is incorrectly formatted. Please use " + regex);
        }

        String[] taskDescriptionStrings = taskDescription.split(regex);
        String taskDescriptionText = taskDescriptionStrings[0].strip();
        String taskDescriptionTime = taskDescriptionStrings[1].strip();

        // check if the user's input is just whitespace 
        if (taskDescriptionText.strip().length() == 0) {
            throw new DukeException("I'm sorry, your " + commandType + " command is missing a task description. Please try again.");
        } else if (taskDescriptionTime.strip().length() == 0) {
            throw new DukeException("I'm sorry, your " + commandType + " command is missing a time. Please try again.");
        }

        try {
            Task task;

            if (commandType.equals(Command.EVENT)) {
                task = new Event(taskDescriptionText, taskDescriptionTime, false);
            } else {
                task = new Deadline(taskDescriptionText, taskDescriptionTime, false);
            }
            
            taskList.add(task);
            System.out.println(String.format("%sNoted. I've added this task:", indentationText));
            System.out.println(String.format("%s%s", indentationTaskStatus, task));
        } catch (DukeException e) {
            System.err.println(indentationText + e.getMessage());
        }
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
                System.err.println(String.format("%s%s", indentationText, e.getMessage())); 
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
                task.markAsUndone();
                System.out.println(String.format("%sOkay, marking this task as not done yet:", indentationText));
                System.out.println(String.format("%s%s", indentationTaskStatus, task));
                
            } catch (DukeException e) {
                System.err.println(String.format("%s%s", indentationText, e.getMessage())); 
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
        if (taskIndex >= taskList.size()) {
            throw new DukeException("I'm sorry, you're referencing a task that does not exist!");
        }

        Task task = taskList.remove(taskIndex);
        System.out.println(String.format("%sOkay, I've deleted this task:", indentationText));
        System.out.println(String.format("%s%s", indentationTaskStatus, task));
    }

}
