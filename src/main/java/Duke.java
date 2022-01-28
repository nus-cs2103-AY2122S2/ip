import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello, traveller! My name in Paimon.\nHow can I help you today?");
        String input = readInput(reader);
        List<Task> tasks = new LinkedList<>();

        //load data file
        try {
            File data = new File("../../../data/duke.txt");
            data.getParentFile().mkdirs(); //make preceding directories, if any are not found
            //File data = new File("duke.txt");
            if (!data.createNewFile()) { //if file exists
                Scanner fileReader = new Scanner(data);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] tmp = line.split("\\|");

                    boolean isDone = tmp[1].trim().equals("D");
                    //assumes valid input
                    switch (tmp[0].trim()) {
                    case "T":
                        Todo t = new Todo(tmp[2].trim());
                        if (isDone) {
                            t.markComplete();
                        }
                        tasks.add(t);
                        break;
                    case "D":
                        Deadline d = new Deadline(tmp[2].trim(), tmp[3].trim());
                        if (isDone) {
                            d.markComplete();
                        }
                        tasks.add(d);
                        break;
                    case "E":
                        Event e = new Event(tmp[2].trim(), tmp[3].trim());
                        if (isDone) {
                            e.markComplete();
                        }
                        tasks.add(e);
                        break;
                    //needs default exception handling
                    }
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("Oh no, an error occurred with the data processing.");
            e.printStackTrace();
        }

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
                    System.out.println("Hmm... Paimon keeps a clear record in her diary.");
                    printList(tasks);
                    break;
                case "do":
                    int i = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    tasks.get(i).markComplete();
                    System.out.println("Task successfully updated.");
                    saveData(tasks);
                    break;
                case "undo":
                    int j = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    tasks.get(j).markIncomplete();
                    saveData(tasks);
                    break;
                case "delete":
                    int k = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    tasks.remove(k);
                    System.out.println("Noted, the task has been scrubbed off the list!");
                    printList(tasks);
                    saveData(tasks);
                    break;
                case "todo":
                    Todo t = new Todo(input);
                    tasks.add(t);
                    System.out.println("Got it! I have noted down the following task in your list.");
                    System.out.println(t);
                    saveData(tasks);
                    break;
                case "deadline":
                    String datetime = input.replaceAll(".* by ", "");
                    input = input.replaceAll(" by .*", "");
                    Deadline d = new Deadline(input, datetime);
                    tasks.add(d);

                    System.out.println("Got it! I have noted down the following task in your list. " +
                            "\nRemember the deadline!");
                    System.out.println(d);
                    saveData(tasks);
                    break;
                case "event":
                    String time = input.replaceAll(".* at ", "");
                    input = input.replaceAll(" at .*", "");
                    Event e = new Event(input, time);
                    tasks.add(e);
                    System.out.println("Got it! I have noted down the following task in your list. " +
                            "\nDo be there on time!");
                    System.out.println(e);
                    saveData(tasks);
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
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i) != null) {
                int index = i+1;
                System.out.println("  " + index + ". " + t.get(i));
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
        if (firstWord.equals("do") || firstWord.equals("undo") || firstWord.equals("delete")) {
            input = input.replaceAll(".* ", "");
            if (input.matches("[0-9]+")) {
                return true;
            }
            System.out.println("You need to specify the task you want to "+ firstWord + " by its index :c");
            return false;
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

    //data.txt file + all its directories will be present at this point
    public static void saveData(List<Task> tasks) {
        File data = new File("../../../data/duke.txt");
        //File data = new File("duke.txt");
        FileWriter f;

        try {
            f = new FileWriter(data, false);
            boolean isFirst = true;
            for (int i = 0; i < tasks.size(); i++) {
                String s = isFirst ? "" : "\n";
                f.write(s + tasks.get(i).writeToFile());
                isFirst = false;
            }
            f.close();
        } catch (IOException e) {
            System.out.println("An error occurred with writing to the data file.");
            e.printStackTrace();
        }
    }
}
