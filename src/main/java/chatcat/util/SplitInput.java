package chatcat.util;

public class SplitInput {

    public static int getIndex(String str, int n) {
        String[] input = str.split(" ");

        return Integer.parseInt(input[n]) - 1;
    }

    public static String getKeyword(String str) {
        String[] input = str.split(" ");

        return input[1];
    }

    public static String getTask(String str, String regex, int subString) {
        String[] input = str.split(regex);
        System.out.println(input[0].substring(subString));
;       return input[0].substring(subString);
    }

    public static String getTime(String str, String regex) {
        String[] input = str.split(regex);
        System.out.println(input[1]);
        return input[1];
    }
}
