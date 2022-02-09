package duke;

/**
 * This class is a child of the Parser class. It parse with user inputs.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class InputParser extends Parser {
    /**
     * Parses user input.
     *
     * @param str the string entered by the user.
     */
    public InputParser(String str) {
        String[] strArray = str.split(" ");
        command = strArray[0];
        int index = 1;
        // Getting the description
        while (index < strArray.length) {
            if (!strArray[index].equals("/by") && !strArray[index].equals("/at")) {
                if (desc == null) {
                    desc = strArray[index];
                } else {
                    desc = desc + " " + strArray[index];
                }
                index++;
            } else {
                break;
            }
        }
        index++; // Skipping "/at" and "/by" string if encountered
        // Getting the date
        while (index < strArray.length) {
            if (date == null) {
                date = strArray[index];
            } else {
                date = date + " " + strArray[index];
            }
            index++;
        }
    }
}
