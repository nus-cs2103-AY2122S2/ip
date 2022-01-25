import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser Class helps to parse the inputs given by the user.
 * It has parseType method: a preliminary Parser to determine what type of input
 * has the user keyed in.
 * It also has getParams method which help to extract out relevant parts of the input
 * and return parameters needed accordingly for Bernie to perform actions
 */
public class Parser {
    static TaskList tasks;

    /**
     * Constructs a Parser class
     * @param tasks
     */
    Parser(TaskList tasks) {
        this.tasks = tasks;
    }
    /**
     * Serves as the preliminary Parser to determine what
     * type of input has the user keyed in. The possible inputs are:
     * list, bye, mark, empty (the ""), delete, and a general add input
     * which is determined in the program by inputs: todo, deadline, event
     * @params input String
     */
    static Bernie.Type parseType(String input) {
        if (isType(Bernie.Type.LIST, input)) {
            return Bernie.Type.LIST;
        } else if (isType(Bernie.Type.BYE, input)) {
            return Bernie.Type.BYE;
        } else if (isType(Bernie.Type.MARK, input)) {
            return Bernie.Type.MARK;
        } else if (isType(Bernie.Type.EMPTY, input)) {
            return Bernie.Type.EMPTY;
        } else if (isType(Bernie.Type.DELETE, input)) {
            return Bernie.Type.DELETE;
        } else {
            return Bernie.Type.ADD;
        }
    }

    /**
     * Verifies the user input is of which task
     * @param taskType Type
     * @param input String
     * @return boolean to affirm if the input is of this task
     */
    static boolean isType(Bernie.Type taskType, String input) {
        if (taskType.equals(Bernie.Type.MARK)) {
            return input.indexOf("mark") == 0 || input.indexOf("unmark") == 0;
        } else if (taskType.equals(Bernie.Type.EMPTY)) {
            return input.equals("");
        }
        return input.indexOf(taskType.name().toLowerCase()) == 0;
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
    static String[] getParams(Bernie.Type taskType, String input) throws BernieException {
        String description;
        String[] inputArr = null;
        switch (taskType) {
        case TODO:
            inputArr = input.split("todo ");
            description = getDescription(inputArr, Bernie.Type.TODO);
            inputArr = new String[]{description};
            break;
        case DEADLINE:
            inputArr = input.split(" /by ");
            description = getDescription(inputArr, Bernie.Type.DEADLINE);
            String by = getTime(inputArr, Bernie.Type.DEADLINE);
            inputArr = new String[]{description, by};
            break;
        case EVENT:
            inputArr = input.split(" /at ");
            description = getDescription(inputArr, Bernie.Type.EVENT);
            String at = getTime(inputArr, Bernie.Type.EVENT);
            inputArr = new String[]{description, at};
            break;
        case MARK:
            inputArr = input.split(" ");
            // check valid
            checkMarkOrDeleteInput(inputArr, Bernie.Type.MARK);
            break;
        case DELETE:
            inputArr = input.split(" ");
            checkMarkOrDeleteInput(inputArr, Bernie.Type.DELETE);
            inputArr = new String[]{inputArr[1]};
            break;
        default:
            break;
        }
        return inputArr;
    }

    /**
     * Checks for valid "mark" or "delete" action inputs. Throws error if it is not valid.
     * @param inputArr String[], the user input String split into an array of String for processing
     *                 parameters for the action
     * @throws BernieException for invalid inputs
     */
    static void checkMarkOrDeleteInput(String[] inputArr, Bernie.Type action) throws BernieException {
        if (inputArr.length == 2) {
            try {
                String taskNum = inputArr[1];
                Integer.parseInt(taskNum);
                tasks.taskExists(taskNum);
            } catch (NumberFormatException nfe) {
                throw new BernieException("That's not a task number! Put a number.");
            }
        } else {
            if (action.equals(Bernie.Type.MARK)) {
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
    static String getDescription(String[] inputArr, Bernie.Type taskType) throws BernieException {
        String description;
        try {
            if (taskType.equals(Bernie.Type.TODO)) {
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

    static boolean checkDescriptionNotNumber(String description) throws BernieException {
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
    static String getTime(String[] inputArr, Bernie.Type taskType) throws BernieException {
        try {
            if (taskType.equals(Bernie.Type.DEADLINE)) {
                LocalDate checkValid = LocalDate.parse(inputArr[1]);
            }
            return inputArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new BernieException("Where's your time input?");
        } catch (DateTimeParseException e) {
            throw new BernieException("Please enter deadline date in: yyyy-mm-dd");
        }
    }
}

