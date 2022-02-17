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
        wordArray.add(" Sorry I think you have forgotten something? ");
        wordArray.add(" The description of " + "~" + description.toUpperCase() + " cannot be empty.");
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
        wordArray.add(" Sorry I think you have a typo :<");
        wordArray.add(" The description of " + "~" + description.toUpperCase() + " must be a number.");
        return wordArray;
    }

    /**
     * Prints error when the input command does not belong to any known command
     *
     * @return The reply string of Angela
     */
    public ArrayList<String> printWrongSyntaxError() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Sorry, but I don't know what you want?");
        return wordArray;
    }

    /**
     * Prints error when the input date does not appear in the database
     *
     * @return The reply string of Angela
     */
    public ArrayList<String> printDateNotFoundError() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Sorry, you don't have any deadlines or events on this day. ");
        wordArray.add(" Try another day instead");
        return wordArray;
    }
}
