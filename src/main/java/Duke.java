import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<String> toDoList = new ArrayList<String>(100);

    public static void main(String[] args) {
        String logo = "__  __ ____________\n" +
                "\\ \\/ /|_  /_  /_  /\n" +
                " >  <  / / / / / / \n" +
                "/_/\\_\\/___/___/___|";
        System.out.println(logo);
        System.out.println("Hello uwu! I'm xzzz,");
        System.out.println("You can check your schedwle here (ɔ◔‿◔)ɔ ♥!");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }

            respond(command);
        }

        System.out.println("Cya later~ ≧◉◡◉≦");
    }

    private static void respond(String command) {
        if (command.equals("list")) {
            displayList();
        } else if (command.equals("blah")) {
            System.out.println("blah");
        } else {
            toDoList.add(command);
            System.out.println("okie!! (✿◠‿◠)  i have added: " + command);
        }
    }

    private static void displayList() {
        int number = 1;
        for (String item : toDoList) {
            System.out.println(number + ". " + item);
        }
    }
}