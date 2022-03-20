package duke.util;

/**
 * Handles the processing of user input into format this program accepts
 */
public class Parser {
    private final String[] inputArray;
    private final String originalInput;

    /**
     * Takes in raw original user input and parses it to an array
     *
     * @param originalInput
     */
    public Parser(String originalInput) {
        this.originalInput = originalInput;
        inputArray = originalInput.split(" ");
    }


    /**
     * @return String Primary command e.g. "todo", "deadline", "delete"
     */
    public String getCommand() {
        return inputArray[0];
    }


    /**
     * @return String[] original string input split by " " into a String[]
     */
    public String[] getInputArray() {
        return inputArray;
    }


    /**
     * @return String orginal raw user input
     */
    public String getOriginalInput() {
        return originalInput;
    }

    public String[] getDeadlineInfo() {
        String metaInfo = originalInput.split("/by")[1];
        String strippedCommand = originalInput.substring(9);
        String description = strippedCommand.split("/")[0];

        return new String[]{removeLastChar(description), metaInfo};
    }

    public String[] getEventInfo() {
        String metaInfo = originalInput.split("/at")[1];
        String strippedCommand = originalInput.substring(6);
        String description = strippedCommand.split("/")[0];

        return new String[]{removeLastChar(description), metaInfo};
    }

    /**
     * Validates the given deadline command
     * @return validity status of the given command.
     */
    public boolean isValidDeadLineCommand() {
        boolean isEmptyMetaInfo = (originalInput.split("/by").length == 1);
        boolean isEmptyCommand = (inputArray.length <= 1);

        return !(isEmptyCommand || isEmptyMetaInfo);
    }

    /**
     * Validates the given event command
     * @return validity status of the given command.
     */
    public boolean isValidEventCommand() {
        boolean isEmptyMetaInfo = (originalInput.split("/at").length == 1);
        boolean isEmptyCommand = (inputArray.length <= 1);

        return !(isEmptyCommand || isEmptyMetaInfo);
    }

    /**
     * Validates the given description
     * @param description string
     * @return validity of the description string
     */
    public boolean isValidDescription(String description) {
        return !(description.isEmpty() || description.isBlank());
    }

    /**
     * Trims the last char of a string
     * @param target string to be trimmed
     * @return target string with the last char removed
     */
    public String removeLastChar(String target) {
        return (target == null || target.length() == 0)
                ? null
                : (target.substring(0, target.length() - 1));
    }
}
