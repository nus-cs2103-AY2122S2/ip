public class ExceptionHandler {
    public static void printDateTimeException() {
        System.out.println(Constant.HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! Wrong format date.");
        System.out.println(Constant.HORIZONTAL_LINE);
    }

    public static void printEmptyTaskException() {
        System.out.println(Constant.HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! Missing arguments.");
        System.out.println(Constant.HORIZONTAL_LINE);
    }

    public static void printUnknownCommandException() {
        System.out.println(Constant.HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(Constant.HORIZONTAL_LINE);
    }
    public static void printInvalidIndexException() {
        System.out.println(Constant.HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! Invalid index.");
        System.out.println(Constant.HORIZONTAL_LINE);
    }

    public static void printMissingDateTimeException() {
        System.out.println(Constant.HORIZONTAL_LINE);
        System.out.println("\t" + "☹ OOPS!!! Missing date/time. Please specify it.");
        System.out.println(Constant.HORIZONTAL_LINE);
    }
}
