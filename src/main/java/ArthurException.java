/**
 * Handles the custom exception for Arthur.
 */
public class ArthurException {

    /**
     * Checks given user input for instruction validity.
     * Instruction valid if it is one of the supported instruction and
     * has description.
     * @param e The user input to check
     * @throws EmptyDescriptionException When the input has no description
     * @throws InvalidInstructionException When the input is not a valid/supported instruction
     */
    public static void checkException(String e)
            throws EmptyDescriptionException, InvalidInstructionException {
        String[] temp = e.split(" ", 2);

        // Check for valid instructions
        switch (temp[0]) {
        case "list":
        case "mark":
        case "unmark":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
            break;
        default:
            throw new InvalidInstructionException();
        }

        // Check if instructions come with description
        if (!temp[0].equals("list")) {
            if (temp.length < 2 || temp[1].trim().isEmpty()) {
                throw new EmptyDescriptionException(temp[0]);
            }
        }
    }
}
