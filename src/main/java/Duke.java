public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println(separation + greeting + separation);
        System.out.println(separation +list + separation);
        System.out.println(separation +blah+ separation);
        System.out.println(separation +bye + separation);

    }

    public static String greeting = "Hello! I'm Duke\nWhat can I do for you?";
    public static String list = "list";
    public static String blah = "blah";
    public static String bye = "Bye, Hope to see you again soon!";
    public static String separation = "\n******************************\n";
}
