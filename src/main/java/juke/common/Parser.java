package juke.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Helper for parsing strings to commands.
 */
public class Parser {
    private static final String COMMAND_DELIMITER = " -";
    private static final String WHITESPACE_DELIMITER = "\\s+";
    private static final String SPACE_DELIMITER = " ";
    private static final String FILE_DELIMITTER = ";";

    private static final String EMPTY_STRING = "";

    /**
     * Takes a string input and parses it into the command, parameters and arguments.
     * The first element of the returned list is a string array containing the command and default argument.
     * All following elements are string arrays that contain a parameter and its argument.
     * An empty string is returned on missing arguments.
     *
     * @param input Input string to parse.
     * @return List of string arrays with the above specifications.
     */
    public static ArrayList<String[]> parseCommand(String input) {
        String[] paramSplit = input.strip().split(COMMAND_DELIMITER);
        ArrayList<String[]> list = new ArrayList<>();
        boolean isInputEmpty = paramSplit.length == 0 || paramSplit[0].isBlank();
        if (isInputEmpty) {
            list.add(new String[]{EMPTY_STRING, EMPTY_STRING});
            return list;
        }
        for (String str : paramSplit) {
            String[] argSplit = str.strip().split(WHITESPACE_DELIMITER);
            String[] arr = new String[2];
            boolean hasArguments = argSplit.length != 0 && !argSplit[0].isBlank();
            if (!hasArguments) {
                continue;
            }
            arr[0] = argSplit[0];
            if (argSplit.length == 1) {
                arr[1] = EMPTY_STRING;
            } else {
                arr[1] = String.join(SPACE_DELIMITER, Arrays.copyOfRange(argSplit, 1, argSplit.length));
            }
            list.add(arr);
        }
        return list;
    }

    /**
     * Parses the file format into string components.
     *
     * @return An array list of string components.
     * @throws FileNotFoundException Throws if file not found.
     */
    public static ArrayList<String[]> parseFile(File file) throws FileNotFoundException {
        ArrayList<String[]> array = new ArrayList<>();
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            array.add(in.nextLine().strip().split(FILE_DELIMITTER));
        }
        in.close();
        return array;
    }

    /**
     * Writes data to the file.
     * Returns true if successful, false otherwise.
     *
     * @param array Array of string components to write.
     * @return Boolean result.
     */
    public static boolean writeFile(ArrayList<String[]> array, File file) {
        try {
            FileWriter out = new FileWriter(file);
            for (String[] args : array) {
                String str = String.join(FILE_DELIMITTER, args);
                out.write(str + System.lineSeparator());
            }
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
