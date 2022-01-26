import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello, traveller! My name in Paimon.\nHow can I help you today?");
        String input = readInput(reader);
        List<Task> tasks = new LinkedList<>();

        while (!input.equals("bye")){
            String command = input.replaceAll(" .*", "");

            if (!isValid(input)) {
                input = readInput(reader);
                continue;
            }

            if (input.contains(" ")) {
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
                    System.out.println("Got it! I have noted down the following task in your list.");
                    System.out.println(t);
                    break;
                case "deadline":
                    String datetime = input.replaceAll(".* by ", "");
                    input = input.replaceAll(" by .*", "");
                    Deadline d = new Deadline(input, datetime);
                    tasks.add(d);
                    System.out.println("Got it! I have noted down the following task in your list. " +
                            "\nRemember the deadline!");
                    System.out.println(d);
                    break;
                case "event":
                    String time = input.replaceAll(".* at ", "");
                    input = input.replaceAll(" at .*", "");
                    Event e = new Event(input, time);
                    tasks.add(e);
                    System.out.println("Got it! I have noted down the following task in your list. " +
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
                System.out.println("  " + i+1 +". " + t.get(i));
            }
            else {
                System.out.println("You now have " + i +" tasks on your list.");
                return;
            }
        }
    }

    public static boolean isValid(String input) {
        //commands = "bye", "list", "do", "undo", "todo", "deadline", "event"
        input = input.trim();
        if (input.equals("bye") || input.equals("list")) {
            return true;
        }

        String firstWord = input.replaceAll(" .*", "");
        if (firstWord.equals("do") || firstWord.equals("undo")) {
            input = input.replaceAll(".* ", "");
            return input.matches("[0-9]+");
        }

        if (firstWord.equals("todo")) {
            input = input.substring(4).trim();
            if (input.equals("")) {
                System.out.println("Oops, you need to mention what the task is :c");
                return false;
            }
            return true;
        }

        if (firstWord.equals("deadline")) {
            input = input.substring(8).trim();
            if (!input.contains(" by ")) {
                System.out.println("Oops, you need to format deadline tasks as \"deadline X by Y\" :c");
                return false;
            }
            String lastWord = input.substring(input.lastIndexOf(" ") + 1);
            return !lastWord.equals("by");
        }

        if (firstWord.equals("event")) {
            input = input.substring(5).trim();
            if (!input.contains(" at ")) {
                System.out.println("Oops, you need to format event tasks as \"event X at Y\" :c");
                return false;
            }
            String lastWord = input.substring(input.lastIndexOf(" ")+1);
            return !lastWord.equals("at");
        }

        return false;
    }
}
