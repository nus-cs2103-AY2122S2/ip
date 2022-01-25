package duke.util;

public class NumericCheck {

    public static boolean checkNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }
}
