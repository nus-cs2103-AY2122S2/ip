public class Parser {
    public static String[] splitForwardSlash(String input) {
        return input.split("/");
    }

    public static String[] splitSpace(String input) {
        return input.split(" ");
    }

    public static String[] splitBracket(String input) {
        return input.split("]");
    }

    public static int stringToInt(String input) {
        return Integer.parseInt(input);
    }

    public static String makeDesc(String[] text, int len) {
        String newText = "";
        for (int i = 1; i < len; i++) {
            newText = newText + text[i] + " ";
        }
        return newText.trim();
    }
}
