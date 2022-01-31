import java.util.Arrays;

public class Parser {

    public Parser() {

    }

    public static String[] parseInput(String input) {
        String[] inputArray = input.split(" ");
        String[] tempArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
        return new String[] {inputArray[0], String.join(" ", tempArray)};
    }
}
