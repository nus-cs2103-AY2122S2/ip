import java.io.File;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }



    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        File directory = new File("../../../data/");
        if (!directory.exists()) directory.mkdir();

        List<Task> ls = new ArrayList<>();

        try {
            File saved = new File("../../../data/duke.txt");
            if (!saved.exists()) {
                saved.createNewFile();
            } else {
                Scanner scan = new Scanner(saved);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] splitted = line.split("\\|");
                    boolean completed = splitted[1].equals("1");
                    if (splitted[0].equals("D")) {
                        Deadline toAdd = new Deadline(splitted[2], splitted[3]);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                    if (splitted[0].equals("T")) {
                        Todo toAdd = new Todo(splitted[2]);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                    if (splitted[0].equals("E")) {
                        Event toAdd = new Event(splitted[2], splitted[3]);
                        ls.add(toAdd);
                        if (completed) toAdd.setDone();
                    }
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }

        Scanner scan = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        while (true) {
            String input = scan.nextLine();
            if (input.startsWith("bye")) {
                break;
            } else if (input.startsWith("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + ". " + ls.get(i));
                }
                printLine();
                continue;
            } else if (input.startsWith("mark")) {
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    ls.get(index - 1).setDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(ls.get(index - 1));
                    printLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index you have provided is invalid");
                    printLine();
                    continue;
                }
            } else if (input.startsWith("unmark")) {
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    ls.get(index - 1).setUndone();
                    printLine();
                    System.out.println("OK, I've marked this task as not dont yet:  ");
                    System.out.println(ls.get(index - 1));
                    printLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index you have provided is invalid");
                    printLine();
                    continue;
                }
            } else if (input.startsWith("todo")) {
                try {
                    Task task = new Todo(input.split("todo")[1].trim());
                    ls.add(task);
                    printLine();
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(task);
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    printLine();
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    printLine();
                    continue;
                }
            } else if (input.startsWith("deadline")) {
                try {
                    String[] splitted = input.split("deadline")[1].split("/by");
                    Deadline toAdd = new Deadline(splitted[0].trim(), splitted[1].trim());
                    ls.add(toAdd);
                    printLine();
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(toAdd);
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    printLine();
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! The correct format is : `deadline /by <type time here>`");
                }

            } else if (input.startsWith("event")) {
                try {
                    String[] splitted = input.split("event")[1].split("/at");
                    Event toAdd = new Event(splitted[0].trim(), splitted[1].trim());
                    ls.add(toAdd);
                    printLine();
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(toAdd);
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    printLine();
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! The correct format is : `event /at <type time here>`");

                }
             } else if (input.startsWith("delete")) {
                try {
                    String[] splitted = input.split(" ");
                    int index = Integer.valueOf(splitted[1]);
                    Task removedTask = ls.get(index - 1);
                    ls.remove(index - 1);
                    printLine();
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    printLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The index you have provided is invalid");
                    printLine();
                    continue;
                }
            } else {
                System.out.println("OOPS!!! I'm sorry, but I do not know what that means :-(");
                printLine();
            }
        }
        try {
            File f = new File("../../../data/duke.txt");
            f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter("../../../data/duke.txt");
            for (Task t : ls) {
                fw.write(t.saveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();

    }
}
