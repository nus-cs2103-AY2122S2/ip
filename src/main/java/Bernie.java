import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Bernie the bot that is the driver for the responses to the user.
 * Internally, Bernie has TaskList, which contain the Tasks that a user inputs
 */

public class Bernie {
    TaskList tasks;
    Ui ui;
    Storage storage;
    Parser parser;

    /**
     * Different Type of tasks: notable types are EMPTY, when the user inputs nothing: ""
     * ADD: refers to when Type is to be added: TODO, DEADLINE, EVENT
     */
    enum Type {
        LIST,
        BYE,
        EMPTY,
        ADD,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE
    }

    /**
     * Constructs a new Bot containing TaskList and greets the user
     */
    Bernie() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        ui.greet();
    }

    Storage getStorage() {
        return storage;
    }

    /**
     * Displays to the user a message according to their input. Actions are performed according
     * to the input. Exceptions are caught and printed out for the user.
     * @param input String, user input
     * @return boolean value, indicating if the program will end or not.
     */
    boolean respond(String input) {
        Bernie.Type type = Parser.parseType(input);
        try {
            switch (type) {
            case LIST:
                ui.showListTasksMsg(tasks);
                break;
            case BYE:
                ui.showLeaveMsg();
                break;
            case MARK:
                mark(input);
                break;
            case EMPTY:
                throw new BernieException("Say something???");
            case DELETE:
                delete(input);
                break;
            case ADD:
                add(input);
                break;
            default:
                break;
            }
        } catch (BernieException e) {
            ui.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMsg(e.getMessage());
        } finally {
            return input.equals("bye");
        }
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
        if (Parser.isType(Type.TODO, input)) {
            String[] inputArr = getParams(Type.TODO, input);
            newTask = tasks.addTask(inputArr, "todo");
        } else if (Parser.isType(Type.DEADLINE, input)) {
            String[] inputArr = getParams(Type.DEADLINE, input);
            newTask = tasks.addTask(inputArr, "deadline");
        } else if (Parser.isType(Type.EVENT, input)) {
            String[] inputArr = getParams(Type.EVENT, input);
            newTask = tasks.addTask(inputArr, "event");
        } else {
            throw new BernieException("Not a valid type of task!");
        }
        storage.saveTasks(tasks);
        ui.showAddedMsg(newTask, tasks.numTasksLeft());
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
            storage.saveTasks(tasks);
            ui.showDeleteMsg(deletedTask, tasks.numTasksLeft());
        } catch (BernieException e){
            ui.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMsg(e.getMessage());
        }
    }
    /**
     * Splits a user input into an array containing parameters for creating Task accordingly,
     * depending on taskType.
     * @param taskType Type
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
                String by = getTime(inputArr, Type.DEADLINE);
                inputArr = new String[]{description, by};
                break;
            case EVENT:
                inputArr = input.split(" /at ");
                description = getDescription(inputArr, Type.EVENT);
                String at = getTime(inputArr, Type.EVENT);
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
                ui.showDoneMsg(markedTask);
            } else if (action.equals("unmark")) {
                Task unmarkedTask = tasks.markTask(Type.UNMARK, taskNum);
                ui.showUndoneMsg(unmarkedTask);
            }
        } catch (BernieException e) {
            ui.showErrorMsg(e.getMessage());
        } catch (Exception e) {
            ui.showErrorMsg(e.getMessage());
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
     * @param taskType Type
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
     * @param taskType type, if it is DEADLINE we check if the input date format is correct.
     * @return String time, for creating of Task. It is of format: yyyy-mm-dd for converting
     * to LocalDate object by TaskList's addTask class
     * @throws BernieException if there is no time input given
     */
    String getTime(String[] inputArr, Type taskType) throws BernieException {
        try {
            if (taskType.equals(Type.DEADLINE)) {
                LocalDate checkValid = LocalDate.parse(inputArr[1]);
            }
            return inputArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new BernieException("Where's your time input?");
        } catch (DateTimeParseException e) {
            throw new BernieException("Please enter deadline date in: yyyy-mm-dd");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bernie bernie = new Bernie();
        Storage storage = bernie.getStorage();
        storage.loadTasks();
        while (true) {
            String input = sc.nextLine();
            boolean end = bernie.respond(input);
            if (end) {
                break;
            }
        }
    }
}
