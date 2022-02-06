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
            boolean inputStartsWithMark = input.indexOf("mark") == 0;
            boolean inputStartsWithUnmark = input.indexOf("unmark") == 0;
            return inputStartsWithMark || inputStartsWithUnmark;
        } else if (taskType.equals(Type.EMPTY)) {
            boolean isEmptyInput = input.equals("");
            return isEmptyInput;
        }
        boolean inputStartsWithType = input.indexOf(taskType.name().toLowerCase()) == 0;
        return inputStartsWithType;
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
            final int TASK_NUM_INDEX = 1;
            parsedArr = new String[]{ parsedArr[TASK_NUM_INDEX] };
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
        final int ACTION_INDEX = 0;
        final int TASK_NUM_INDEX = 1;
        String action = parsedArr[ACTION_INDEX];
        int taskIndex = Integer.parseInt(parsedArr[TASK_NUM_INDEX]) - 1;
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
        boolean isFullInput = parsedArr.length == 2;
        if (isFullInput) {
            try {
                final int TASK_NUM_INDEX = 1;
                String taskNum = parsedArr[TASK_NUM_INDEX];
                Integer.parseInt(taskNum);
                tasks.checkTaskExists(taskNum);
            } catch (NumberFormatException nfe) {
                String notNumberMsg = "That's not a task number! Put a number.";
                throw new InvalidArgumentException(notNumberMsg);
            }
        } else {
            if (action.equals(Type.MARK)) {
                String wrongMarkFormatMsg = "Wrong input. Type this: mark/unmark taskNumber";
                throw new InvalidArgumentException(wrongMarkFormatMsg);
            } else {
                String wrongDeleteFormatMsg = "Wrong input. Type this: delete taskNumber";
                throw new InvalidArgumentException(wrongDeleteFormatMsg);
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
                final int DESCRIPTION_INDEX = 1;
                description = parsedArr[DESCRIPTION_INDEX];
            } else {
                final int DESCRIPTION_PART_INDEX = 0;
                description = extractDescriptionFromType(taskType, parsedArr[DESCRIPTION_PART_INDEX]);
            }
            checkDescriptionNotNumber(description);
            return description;
        } catch (IndexOutOfBoundsException e) {
            String emptyDescriptionMsg = "Where's the description?";
            throw new InvalidArgumentException(emptyDescriptionMsg);
        }
    }

    /**
     * For extracting description from deadline and event type
     * @param taskType Type, either deadline or event
     * @param parsedPart String of the first half of the parsed part: e.g "deadline do dishes" from
     *                   "deadline do dishes /by ..."
     * @return String, the description
     */
    private String extractDescriptionFromType(Type taskType, String parsedPart) {
        final int DESCRIPTION_INDEX = 1;
        final String typePart = taskType.name().toLowerCase() + " ";
        return parsedPart.split(typePart)[DESCRIPTION_INDEX];
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
            String nonStringDescriptionMsg = "Description can't be numbers? We need String!";
            throw new InvalidArgumentException(nonStringDescriptionMsg);
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
                final int TIME_INDEX = 1;
                LocalDate checkValid = LocalDate.parse(parsedArr[TIME_INDEX]);
            }
            return parsedArr[1];
        } catch (IndexOutOfBoundsException e) {
            String missingTimeMsg = "Where's your time input?";
            throw new InvalidArgumentException(missingTimeMsg);
        } catch (DateTimeParseException e) {
            String wrongDateFormatMsg = "Please enter deadline date in: yyyy-mm-dd";
            throw new InvalidArgumentException(wrongDateFormatMsg);
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
        String[][] initTaskArgs = new String[][]{ taskArgs, new String[]{ type }, new String[]{ isDone } };
        return initTaskArgs;
    }

    /**
     * Takes in the result of the split array by open brackets and
     * parses out the task type
     * @param splitArr
     * @return String, the task type
     */
    String parseTaskInLine(String[] splitArr) {
        final int LETTER_PART_INDEX = 1;
        String letter = extractLetter(splitArr[LETTER_PART_INDEX]);
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
     * Extracts the letter of the type of tasks from the rest of the splitArr String
     * @param splitArr String, after splitting the letter part out
     * @return String, a letter representing the type either: "T", "D", "E"
     */
    private String extractLetter(String splitArr) {
        return splitArr.substring(0, 1);
    }

    /**
     * Parses the splitArr to get the done status of the task
     * @param splitArr String[], array split by an open square bracket
     * @return String, indicating the done status by a whitespace (indicatingg not done)
     *          or X (indicates done)
     */
    String isDoneStatusInLine(String[] splitArr) {
        final int DONE_STATUS_PART_INDEX = 2;
        return extractDoneStatus(splitArr[DONE_STATUS_PART_INDEX]);
    }

    /**
     * Extracts the done status from the rest of the splitArr String
     * @param splitArr String, after splitting the done status part out
     * @return String, either "X" indicating done or " " not done
     */
    private String extractDoneStatus(String splitArr) {
        return splitArr.substring(0, 1);
    }

    /**
     * Parses the event in the line inside the text file to return its
     * description and timing at, in a String[]
     * @param splitArr String[], array split by [
     * @return String[], args for creating event
     */
    String[] parseEventInLine(String[] splitArr) {
        final int PARSING_PART_INDEX = 2;
        final String SPLIT_SEP_AT = " \\(at: ";
        final String SPLIT_SEP_DESCRIPTION = ".] ";
        final int DESCRIPTION_PART_INDEX = 0;
        final int DESCRIPTION_INDEX = 1;
        final int TIMING_AT_INDEX = 1;

        String parsingPart = splitArr[PARSING_PART_INDEX];
        String[] parsedArr = parsingPart.split(SPLIT_SEP_AT);
        String description = parsedArr[DESCRIPTION_PART_INDEX].split(SPLIT_SEP_DESCRIPTION)[DESCRIPTION_INDEX];
        String at = removeEndBracket(parsedArr[TIMING_AT_INDEX]);
        return new String[]{ description, at };
    }

    /**
     * Parses the deadline in the line inside the text file to return its
     * description and timing by, in a String[]
     * @param splitArr String[], array split by [
     * @return String[], args for creating deadline
     */
    String[] parseDeadlineInLine(String[] splitArr) {
        final int PARSING_PART_INDEX = 2;
        final String SPLIT_SEP_AT = " \\(by: ";
        final String SPLIT_SEP_DESCRIPTION = ".] ";
        final int DESCRIPTION_PART_INDEX = 0;
        final int DESCRIPTION_INDEX = 1;
        final int DEADLINE_INDEX = 1;

        String parsingPart = splitArr[PARSING_PART_INDEX];
        String[] parsedArr = parsingPart.split(SPLIT_SEP_AT);
        String description = parsedArr[DESCRIPTION_PART_INDEX].split(SPLIT_SEP_DESCRIPTION)[DESCRIPTION_INDEX];
        String by = removeEndBracket(parsedArr[DEADLINE_INDEX]);
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
        final int PARSING_PART_INDEX = 2;
        final String SPLIT_SEP_DESCRIPTION = ".] ";
        final int DESCRIPTION_INDEX = 1;
        String parsingPart = splitArr[PARSING_PART_INDEX];
        String description = parsingPart.split(SPLIT_SEP_DESCRIPTION)[DESCRIPTION_INDEX];
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
     * @param date String, in format mmm dd yyyy
     * @return date in yyyy-mm-dd format
     */
    String changeDateFormat(String date) {
        final int MONTH_INDEX = 0;
        final int DAY_INDEX = 1;
        final int YEAR_INDEX = 2;
        final String WHITESPACE = " ";
        String[] splitArr = date.split(WHITESPACE);
        String year = splitArr[YEAR_INDEX];
        String day = splitArr[DAY_INDEX];
        String month = splitArr[MONTH_INDEX];
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
