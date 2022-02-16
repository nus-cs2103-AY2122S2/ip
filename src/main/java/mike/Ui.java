package mike;

/**
 * Handles the output to be sent to the user from Mike.
 */
public class Ui {

    /**
     * Prints a string with line separators to make it
     * visually appealing
     *
     * @param str The string that is to be printed
     */
    public String printReply(String str) {
        String messageBorder = "~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String message = "Mike: " + str;
        return String.format("%s\n%s\n%s", messageBorder, message, messageBorder);
    }

    /**
     * Prints a message informing the user that their input does not contain any characters.
     */
    public String printNoCharactersMessage() {
        return printReply("Hey! You didn't enter any characters!");
    }
}
