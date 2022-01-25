import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello, traveller! My name in Paimon.\nHow can I help you today?");
        String input = readInput(reader);
        //Task tasks[] = new Task[100]; //100 is an arbitrary constant
        List<Task> tasks = new LinkedList<Task>();
        String command;

        while (!input.equals("bye")){
            command = input.replaceAll(" .*", "");

            switch (command) {
                case "list":
                    printList(tasks);
                    break;
                case "do":
                    int i = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    tasks.get(i).markComplete();
                    break;
                case "undo":
                    int j = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    tasks.get(j).markIncomplete();
                    break;
                default:
                    tasks.add(new Task(input));
                    System.out.println(input);
            }

            input = readInput(reader);
        }

        System.out.println("Bye, hope to see you again soon!");
        reader.close();
    }

    public static String readInput(Scanner s) {
        System.out.print("| \r");
        return s.nextLine();
    }

    /* Returns true if an item is in a given array, and false otherwise.
     * Only compatible with Strings at the moment.
     */
    public static boolean inList(String[] list, String s) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    /* Prints out a given list.
     * Only compatible with Strings at the moment.
     */
    public static void printList(List<Task> t) {
        System.out.println("Hmm... Paimon keeps a clear record in her diary.");
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i) != null) {
                System.out.println(String.format("  %d. %s", i + 1, t.get(i)));
            }
            else {
                return;
            }
        }
    }
}
