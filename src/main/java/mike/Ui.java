package mike;

public class Ui {

    /**
     * Prints a greeting from "Mike" with an accompanying text banner.
     */
    public void printGreeting() {
        String textBanner = ""
                + "  _     _   _\n"
                + " | |   (_) (_)\n"
                + " | |__  _   _    __ _ _ __ ___\n"
                + " | '_ \\| | | |  / _` | '_ ` _ \\\n"
                + " | | | | | | | | (_| | | | | | |\n"
                + " |_| |_|_| |_|_ \\__,_|_| |_| |_|\n"
                + "           (_) |\n"
                + "  _ __ ___  _| | _____\n"
                + " | '_ ` _ \\| | |/ / _ \\\n"
                + " | | | | | | |   <  __/\n"
                + " |_| |_| |_|_|_|\\_\\___|\n";
        System.out.println("Welcome!\n" + textBanner);
    }

    /**
     * Prints instructions to guide a user on how to use "Mike".
     */
    public void printStartingInstruction() {
        String tip = "**Tip 1: type bye to end conversation**\n"
                + "**Tip 2: enter dates in the format yyyy-mm-dd**";
        System.out.println(tip);
    }

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
     * Prints instructions to ask a user for the next command.
     */
    public void printNextCommandInstruction() {
        System.out.println("Enter next command:");
    }

    /**
     * Prints a goodbye message from "Mike".
     */
    public void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye and see you again :)";
        printReply(goodbyeMessage);
    }

    /**
     * Prints a message informing the user that their input does not contain any characters.
     */
    public String printNoCharactersMessage() {
        return printReply("Hey! You didn't enter any characters!");
    }
}
