package duke;

import java.util.Arrays;
import java.util.List;

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
        List<String> strArray = Arrays.asList(str.split(" "));
        command = strArray.get(0);
        int dateIndex = -1;
        if (dateIndex == -1) {
            dateIndex = strArray.indexOf("/at");
        }
        if (dateIndex == -1) {
            dateIndex = strArray.indexOf("/by");
        }
        if (dateIndex != -1) {
            desc = constructStringByPosition(strArray, 1, dateIndex);
            date = constructStringByPosition(strArray, dateIndex + 1, strArray.size());
        } else {
            desc = constructStringByPosition(strArray, 1, strArray.size());
        }
    }

    /**
     * This method retrieves a given string (based on its start
     * and end position) from an string array.
     *
     * @param list the list that contains the string.
     * @param start the starting position of the string.
     * @param end the ending position of the string.
     * @return the string construct from this method.
     */
    public String constructStringByPosition(List<String> list, int start, int end) {
        String str = null;
        for (int i = start; i < end; i++) {
            if (str == null) {
                str = list.get(i);
            } else {
                str = str + " " + list.get(i);
            }
        }
        return str;
    }
}
