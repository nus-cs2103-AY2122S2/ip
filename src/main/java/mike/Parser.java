package mike;

public class Parser {
    private final String userInput;
    private final String[] splitString;

    public Parser(String userInput) {
        this.userInput = userInput.trim(); //Parser trims userInput automatically
        this.splitString = this.userInput.split(" ");
    }

    public String getUserInput() {
        return userInput;
    }

    public String getCommand() {
        return this.splitString[0];
    }

    public int getIndex() {
        return Integer.parseInt(this.splitString[1]);
    }

    public boolean isBye() {
        return getUserInput().equals("bye");
    }

    public boolean isBlank() {
        return getUserInput().isEmpty();
    }

    /**
     * Trims the "command" portion out from the input string and returns the remainder of the string.
     * This is a helper function for the processInput method.
     *
     * @return The input string from the user with the "command" portion removed.
     */
    public String removeCommandFromString() {
        int indexOfFirstSpace = this.userInput.indexOf(" ");
        int indexOfTaskParameters = indexOfFirstSpace + 1;
        return this.userInput.substring(indexOfTaskParameters);
    }

    String getTodoName() {
        return removeCommandFromString();
    }

    String getDeadlineName() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/by");
        String name = userInputCommandRemoved.substring(0, indexOfBy - 1);
        return name;
    }

    String getDeadlineDate() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/by");
        int indexOfEndDate = indexOfBy + 4;
        String endDate = userInputCommandRemoved.substring(indexOfEndDate);
        return endDate;
    }

    String getEventName() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/at");
        String name = userInputCommandRemoved.substring(0, indexOfBy - 1);
        return name;
    }

    String getEventDate() {
        String userInputCommandRemoved = removeCommandFromString();
        int indexOfBy = userInputCommandRemoved.indexOf("/at");
        int indexOfEndDate = indexOfBy + 4;
        String date = userInputCommandRemoved.substring(indexOfEndDate);
        return date;
    }
}
