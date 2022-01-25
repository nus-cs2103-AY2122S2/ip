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
    static Type parseType(String input) {
        if (isType(Type.LIST, input)) {
            return Type.LIST;
        } else if (isType(Type.BYE, input)) {
            return Type.BYE;
        } else if (isType(Type.MARK, input)) {
            return Type.MARK;
        } else if (isType(Type.EMPTY, input)) {
            return Type.EMPTY;
        } else if (isType(Type.DELETE, input)) {
            return Type.DELETE;
        } else {
            return Type.ADD;
        }
    }

    /**
     * Verifies the user input is of which task
     * @param taskType Type
     * @param input String
     * @return boolean to affirm if the input is of this task
     */
    static boolean isType(Type taskType, String input) {
        if (taskType.equals(Type.MARK)) {
            return input.indexOf("mark") == 0 || input.indexOf("unmark") == 0;
        } else if (taskType.equals(Type.EMPTY)) {
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
    static String[] getParams(Type taskType, String input) throws BernieException {
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
     * Checks for valid "mark" or "delete" action inputs. Throws error if it is not valid.
     * @param inputArr String[], the user input String split into an array of String for processing
     *                 parameters for the action
     * @throws BernieException for invalid inputs
     */
    static void checkMarkOrDeleteInput(String[] inputArr, Type action) throws BernieException {
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
    static String getDescription(String[] inputArr, Type taskType) throws BernieException {
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
    static String getTime(String[] inputArr, Type taskType) throws BernieException {
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
}

