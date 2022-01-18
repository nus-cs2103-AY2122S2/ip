import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> toDoList = new ArrayList<Task>(100);

    public static void main(String[] args) {
        String logo = "__  __ ____________\n" +
                "\\ \\/ /|_  /_  /_  /\n" +
                " >  <  / / / / / / \n" +
                "/_/\\_\\/___/___/___|";
        System.out.println(logo);
        System.out.println("Hello uwu! I'm xzzz,");
        System.out.println("You can check your schedwle here (ɔ◔‿◔)ɔ ♥!");

        respond();

        System.out.println("Cya later~ ≧◉◡◉≦");
    }

    private static void respond() {
        Scanner sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    break label;
                case "list":
                    displayList();
                    break;
                case "blah":
                    System.out.println("blah");
                    break;
                case "mark":
                    mark(sc.nextInt());
                    break;
                default:
                    toDoList.add(new Task(command));
                    System.out.println("okie!! (✿◠‿◠)  i have added: " + command);
                    break;
            }
        }
    }

    private static void displayList() {
        int number = 1;
        for (Task item : toDoList) {
            System.out.println(number + ". " + item.toString());
        }
    }

    private static void mark(int idx) {
        toDoList.set(idx - 1, toDoList.get(idx - 1).mark()); // minus 1 to offset the indexing
    }
}