import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<String> ls = new ArrayList<>();


    public static void main(String[] args) {
        initDuke();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("list")) {
                printList();
            } else if (userInput.equals("bye")) {
                dukePrint("Bye. Hope to see you again soon!");
                break;
            } else {
                dukePrint("added: " + addInput(userInput));
            }
        }
        sc.close();
    }

    private static String addInput(String input) {
        ls.add(input);
        return input;
    }

    private static void printList() {
        for (int i = 0; i < ls.size(); i++) {
            String num = String.format("%d. ", i + 1);
            System.out.println(num + ls.get(i));
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
