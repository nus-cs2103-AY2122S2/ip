package angela.exception;

import java.util.ArrayList;

/**
 * Prints error message when user input the wrong command
 */
public class BotException {

    /**
     * Prints error when the bot command does not follow with description
     *
     * @param description The input command type
     * @return The reply string of Angela
     */
    public ArrayList<String> printEmptyDescriptionError(String description) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Sorry I think you have forgotten something? The description of a " + "/"
                + description.toUpperCase() + " cannot be empty.");
        return wordArray;
    }

    /**
     * Prints error when the input command does not follow with a number
     * eg: 'todo abc' instead of 'todo 2'
     *
     * @param description The input command type
     * @return The reply string of Angela
     */
    public ArrayList<String> printNotNumericError(String description) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Sorry I think you have a typo :< \n"
                + " The description of a " + "/" + description.toUpperCase() + " must be a number.");
        return wordArray;
    }

    /**
     * Prints error when the input command does not belong to any known command
     *
     * @return The reply string of Angela
     */
    public ArrayList<String> printWrongSyntaxError() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Sorry, but I don't know what that means :-(");
        return wordArray;
    }

    /**
     * Prints error when the input date does not appear in the database
     *
     * @return The reply string of Angela
     */
    public ArrayList<String> printDateNotFoundError() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" OOPS!!! You don't have any deadlines/events on this day :-(");
        return wordArray;
    }
}
