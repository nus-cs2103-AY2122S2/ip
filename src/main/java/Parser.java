/**
 * Parser class serves as the preliminary Parser to determine what
 * type of input has the user keyed in. The possible inputs are:
 * list, bye, mark, empty (the ""), delete, and a general add input
 * which is determined in the program by inputs: todo, deadline, event
 */
public class Parser {
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

    // getParams
    // checkMarkOrDeleteInput
    // getDescription
    // checkDescriptionNotNumber
    // getTime
}

