package arthur.exceptions;

/**
 * Handles the custom exception for arthur.Arthur.
 */
public class ArthurException {
    private static final String DATE_TIME_NOT_PRESENT_MESSAGE = "Please state the date and/or time";

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
        String[] instruction = e.split(" ", 2);
        assert instruction.length >= 1 : "Invalid command input";
        // Check for valid instructions
        switch (instruction[0]) {
        case "bye":
        case "find":
        case "list":
        case "mark":
        case "unmark":
        case "reminder":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
            break;
        default:
            throw new InvalidInstructionException();
        }

        boolean isSingleInstruction = instruction[0].equals("list")
                || instruction[0].equals("bye") || instruction[0].equals("find")
                || instruction[0].equals("reminder");
        // Check if instructions come with description
        if (!isSingleInstruction) {
            if (instruction.length < 2 || instruction[1].trim().isEmpty()) {
                throw new EmptyDescriptionException(instruction[0]);
            } else if (instruction[0].equals("deadline") && !instruction[1].contains("/by")) {
                throw new InvalidInstructionException(DATE_TIME_NOT_PRESENT_MESSAGE);
            } else if (instruction[0].equals("event") && !instruction[1].contains("/at")) {
                throw new InvalidInstructionException(DATE_TIME_NOT_PRESENT_MESSAGE);
            }
        }
    }
}
