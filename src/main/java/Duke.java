import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.nio.file.Files;

public class Duke {
    public ArrayList<Task> userTasks;
    private final String SAVEDTASKSPATH;

    public Duke(String savedTasksPath){
        this.userTasks = new ArrayList<Task>();
        this.SAVEDTASKSPATH = savedTasksPath;
    }

    public void print(Object str){
        System.out.println(str);
        System.out.println("_".repeat(100));
    }

    public void printBye(){
        this.print("Bye. Hope I've motivated you as much as I could have, and SMILE :D");
    }

    public void addTask(Task task) {
        this.userTasks.add(task);
    }

    public void deleteTask(int taskIndex) throws DukeException {
        if (this.checkValidTask(taskIndex)) {
            Task userTask = this.userTasks.get(taskIndex);
            this.userTasks.remove(taskIndex);
            this.print("I have removed " + userTask);
        }
    }

    public void printUserTasks() {
        int numberOfTasks = this.userTasks.size();
        int counter = 0;
        if (numberOfTasks == 0){
            this.print("You currently do not have any outstanding tasks! Great job! :D");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 1; i <= numberOfTasks; i++) {
                Task userTask = this.userTasks.get(i - 1);
                System.out.println((String.valueOf(i) + ": " + userTask));
                if (!userTask.checkIsDone()) {
                    counter += 1;
                }
            }
            if (counter == 0) {
                this.print("You have completed all your tasks! Great job!");
            } else {
                this.print("These are all your tasks! Only " + String.valueOf(counter) + " more remaining! Keep going!");
            }
        }
    }

    public boolean checkValidTask(int taskIndex) throws DukeException {
        if ((taskIndex > this.userTasks.size()-1) || (taskIndex <0)) {
            throw new DukeException("There is no such task! Maybe you entered the wrong task?");
        } else {
            return true;
        }
    }

    public void markTaskDone(int taskIndex) throws DukeException { //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTasks.get(taskIndex);
            task.markAsDone();
        }
    }

    public void markTaskNotDone(int taskIndex) throws DukeException { //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTasks.get(taskIndex);
            task.markAsNotDone();
        }
    }

    private void appendToFile(String filePath, String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e){
            throw new DukeException(("We did not manage to save your tasks:\n" + e.getMessage()));
        }
    }

    private void createFile(String filePath) throws DukeException {
        try {
            File yourFile = new File(filePath);
            yourFile.getParentFile().mkdirs(); //
            yourFile.createNewFile(); //Will not create new file if it exists
        } catch (IOException e) {
            throw new DukeException("We did not manage to create a file to save your tasks!");
        }
    }

    protected void saveTasks(String filePath) throws DukeException {
        try {
            Files.deleteIfExists(new File(filePath).toPath());
        } catch (IOException e) {
            throw new DukeException("File does not exist!");
        }
        this.createFile(filePath); //Creates file at filePath if it does not exist
        for (Task task:this.userTasks){
            this.appendToFile(this.SAVEDTASKSPATH, task.toSaveDataFormat());
            }
        }

    protected void loadTasks() throws DukeException {
        try {
            File file = new File(this.SAVEDTASKSPATH);
            Scanner s = new Scanner(file);
            while (s.hasNext()){
                String loadedTask = s.nextLine();
                this.parseLoadedTask(loadedTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No past tasks found! Let's start creating tasks!\n");
        }
    }

    private void parseLoadedTask(String loadedTask) throws DukeException {
        String[] loadedTaskSplit = loadedTask.split(Pattern.quote("|"));
        switch (loadedTaskSplit[0]) {
            case "T":
                Todo todo = new Todo(loadedTaskSplit[2]);
                this.addTask(todo);
                if (loadedTaskSplit[1].equals("1")) {
                    this.markTaskDone(this.userTasks.size()-1);
                }
                break;
            case "D":
                Deadline deadline = new Deadline(loadedTaskSplit[2], loadedTaskSplit[3]);
                this.addTask(deadline);
                if (loadedTaskSplit[1].equals("1")) {
                    this.markTaskDone(this.userTasks.size()-1);
                }
                break;
            case "E":
                Event event = new Event(loadedTaskSplit[2], loadedTaskSplit[3]);
                this.addTask(event);
                if (loadedTaskSplit[1].equals("1")) {
                    this.markTaskDone(this.userTasks.size()-1);
                }
                break;
        }
    }

    public void performCommand(String userTaskString) throws DukeException {
            if (userTaskString.equalsIgnoreCase("bye")){
                this.saveTasks(this.SAVEDTASKSPATH);
                this.printBye();
                System.exit(0);
            } else if (userTaskString.length() == 0){
                throw new DukeException("You have not keyed in any task! Maybe you missed something? Don't worry, just try again!");
            } else if (userTaskString.equalsIgnoreCase("list")){
                this.printUserTasks();
            } else if (userTaskString.matches("^(delete|Delete|DELETE).*")){
                    try {
                        this.deleteTask(Integer.parseInt(userTaskString.substring(6+1))-1);
                        //+1 is to take into account the " "after delete, -1 is to convert it into 0-based indexing
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "delete" without a number
                        throw new DukeException("Please enter a task number to delete!");
                    } catch (NumberFormatException err) { // For cases like "delete1" without a space in between or "delete 3b" where the term is not a number
                        throw new DukeException("Please delete a task in the following format:\n" +
                                "delete [number]");
                    }
            } else if (userTaskString.matches("^(mark|Mark|MARK).*")){
                    try {
                        int taskIdx = Integer.parseInt(userTaskString.substring(4+1))-1;
                        this.markTaskDone(taskIdx);
                        this.print("Congratulations on completing the task!\n" + this.userTasks.get(taskIdx));
                        //+1 is to take into account the " "after mark, -1 is to convert it into 0-based indexing
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "mark" without a number
                        throw new DukeException("Please enter a task number to mark!");
                    } catch (NumberFormatException err) { // For cases like "mark1" without a space in between or "mark 3b" where the term is not a number
                        throw new DukeException("Please mark a task in the following format:\n" +
                                "mark [number]");
                    }
            } else if (userTaskString.matches("^(unmark|Unmark|UNMARK).*")){
                    try {
                        int taskIdx = Integer.parseInt(userTaskString.substring(6+1))-1;
                        this.markTaskNotDone(taskIdx);
                        this.print("I have un-marked this task!\n" + this.userTasks.get(taskIdx));
                        //+1 is to take into account the " "after unmark, -1 is to convert it into 0-based indexing
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "unmark" without a number
                        throw new DukeException("Please enter a task number to unmark!");
                    } catch (NumberFormatException err) { // For cases like "unmark1" without a space in between or "unmark 3b" where the term is not a number
                        throw new DukeException("Please unmark a task in the following format:\n" +
                                "unmark [number]");
                    }
            } else if (userTaskString.matches("^(todo|Todo|TODO).*")){
                    try {
                        Todo userTodo = new Todo(userTaskString.substring(4+1));
                        this.addTask(userTodo);
                        this.print("Fantabulous! You have added this Todo Item:\n" + userTodo);
                    } catch (StringIndexOutOfBoundsException err) { //For cases like "todo" without any further description
                        throw new DukeException("Please enter a description!");
                    }
            } else if (userTaskString.matches("^(deadline|Deadline|DEADLINE).*")){
                    try {
                        String userTaskStringSliced = userTaskString.substring(8+1);
                        String[] descriptionDeadlineSplit = userTaskStringSliced.split(" /by ", 2);
                        Deadline userDeadline = new Deadline(descriptionDeadlineSplit[0], descriptionDeadlineSplit[1]);
                        this.addTask(userDeadline);
                        this.print("Perfect! You have added this Deadline Item:\n" + userDeadline + "\nRemember to stick to your objective date!");
                    } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | DateTimeParseException err) {
                        throw new DukeException("Please enter a Deadline in the following format:\n" +
                                "deadline [description] /by [yyyy-mm-dd]");
                    }
                    //StringIndexOutOfBoundsException For cases like "deadline" without any other information
                    //ArrayIndexOutOfBoundsException For cases like "deadline homework" without a "/by"
                    //DateTimeParseException For cases where date cannot be recognised
            } else if (userTaskString.matches("^(event|Event|EVENT).*")){
                    try {
                        String userTaskStringSliced = userTaskString.substring(5+1);
                        String[] descriptionTimingSplit = userTaskStringSliced.split(" /at ", 2);
                        Event userEvent = new Event(descriptionTimingSplit[0], descriptionTimingSplit[1]);
                        this.addTask(userEvent);
                        this.print("Perfect! You have added this Event Item:\n" + userEvent + "\nRemember to be there 5 minutes early!");
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "event" without any other information
                        throw new DukeException("Please enter an Event in the following format:\n" +
                                "event [description] /at [datetime]");
                    } catch (ArrayIndexOutOfBoundsException err) { //For cases like "event meeting" without a "/at"
                        throw new DukeException("Please enter an Event in the following format:\n" +
                                "event [description] /at [datetime]");
                    }
            } else { // If a user does not specify a todo, deadline or event
                throw new DukeException("Invalid Input! Please either add in a Todo, Deadline or Event!");
            }
        }

    public static void main(String[] args) throws DukeException {
        Scanner sc= new Scanner(System.in);
        Duke duke = new Duke("./data/duke.txt");
        duke.loadTasks();
        duke.print("Hello, My Dear Friend... I'm Duke, your personal motivator!");
        Quote quoteOfTheDay = new Quote();
//        duke.print(quoteOfTheDay.generateQuote());
        duke.print("What can i do for you today?");
        while (true) {
            String userTaskString = sc.nextLine();
            try {
                duke.performCommand(userTaskString);
            } catch (DukeException exception){
                duke.print(exception);
            }
        }
    }
}
