package juke.common;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helper for parsing strings to commands.
 */
public class Parser {
    
    /**
     * Takes a string input and parses it into the command, parameters and arguments.
     * The first element of the returned list is a string array containing the command and default argument.
     * All following elements are string arrays that contain a parameter and its argument.
     * An empty string is returned on missing arguments.
     *
     * @param input Input string to parse.
     * @return List of string arrays with the above specifications.
     */
    public static ArrayList<String[]> parseInput(String input) {
        String[] paramSplit = input.strip().split(" -");
        ArrayList<String[]> list = new ArrayList<>();
        if (paramSplit.length == 0 || paramSplit[0].isBlank()) {
            list.add(new String[]{"", ""});
        } else {
            for (String str : paramSplit) {
                String[] argSplit = str.strip().split("\\s+");
                String[] arr = new String[2];
                if (argSplit.length != 0 && !argSplit[0].isBlank()) {
                    arr[0] = argSplit[0];
                    if (argSplit.length == 1) {
                        arr[1] = "";
                    } else {
                        arr[1] = String.join(" ", Arrays.copyOfRange(argSplit, 1, argSplit.length));
                    }
                }
                list.add(arr);
            }
        }
        return list;
    }
}
