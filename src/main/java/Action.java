import java.util.ArrayList;

public class Action {

    Action() {}

    public static void greet() {
        String logo  = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello! I'm JiaMing aka\n"+ logo + "\nWhat can I do for you?\nTip: use 'help' for help\n";
        System.out.println(greet);
    }

    public static void echo(String phrase) {
        System.out.println(phrase);
    }

    public static void bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    public static void showList(ArrayList<Task> arrlst) {
        System.out.println("Here are the tasks in your list:");
        for (int i =  0; i < arrlst.size(); i++) {
            String output = String.format("%d.%s\n", i + 1, arrlst.get(i));
            System.out.println(output);
        }
    }
}
