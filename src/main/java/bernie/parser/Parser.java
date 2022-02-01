package bernie.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;
import bernie.tasks.TaskList;

/**
 * Parser Class helps to parse the inputs given by the user.
 * It has parseType method: a preliminary Parser to determine what type of input
 * has the user keyed in.
 * It also has getParams method which help to extract out relevant parts of the input
 * and return parameters needed accordingly to perform actions
 */
public class Parser {
    private final TaskList tasks;

    /**
     * Constructs a Parser class
     * @param tasks TaskList
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Serves as the preliminary Parser to determine what
     * type of input has the user keyed in. The possible inputs are:
     * list, bye, mark, empty (the ""), delete, and a general add input
     * which is determined in the program by inputs: todo, deadline, event
     * @params input String
     */
    public Type parseType(String input) {
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
        } else if (isType(Type.FIND, input)) {
            return Type.FIND;
        } else {
            return Type.ADD;
        }
    }

    /**
     * Verifies the user input is of which task type
     * @param taskType Type
     * @param input String
     * @return boolean to affirm if the input is of this task
     */
    public boolean isType(Type taskType, String input) {
        if (taskType.equals(Type.MARK)) {
            return input.indexOf("mark") == 0 || input.indexOf("unmark") == 0;
        } else if (taskType.equals(Type.EMPTY)) {
            return input.equals("");
        }
        return input.indexOf(taskType.name().toLowerCase()) == 0;
    }

    /**
     * Splits a user input into an array containing parameters for handling actions accordingly,
     * depending on taskType.
     * @param taskType Type
     * @param input String, a user input to be split into an array of parameters
     * @return String[] parsedArr.
     * For "todo": an array of 1, containing description.
     * For "deadline": an array of 2: [description, by]
     * For "event": an array of 2: [description, at]
     * For "mark": an array of 2: [action, taskNum], action is either mark/unmark
     * For "delete": an array of 1: [taskNum]
     * For "find": an array of 1: [description]
     */
    public String[] getParams(Type taskType, String input) throws InvalidArgumentException {
        String description;
        String[] parsedArr = null;
        switch (taskType) {
        case TODO:
            parsedArr = input.split("todo ");
            description = getDescription(parsedArr, Type.TODO);
            parsedArr = new String[]{ description };
            break;
        case DEADLINE:
            parsedArr = input.split(" /by ");
            description = getDescription(parsedArr, Type.DEADLINE);
            String by = getTime(parsedArr, Type.DEADLINE);
            parsedArr = new String[]{ description, by };
            break;
        case EVENT:
            parsedArr = input.split(" /at ");
            description = getDescription(parsedArr, Type.EVENT);
            String at = getTime(parsedArr, Type.EVENT);
            parsedArr = new String[]{ description, at };
            break;
        case MARK:
            parsedArr = input.split(" ");
            // check valid
            checkMarkOrDeleteInput(parsedArr, Type.MARK);
            checkValidMarkAction(parsedArr);
            break;
        case DELETE:
            parsedArr = input.split(" ");
            checkMarkOrDeleteInput(parsedArr, Type.DELETE);
            parsedArr = new String[]{ parsedArr[1] };
            break;
        case FIND:
            parsedArr = input.split("find ");
            description = getDescription(parsedArr, Type.FIND);
            parsedArr = new String[]{ description };
            break;
        default:
            break;
        }
        return parsedArr;
    }

    /**
     * Checks if user marks a task that is already marked, or unmarks a task that is already unmarked
     * @param parsedArr
     * @throws InvalidArgumentException if user attempts to marks a marked task or unmark an unmarked task
     */
    void checkValidMarkAction(String[] parsedArr) throws InvalidArgumentException {
        String action = parsedArr[0];
        int taskIndex = Integer.parseInt(parsedArr[1]) - 1;
        if (action.equals("mark")) {
            tasks.getTask(taskIndex).checkMark();
        } else if (action.equals("unmark")) {
            tasks.getTask(taskIndex).checkUnmark();
        }
    }

    /**
     * Checks for valid "mark" or "delete" action inputs. Throws error if it is not valid: wrong input format or
     * a task number is not given
     * @param parsedArr String[], the user input String split into an array of String for processing
     *                 parameters for the action
     * @throws InvalidArgumentException for invalid inputs
     */
    void checkMarkOrDeleteInput(String[] parsedArr, Type action) throws InvalidArgumentException {
        if (parsedArr.length == 2) {
            try {
                String taskNum = parsedArr[1];
                Integer.parseInt(taskNum);
                tasks.checkTaskExists(taskNum);
            } catch (NumberFormatException nfe) {
                throw new InvalidArgumentException("That's not a task number! Put a number.");
            }
        } else {
            if (action.equals(Type.MARK)) {
                throw new InvalidArgumentException("Wrong input. Type this: mark/unmark taskNumber");
            } else {
                throw new InvalidArgumentException("Wrong input. Type this: delete taskNumber");
            }
        }
    }

    /**
     * Gets the description from the parsedArr which contains parameters for creating Task
     * @param parsedArr String[], parameters for creating Task, obtained from user input String.
     * @param taskType Type
     * @return String description, for creating of Task
     * @throws InvalidArgumentException for invalid descriptions such as empty or number description
     */
    String getDescription(String[] parsedArr, Type taskType) throws InvalidArgumentException {
        String description;
        try {
            if (taskType.equals(Type.TODO) || taskType.equals(Type.FIND)) {
                description = parsedArr[1];
            } else {
                description = parsedArr[0].split(taskType.name().toLowerCase() + " ")[1];
            }
            checkDescriptionNotNumber(description);
            return description;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Where's the description?");
        }
    }

    /**
     * Checks if the description is a valid String: not a number.
     * @param description
     * @return boolean
     * @throws InvalidArgumentException if the description is a number
     */
    boolean checkDescriptionNotNumber(String description) throws InvalidArgumentException {
        try {
            Integer.parseInt(description);
            throw new InvalidArgumentException("Description can't be numbers? We need String!");
        } catch (NumberFormatException nfe) {
            return true;
        }
    }

    /**
     * Gets the time from the parsedArr which contains parameters for creating Task
     * @param parsedArr String[], parameters for creating Task, obtained from user input String.
     * @param taskType type, if it is DEADLINE we check if the input date format is correct.
     * @return String time, for creating of Task. It is of format: yyyy-mm-dd for converting
     * to LocalDate object by TaskList's addTask class
     * @throws InvalidArgumentException if there is no time input given or wrong date format
     */
    String getTime(String[] parsedArr, Type taskType) throws InvalidArgumentException {
        try {
            if (taskType.equals(Type.DEADLINE)) {
                LocalDate checkValid = LocalDate.parse(parsedArr[1]);
            }
            return parsedArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Where's your time input?");
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Please enter deadline date in: yyyy-mm-dd");
        }
    }
}
