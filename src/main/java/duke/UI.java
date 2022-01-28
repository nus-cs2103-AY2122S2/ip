package duke;

public class UI {
    static String line = "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
    static String start = "              Greetings! Mike here!\n" +
            "               How can I help you?\n" ;
    static String gotit = "Got it. I've added this task:\n ";
    static String now1 = "Now you have ";
    static String now2 = " tasks in the list.";
    static String mark = "Nice! I've marked this task as";
    static String deleted = "Noted. I've removed this task:\n";
    static String gnum = "Give me a Number.";
    static String gdes = "Description is empty, give me a description.";
    static String invalid = "INVALID Entry man, Try again :-( ";
    static String logo =
            "||======||==||======|| !!!! ||====||    //===//======||\n"
                    + "||                  || !!!! ||    ||   //   //       ||\n"
                    + "||  ||==||  ||==||  ||======||    ||==||   //  ||====||\n"
                    + "||  ||  ||  ||  ||  ||      ||            |||  ||====||\n"
                    + "||  ||  ||  ||  ||  ||      ||    ||==||   \\\\  ||====||\n"
                    + "||  ||  ||  ||  ||  ||      ||    ||   \\\\   \\\\       ||\n"
                    + "||==||  ||==||  ||==||======||====||    \\\\===\\\\======||\n";

    public static void printIntro() {
        System.out.println("\n" + logo + line);
        System.out.println(start + line);
    }

    public static void printLine() {
        System.out.println(line);
    }

    public static void printTerminate() {
        System.out.println(line + "Bye yo. Hope I helped you!" + line);
    }

    public static void printWithLines(String text) {
        System.out.println(line + text + line);
    }

    public static void printAddMessage(String text, int n) {
        System.out.println(line + gotit + text + "\n" + now1 + (n+1) + now2 + line);
    }

    public static void deleteMessage(String text) {
        System.out.println(line + deleted + text + line);
    }

    public static void printMarked(String text) {
        System.out.println(line + mark + " as done:\n" + text + line);
    }

    public static void printUnMarked(String text) {
        System.out.println(line + mark + " as not done yet:\n" + text + line);
    }

}
