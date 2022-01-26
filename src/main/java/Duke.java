import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    ArrayList<Task> taskList = new ArrayList<>();
    boolean firstUserChat = true;
    String userName;
    String taskFilePath;
    Ui ui;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        taskFilePath = filePath;
        ui = new Ui();
    }

    private void loadFileContents(String taskFilePath) {
        // load tasklist data from save 
        try {
            File f = new File(taskFilePath);
            
            // checks if the user already has existing save data 
            if (!f.createNewFile()) {
                ui.showTask("loading previous save data...");
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
                ui.showText("creating save data...");
            }
            
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
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
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        
        // Jarvis introduces himself, asks for user's name & greets user
        userName = ui.showWelcome();

        // load TaskList from existing data 
        loadFileContents(taskFilePath);

        boolean active = true;

        while(active) {
            try {

                String userInput = ui.readCommand();
                String[] userInputString = userInput.split(" ", 2);
                String command = userInputString[0];
                Command commandType = Command.getCommand(command);
                String description = userInputString.length > 1 ? userInputString[1] : "" ;

                switch (commandType) {
                    
                    // Exit - "bye", exits the program 
                    case BYE:
                        ui.showGoodbye();
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
                ui.showError(e.getMessage());
            }
        }

    }

    private void addTodo(String taskDescription) throws DukeException {
        // check if the user's input is just whitespaces
        if (taskDescription.trim().length() == 0) {
            throw new DukeException("I'm sorry, your event command is missing a task description. Please try again."); 
        }
        Task task = new Todo(taskDescription, false); 
        taskList.add(task);
        ui.showText("Noted. I've added this task: ");
        ui.showTask(task.toString());
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
            ui.showText("Noted. I've added this task: ");
            ui.showText(task.toString());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
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
                ui.showText("Okay, marking this task as done:");
                ui.showTask(task.toString());
                
            } catch (DukeException e) {
                ui.showError(e.getMessage());
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
                ui.showText("Okay, marking this task as not done yet:");
                ui.showTask(task.toString());
                
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        } else {
            throw new DukeException("I'm sorry, you missed out the task index");
        }
    }

    private void listTasks() {
        if (taskList.size() == 0) {
            ui.showText("Your list is empty");
        }
        else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                ui.showText((i+1) + ". " + task.toString());
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
        ui.showText("Okay, I've deleted this task");
        ui.showTask(task.toString());
    }

}
