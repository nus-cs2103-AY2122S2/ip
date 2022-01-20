import java.util.Scanner;
/**
 * Bernie the bot that is the driver for the responses to the user.
 * Internally, Bernie has Tasklist, which contain the Tasks that a user inputs
 */

public class Bernie {
    TaskList tasks;
    String lineBreak = "___________________________________________________________";
    /**
     * Constructs a new Bot containing TaskList
     */
    Bernie() {
        this.tasks = new TaskList();
    }
    void greet() {
        System.out.println("Hello! I'm Bernie\nWhat's up?");
        System.out.println(lineBreak);
    }
    void leave() {
        System.out.println("See ya!");
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
     * Bernie will decide what kind of task is to be created. Bernie splits the input accordingly,
     * to get the parameters required to create the type of task. The creation and adding of task will be
     * handled by the TaskList.
     * @param input String, given by user. Bernie verifies the Task type and the Task either is todo,
     * deadline or event
     * @throws BernieException, if the task is not a valid type.
     */
    void add(String input) throws BernieException {
        Task newTask = null;
        if (isType("todo", input)) {
            String[] inputArr = getParams("todo", input);
            newTask = tasks.addTask(inputArr, "todo");
        } else if (isType("deadline", input)) {
            String[] inputArr = getParams("deadline", input);
            newTask = tasks.addTask(inputArr, "deadline");
        } else if (isType("event", input)) {
            String[] inputArr = getParams("event", input);
            newTask = tasks.addTask(inputArr, "event");
        } else {
            throw new BernieException("Not a valid type of task!");
        }
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
    boolean isType(String taskType, String input) {
        return input.indexOf(taskType) == 0;
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
     */
    String[] getParams(String taskType, String input) throws BernieException {
        String description;
        String[] inputArr = null;
        switch (taskType) {
            case "todo":
                inputArr = input.split("todo ");
                description = getDescription(inputArr, "todo");
                inputArr = new String[]{description};
                break;
            case "deadline":
                inputArr = input.split(" /by ");
                description = getDescription(inputArr, "deadline");
                String by = getTime(inputArr);
                inputArr = new String[]{description, by};
                break;
            case "event":
                inputArr = input.split(" /at ");
                description = getDescription(inputArr, "event");
                String at = getTime(inputArr);
                inputArr = new String[]{description, at};
                break;
            case "mark":
                inputArr = input.split(" ");
                // check valid
                checkMarkInput(inputArr);
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
            String[] inputArr = getParams("mark", input);
            String action = inputArr[0];
            String taskNum = inputArr[1];
            if (action.equals("mark")) {
                Task markedTask = tasks.markTask("mark", taskNum);
                System.out.printf("This is now done:\n%s\n", markedTask);
                System.out.println(lineBreak);
            } else if (action.equals("unmark")) {
                Task unmarkedTask = tasks.markTask("unmark", taskNum);
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
     * Checks for valid "mark" action inputs. Throws error if it is not valid.
     * @param inputArr String[], the user input String split into an array of String for processing
     *                 parameters for marking/unmarking a Task
     * @throws BernieException for invalid inputs
     */
    void checkMarkInput(String[] inputArr) throws BernieException {
        if (inputArr.length == 2) {
            try {
                String taskNumber = inputArr[1];
                Integer.parseInt(taskNumber);
                tasks.taskExists(taskNumber);
            } catch (NumberFormatException nfe) {
                throw new BernieException("That's not a task number! Put a number.");
            }
        } else {
            throw new BernieException("Wrong input. Type this: mark/unmark taskNumber");
        }
    }

    /**
     * Gets the description from the inputArr which contains parameters for creating Task
     * @param inputArr String[], parameters for creating Task, obtained from user input String.
     * @param taskType String
     * @return String description, for creating of Task
     * @throws BernieException for invalid descriptions such as empty or number description
     */
    String getDescription(String[] inputArr, String taskType) throws BernieException {
        String description = "";
        try {
            if (taskType.equals("todo")) {
                description = inputArr[1];
            } else {
                description = inputArr[0].split(taskType + " ")[1];
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
            String time = inputArr[1];
            return time;
        } catch (IndexOutOfBoundsException e) {
            throw new BernieException("Where's your time input?");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bernie bernie = new Bernie();
        bernie.greet();
        while (true) {
            String input = sc.nextLine();
            boolean end = bernie.respond(input);
            if (end) {
                break;
            }
        }
    }
}
