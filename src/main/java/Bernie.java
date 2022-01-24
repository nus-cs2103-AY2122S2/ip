import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Bernie the bot that is the driver for the responses to the user.
 * Internally, Bernie has Tasklist, which contain the Tasks that a user inputs
 */

public class Bernie {
    TaskList tasks;
    String lineBreak = "___________________________________________________________";
    String root = System.getProperty("user.dir");
    File tasksFile;
    File dataDir;

    enum Type {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE
    }

    /**
     * Constructs a new Bot containing TaskList
     */
    Bernie() {
        this.tasks = new TaskList();
        this.tasksFile = new File(root + "/data", "Bernie.txt");
        this.dataDir = new File(root, "data");
    }
    void greet() {
        System.out.println("Hello! I'm Bernie\nWhat's up?");
        System.out.println(lineBreak);
    }
    void leave() {
        System.out.println("See ya!");
    }

    /**
     * Saves the most updated tasks whenever the tasks changes upon
     * delete or add by writing the file. The file is saved to ./data/Bernie.txt
     */
    void saveTasks() {
        try {
            if (dataDir.exists() && tasksFile.exists()) {
                tasks.save(tasksFile);
            } else {
                // create dir and file
                dataDir.mkdir();
                tasksFile.createNewFile();
                tasks.save(tasksFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the data when Bernie starts up if it exists and reads. If doesn't
     * exist, creates the required files
     */
    void readTasks() {
        try {
            if (tasksFile.exists() && dataDir.exists()) {
                System.out.println("On the list:");
                tasks.read(tasksFile);
                System.out.println(lineBreak);
            } else {
                // create dir and file
                dataDir.mkdir();
                tasksFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays to the user a message according to their input. Actions are performed according
     * to the input. Exceptions are caught and printed out for the user.
     * @param input String, user input
     * @return boolean value, indicating if the program will end or not.
     */
    boolean respond(String input) {
        try {
            if (input.equals("list")) {
                list();
            } else if (input.equals("bye")) {
                leave();
            } else if (isMarkInput(input)) {
                mark(input);
            } else if (input.equals("")) {
                throw new BernieException("Say something???");
            } else if (isDeleteInput(input)) {
                delete(input);
            } else {
                add(input);
            }
        } catch (BernieException e) {
            System.out.println(e.getMessage());
            System.out.println(lineBreak);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return input.equals("bye");
        }
    }

    /**
     * Verifies if an input is of "mark" type: meaning either "mark" or "unmark"
     * @param input String, user input
     * @return boolean
     */
    boolean isMarkInput(String input) {
        return input.indexOf("mark") == 0 || input.indexOf("unmark") == 0;
    }
    /**
     * Verifies if an input is of "delete" type
     * @param input String, user input
     * @return boolean
     */
    boolean isDeleteInput(String input) {
        return input.indexOf("delete") == 0;
    }

    /**
     * Bernie will decide what kind of task is to be created. Bernie splits the input accordingly,
     * to get the parameters required to create the type of task. The creation and adding of task will be
     * handled by the TaskList. New state of tasks is saved to the data directory
     * after we add a task.
     * @param input String, given by user. Bernie verifies the Task type and the Task either is todo,
     * deadline or event
     * @throws BernieException, if the task is not a valid type.
     */
    void add(String input) throws BernieException {
        Task newTask;
        if (isType(Type.TODO, input)) {
            String[] inputArr = getParams(Type.TODO, input);
            newTask = tasks.addTask(inputArr, "todo");
        } else if (isType(Type.DEADLINE, input)) {
            String[] inputArr = getParams(Type.DEADLINE, input);
            newTask = tasks.addTask(inputArr, "deadline");
        } else if (isType(Type.EVENT, input)) {
            String[] inputArr = getParams(Type.EVENT, input);
            newTask = tasks.addTask(inputArr, "event");
        } else {
            throw new BernieException("Not a valid type of task!");
        }
        saveTasks();
        System.out.printf("Got ya. Added:\n%s\nYou got %d tasks waiting for ya!\n",
                newTask, tasks.numTasksLeft());
        System.out.println(lineBreak);
    }

    /**
     * Verifies the user input is of which task
     * @param taskType String
     * @param input String
     * @return boolean to affirm if the input is of this task
     */
    boolean isType(Type taskType, String input) {
        return input.indexOf(taskType.name().toLowerCase()) == 0;
    }

    /**
     * Delete the task according to the user input. The new state of the
     * tasks is saved to the data directory after deletion of task
     * @param input String, which is of the form delete ___
     */
    void delete(String input) {
        try {
            String taskNum = getParams(Type.DELETE, input)[0];
            Task deletedTask = tasks.deleteTask(taskNum);
            saveTasks();
            System.out.printf("Got ya. Removed:\n%s\nYou got %d tasks waiting for ya!\n",
                    deletedTask, tasks.numTasksLeft());
            System.out.println(lineBreak);
        } catch (BernieException e){
            System.out.println(e.getMessage());
            System.out.println(lineBreak);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Splits a user input into an array containing parameters for creating Task accordingly,
     * depending on taskType.
     * @param taskType String
     * @param input String, a user input to be split into an array of parameters
     * @return String[] inputArr.
     * For "todo": an array of 1, containing description.
     * For "deadline": an array of 2: [description, by]
     * For "event": an array of 2: [description, at]
     * For "mark": an array of 2: [action, taskNum]
     * For "delete": an array of 1: [taskNum]
     */
    String[] getParams(Type taskType, String input) throws BernieException {
        String description;
        String[] inputArr = null;
        switch (taskType) {
            case TODO:
                inputArr = input.split("todo ");
                description = getDescription(inputArr, Type.TODO);
                inputArr = new String[]{description};
                break;
            case DEADLINE:
                inputArr = input.split(" /by ");
                description = getDescription(inputArr, Type.DEADLINE);
                String by = getTime(inputArr);
                inputArr = new String[]{description, by};
                break;
            case EVENT:
                inputArr = input.split(" /at ");
                description = getDescription(inputArr, Type.EVENT);
                String at = getTime(inputArr);
                inputArr = new String[]{description, at};
                break;
            case MARK:
                inputArr = input.split(" ");
                // check valid
                checkMarkOrDeleteInput(inputArr, Type.MARK);
                break;
            case DELETE:
                inputArr = input.split(" ");
                checkMarkOrDeleteInput(inputArr, Type.DELETE);
                inputArr = new String[]{inputArr[1]};
                break;
            default:
                break;
        }
        return inputArr;
    }

    /**
     * Bernie gets the TaskList to print out every Task contained inside
     */
    void list() {
        System.out.println("Here's what you need to do buddy:");
        tasks.listTasks();
        System.out.println(lineBreak);
    }

    /**
     * Mark or unmark a task number depending on the input. Handles error if user
     * enters invalid mark inputs etc.
     * @param input String, containing mark/unmark and the task number to perform action
     */
    void mark(String input) {
        try {
            String[] inputArr = getParams(Type.MARK, input);
            String action = inputArr[0];
            String taskNum = inputArr[1];
            if (action.equals("mark")) {
                Task markedTask = tasks.markTask(Type.MARK, taskNum);
                System.out.printf("This is now done:\n%s\n", markedTask);
                System.out.println(lineBreak);
            } else if (action.equals("unmark")) {
                Task unmarkedTask = tasks.markTask(Type.UNMARK, taskNum);
                System.out.printf("This is now undone:\n%s\n", unmarkedTask);
                System.out.println(lineBreak);
            }
        } catch (BernieException e) {
            System.out.println(e.getMessage());
            System.out.println(lineBreak);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks for valid "mark" or "delete" action inputs. Throws error if it is not valid.
     * @param inputArr String[], the user input String split into an array of String for processing
     *                 parameters for the action
     * @throws BernieException for invalid inputs
     */
    void checkMarkOrDeleteInput(String[] inputArr, Type action) throws BernieException {
        if (inputArr.length == 2) {
            try {
                String taskNum = inputArr[1];
                Integer.parseInt(taskNum);
                tasks.taskExists(taskNum);
            } catch (NumberFormatException nfe) {
                throw new BernieException("That's not a task number! Put a number.");
            }
        } else {
            if (action.equals(Type.MARK)) {
                throw new BernieException("Wrong input. Type this: mark/unmark taskNumber");
            } else {
                throw new BernieException("Wrong input. Type this: delete taskNumber");
            }
        }
    }

    /**
     * Gets the description from the inputArr which contains parameters for creating Task
     * @param inputArr String[], parameters for creating Task, obtained from user input String.
     * @param taskType String
     * @return String description, for creating of Task
     * @throws BernieException for invalid descriptions such as empty or number description
     */
    String getDescription(String[] inputArr, Type taskType) throws BernieException {
        String description;
        try {
            if (taskType.equals(Type.TODO)) {
                description = inputArr[1];
            } else {
                // can take note of lowercase/uppercase
                description = inputArr[0].split(taskType.name().toLowerCase() + " ")[1];
            }
            checkDescriptionNotNumber(description);
            return description;
        } catch (IndexOutOfBoundsException e) {
            throw new BernieException("Where's the description?");
        }
    }

    boolean checkDescriptionNotNumber(String description) throws BernieException {
        try {
            Integer.parseInt(description);
            throw new BernieException("Description can't be numbers? We need String!");
        } catch (NumberFormatException nfe) {
            return true;
        }
    }

    /**
     * Gets the time from the inputArr which contains parametrs for creating Task
     * @param inputArr String[], parameters for creating Task, obtained from user input String.
     * @return String time, for craeting of Task
     * @throws BernieException if there is no time input given
     */
    String getTime(String[] inputArr) throws BernieException {
        try {
            return inputArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new BernieException("Where's your time input?");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bernie bernie = new Bernie();
        bernie.greet();
        bernie.readTasks();
        while (true) {
            String input = sc.nextLine();
            boolean end = bernie.respond(input);
            if (end) {
                break;
            }
        }
    }
}
