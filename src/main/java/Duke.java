public class Duke {

    TaskList taskList;
    boolean firstUserChat = true;
    String userName;
    String taskFilePath;
    Ui ui;
    Storage storage;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        taskFilePath = filePath;
        ui = new Ui();
        storage = new Storage(taskFilePath, ui);
        // load TaskList from existing data 
        taskList = new TaskList(storage.loadFileContents());
    }

    public void run() {
        
        // Jarvis introduces himself, asks for user's name & greets user
        userName = ui.showWelcome();

        boolean active = true;

        while(active) {
            try {

                String userInput = ui.readCommand();
                String[] userInputString = userInput.split(" ", 2);
                String command = userInputString[0];
                CommandType commandType = CommandType.getCommand(command);
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
                        storage.updateFileContents(taskList);
                        break;
                    
                    // Unmark, "unmark itemIndexNumber", marks an item as undone 
                    case UNMARK:
                        unmarkTask(description, commandType.getRegex());
                        storage.updateFileContents(taskList);
                        break;

                    // Delete - removes Tasks from the list 
                    case DELETE:
                        deleteTask(description);
                        storage.updateFileContents(taskList);
                        break;

                    // Adds a todo task
                    case TODO:
                        addTodo(description);
                        storage.updateFileContents(taskList);
                        break;

                    // Adds an event task 
                    case EVENT:
                        addEventOrDeadline(description, commandType, commandType.getRegex());
                        storage.updateFileContents(taskList);
                        break;

                    // Adds a deadline task 
                    case DEADLINE:
                        addEventOrDeadline(description, commandType, commandType.getRegex());
                        storage.updateFileContents(taskList);
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
            throw new DukeException("I'm sorry, your todo command is missing a task description. Please try again."); 
        }
        Task task = new Todo(taskDescription, false); 
        taskList.add(task);
        ui.showText("Noted. I've added this task: ");
        ui.showTask(task.toString());
    }

    private void addEventOrDeadline(String taskDescription, CommandType commandType, String regex) throws DukeException {
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

            if (commandType.equals(CommandType.EVENT)) {
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
