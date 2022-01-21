import java.util.ArrayList;

public class Apollo {
    private static ArrayList<String> items;
    private static Commands input;

    public static void printMsg(String msg) {
        System.out.println("\t________________________________________________________________________\n\t " +
                msg.replace("\n","\n\t ") +
                "\n\t________________________________________________________________________\n");
    }

    private static void start() {
        items = new ArrayList<>();
        input = new Commands();
        Banner.welcomeMsg();
    }

    public static void stop() {
        printMsg("See you next time. \n" +
                "I am always available when you need me. ");
    }

    public static void addItem(String item) {
        items.add("[ ] " + item);
        printMsg("added: " + item);
    }

    public static void printList() {
        if (items.isEmpty()) {
            printMsg("You have no tasks left. ");
            return;
        }
        StringBuilder list = new StringBuilder();
        list.append("These are your current tasks. ");
        var counter = new Object() {
            int num = 1;
        };
        items.forEach(i -> {
            list.append("\n  ").append(counter.num).append(".").append(i);
            counter.num++;
        });
        printMsg(list.toString());
    }

    public static void mark(int i, Commands.Mark m) {
        if (i > items.size()) {
            printMsg("Please add more items first. ");
        } else {
            String mark = m == Commands.Mark.MARK
                    ? "X"
                    : " ";
            items.set(i-1, items.get(i-1).substring(0,1) + mark + items.get(i-1).substring(2));
        }
        String msg = m == Commands.Mark.MARK
                ? "Alright, I've mark this task as done.\n  "
                : "Noted, I've marked this task as not done yet.\n  ";
        printMsg(msg + items.get(i-1));
    }

    public static void main(String[] args) {
        start();
        input.response();
    }
}
