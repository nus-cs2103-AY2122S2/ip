package arthur.exceptions;

/**
 * Handles the custom exception for arthur.Arthur.
 */
public class ArthurException {
    private static final String DATE_TIME_NOT_PRESENT_MESSAGE = "Please state the date and/or time";
    private static final String ASSERT_INSTRUCTION_ERROR_MESSAGE = "Invalid command input";
    private static final String DEADLINE_DESC_REQ = "/by";
    private static final String EVENT_DESC_REQ = "/at";

    /**
     * Checks given user input for instruction validity.
     * Instruction valid if it is one of the supported instruction and
     * has description.
     *
     * @param e The user input to check.
     * @throws EmptyDescriptionException When the input has no description.
     * @throws InvalidInstructionException When the input is not a valid/supported instruction.
     */
    public static void checkException(String e)
            throws EmptyDescriptionException, InvalidInstructionException {
        String[] instruction = e.split(" ", 2);
        assert instruction.length >= 1 : ASSERT_INSTRUCTION_ERROR_MESSAGE;
        String command = instruction[0];
        // Check for valid instructions
        switch (command) {
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

        checkDescription(instruction);
    }

    /**
     * Checks if input instruction comes with valid description.
     *
     * @param instruction The user input instruction.
     * @throws EmptyDescriptionException When the input has no description.
     * @throws InvalidInstructionException When the input is not a valid/supported instruction.
     */
    private static void checkDescription(String[] instruction)
            throws EmptyDescriptionException, InvalidInstructionException {
        String command = instruction[0];
        // Desc = instruction[1]
        boolean isSingleInstruction = command.equals("list") || command.equals("bye")
                || command.equals("find") || command.equals("reminder");
        if (!isSingleInstruction) {
            // Check if instructions come with description
            checker(instruction, command);
        }
    }

    private static void checker(String[] instruction, String command)
            throws EmptyDescriptionException, InvalidInstructionException {
        if (instruction.length < 2 || instruction[1].trim().isEmpty()) {
            throw new EmptyDescriptionException(command);
        } else if (command.equals("deadline") && !instruction[1].contains(DEADLINE_DESC_REQ)) {
            throw new InvalidInstructionException(DATE_TIME_NOT_PRESENT_MESSAGE);
        } else if (command.equals("event") && !instruction[1].contains(EVENT_DESC_REQ)) {
            throw new InvalidInstructionException(DATE_TIME_NOT_PRESENT_MESSAGE);
        }
    }
}
