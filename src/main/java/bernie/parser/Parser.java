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

    /**
     * Takes in the line that is read by the FileReader and parses the line to create the arguments required
     * to create the respective tasks upon loading the file on start up. This is assuming that the file does exist.
     * @param line String, the line of the text file that contains the text for a singular task.
     *             Format is:
     *             Todo objects: taskNumber. [T][ ] borrow book
     *             Deadline objects: taskNumber. [D][ ] buy equipment (by: Feb 1 2022)
     *             Event objects: taskNumber. [E][ ] project meeting (at: Mon 2-4pm)
     * @return String[][], an array of arguments required create the object:
     *          [ [taskArgs], [ type ], [ isDone ] ]
     */
    public String[][] parseFileLine(String line) {
        String[] splitArr = line.split("\\[");
        // Task type
        String type = parseTaskInLine(splitArr);
        // done or not
        String isDone = isDoneStatusInLine(splitArr);
        // Task args
        String[] taskArgs = null;
        switch (type) {
        case "todo":
            taskArgs = parseToDoInLine(splitArr);
            break;
        case "deadline":
            taskArgs = parseDeadlineInLine(splitArr);
            break;
        case "event":
            taskArgs = parseEventInLine(splitArr);
            break;
        default:
            break;
        }
        return new String[][]{ taskArgs, new String[]{ type }, new String[]{ isDone } };
    }

    /**
     * Takes in the result of the split array by open brackets and
     * parses out the task type
     * @param splitArr
     * @return String, the task type
     */
    String parseTaskInLine(String[] splitArr) {
        String letter = splitArr[1].substring(0, 1);
        String type = null;
        switch (letter) {
        case "T":
            type = "todo";
            break;
        case "D":
            type = "deadline";
            break;
        case "E":
            type = "event";
            break;
        default:
            break;
        }
        return type;
    }

    /**
     * Parses the splitArr to get the done status of the task
     * @param splitArr, array split by [
     * @return String, indicating the done status: " " or "X"
     */
    String isDoneStatusInLine(String[] splitArr) {
        return splitArr[2].substring(0, 1);
    }

    /**
     * Parses the event in the line inside the text file to return its
     * description and timing at, in a String[]
     * @param splitArr String[], array split by [
     * @return String[], args for creating event
     */
    String[] parseEventInLine(String[] splitArr) {
        String parsingPart = splitArr[2];
        String[] parsedArr = parsingPart.split(" \\(at: ");
        String description = parsedArr[0].split(".] ")[1];
        String at = removeEndBracket(parsedArr[1]);
        return new String[]{ description, at };
    }

    /**
     * Parses the deadline in the line inside the text file to return its
     * description and timing by, in a String[]
     * @param splitArr String[], array split by [
     * @return String[], args for creating deadline
     */
    String[] parseDeadlineInLine(String[] splitArr) {
        String parsingPart = splitArr[2];
        String[] parsedArr = parsingPart.split(" \\(by: ");
        String description = parsedArr[0].split(".] ")[1];
        String by = removeEndBracket(parsedArr[1]);
        String byInLocalDateFormat = changeDateFormat(by);
        return new String[]{ description, byInLocalDateFormat };
    }

    /**
     * Parses the ToDo in the line inside the text file to return its
     * description, in a String[]
     * @param splitArr String[], array split by [
     * @return String[], args for creating todo
     */
    String[] parseToDoInLine(String[] splitArr) {
        String parsingPart = splitArr[2];
        String description = parsingPart.split(".] ")[1];
        return new String[]{ description };
    }

    /**
     * Takes in a String that is partially parsed of form ....)
     * @param str String
     * @return String, without end bracket
     */
    String removeEndBracket(String str) {
        return str.substring(0, str.length() - 1);
    }

    /**
     * Takes in the date in the line of text file and converts it to
     * yyyy-mm-dd format so we can create it as LocalDate object
     * @param date, in format mmm dd yyyy
     * @return date in yyyy-mm-dd format
     */
    String changeDateFormat(String date) {
        String[] splitArr = date.split(" ");
        String year = splitArr[2];
        String day = splitArr[1];
        String month = splitArr[0];
        String numericMonth = null;
        switch (month) {
        case "Jan":
            numericMonth = "01";
            break;
        case "Feb":
            numericMonth = "02";
            break;
        case "Mar":
            numericMonth = "03";
            break;
        case "Apr":
            numericMonth = "04";
            break;
        case "May":
            numericMonth = "05";
            break;
        case "Jun":
            numericMonth = "06";
            break;
        case "Jul":
            numericMonth = "07";
            break;
        case "Aug":
            numericMonth = "08";
            break;
        case "Sep":
            numericMonth = "09";
            break;
        case "Oct":
            numericMonth = "10";
            break;
        case "Nov":
            numericMonth = "11";
            break;
        case "Dec":
            numericMonth = "12";
            break;
        default:
            break;
        }
        return String.format("%s-%s-%s", year, numericMonth, day);
    }
}
