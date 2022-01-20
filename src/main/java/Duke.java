import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<DukePair> ls = new ArrayList<>();

    public static void main(String[] args) {
        initDuke();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String[] wordSplit = userInput.split(" ");
            if (userInput.equals("bye")) {
                dukePrint("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (wordSplit[0].equals("mark")) {
                mark(Integer.valueOf(wordSplit[1]) - 1);
            } else if (wordSplit[0].equals("unmark")) {
                unmark(Integer.valueOf(wordSplit[1]) - 1);
            } else {
                dukePrint("added: " + addInput(userInput));
            }
        }
        sc.close();
    }

    private static void mark(int indx) {
        System.out.println("Nice! I've marked this task as done: ");
        DukePair dp = ls.get(indx);
        dp.setValue(true);
        System.out.println(dp);
        printHorizontalLine();
    }

    private static void unmark(int indx) {
        System.out.println("OK, I've marked this task as not done yet: ");
        DukePair dp = ls.get(indx);
        dp.setValue(false);
        System.out.println(dp);
        printHorizontalLine();
    }

    private static String addInput(String input) {
        ls.add(new DukePair(input, false));
        return input;
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            String num = String.format("%d. ", i + 1);
            System.out.println(num + ls.get(i).toString());
        }
        printHorizontalLine();
    }

    private static void dukePrint(String input) {
        printHorizontalLine();
        System.out.println(input);
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void initDuke() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

}
