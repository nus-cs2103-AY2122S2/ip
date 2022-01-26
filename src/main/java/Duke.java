import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello, traveller! My name in Paimon.\nHow can I help you today?");
        String input = readInput(reader);
        List<Task> tasks = new LinkedList<Task>();

        while (!input.equals("bye")){
            String command = input.replaceAll(" .*", "");
            if (input.indexOf(" ") > 0) {
                input = input.substring(input.indexOf(" "));
            }

            switch (command) {
                case "list":
                    printList(tasks);
                    break;
                case "do":
                    int i = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    tasks.get(i).markComplete();
                    System.out.println("Task successfully updated.");
                    break;
                case "undo":
                    int j = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    tasks.get(j).markIncomplete();
                    break;
                case "todo":
                    Todo t = new Todo(input);
                    tasks.add(t);
                    System.out.println("Got it! I have noted down the following task to your list.");
                    System.out.println(t);
                    break;
                case "deadline":
                    String datetime = input.replaceAll(".* by ", "");
                    input = input.replaceAll(" by .*", "");
                    Deadline d = new Deadline(input, datetime);
                    tasks.add(d);
                    System.out.println("Got it! I have noted down the following task to your list. " +
                            "\nRemember the deadline!");
                    System.out.println(d);
                    break;
                case "event":
                    String time = input.replaceAll(".* at ", "");
                    input = input.replaceAll(" at .*", "");
                    Event e = new Event(input, time);
                    tasks.add(e);
                    System.out.println("Got it! I have noted down the following task to your list. " +
                            "\nDo be there on time!");
                    System.out.println(e);
                    break;
                default:
                    System.out.println("That went over Paimon's head a little...");
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

    /* Prints out a given list.
     * Only compatible with Tasks at the moment.
     */
    public static void printList(List<Task> t) {
        System.out.println("Hmm... Paimon keeps a clear record in her diary.");
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i) != null) {
                System.out.println(String.format("  %d. %s", i + 1, t.get(i)));
            }
            else {
                System.out.println(String.format("You now have %d tasks on your list.", i));
                return;
            }
        }
    }
}
