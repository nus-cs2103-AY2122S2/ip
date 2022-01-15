import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo =
                "U _____ u    ____    _   _      U  ___ u \n" +
                        "\\| ___\"|/ U /\"___|  |'| |'|      \\/\"_ \\/ \n" +
                        " |  _|\"   \\| | u   /| |_| |\\     | | | | \n" +
                        " | |___    | |/__  U|  _  |u .-,_| |_| | \n" +
                        " |_____|    \\____|  |_| |_|   \\_)-\\___/  \n" +
                        " <<   >>   _// \\\\   //   \\\\        \\\\    \n" +
                        "(__) (__) (__)(__) (_\") (\"_)      (__)";

        System.out.println("Hello I'm\n" + logo);

        Echo echo = new Echo();

        String s = sc.nextLine();
        while (!s.equals("bye")) {
            String[] split = s.split(" ");
            if (split[0].equals("list")) {
                echo.getTask();
            } else if (split[0].equals("mark") && isInteger(split[1])) {
                echo.mark(Integer.parseInt(split[1]));
            } else if (split[0].equals("unmark") && isInteger(split[1])) {
                echo.unmark(Integer.parseInt(split[1]));
            } else {
                echo.addTask(s);
            }
            s = sc.nextLine();
        }
        echo.exit();
    }

    /**
     * Checks if a string is an integer.
     *
     * @param str String
     * @return True if str is an integer; otherwise false.
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
