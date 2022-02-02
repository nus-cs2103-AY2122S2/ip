import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.time.format.DateTimeParseException;

public class Duke {
    public static void main(String[] args) throws IOException {

        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = br.readLine().strip().replaceAll(" +", " ");

        ArrayList<Task> list = new ArrayList<Task>();

        while (!nextLine.equals("bye")) {
            System.out.println("__________________________________________________");
            String firstWord = nextLine.split(" ")[0];
            if (firstWord.equals("mark")) {
                try {
                    int indexToMark = Integer.parseInt(nextLine.substring(5)) - 1;
                    list.set(indexToMark, list.get(indexToMark).mark());
                    System.out.println("Nice! I've marked this task as done:\n  " 
                            + list.get(indexToMark));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose a valid task! (Your list has " 
                            + list.size() + " tasks)");
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            } else if (firstWord.equals("unmark")) {
                try {
                    int indexToUnmark = Integer.parseInt(nextLine.substring(7)) - 1;
                    list.set(indexToUnmark, list.get(indexToUnmark).unmark());
                    System.out.println("OK, I've marked this task as not done yet:\n  " 
                            + list.get(indexToUnmark));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose a valid task! (Your list has " 
                            + list.size() + " tasks)");
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            } else if (firstWord.equals("list")) {
                if (nextLine.split(" ").length > 1) {
                    System.out.println("Uh-oh! There should not be any words after 'list'.");
                } else if (list.isEmpty()) {
                    System.out.println("Uh-oh! List is empty!");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                }
            } else if (firstWord.equals("delete")) {
                try {
                    int indexToDelete = Integer.parseInt(nextLine.substring(7)) - 1;
                    Task deletedTask = list.remove(indexToDelete);
                    System.out.println("Noted. I've removed this task:\n  " 
                            + deletedTask 
                            + "\n Now you have " + list.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose a valid task! (Your list has " 
                            + list.size() + " tasks)");
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            } else {
                String taskType = nextLine.split(" ")[0];
                Optional<Task> task = Optional.empty();
                if (taskType.equals("todo")) {
                    if (nextLine.split(" ").length == 1) {
                        System.out.println("Uh-oh! There is nothing to do here!");
                    } else {
                        task = Optional.of(new Todo(nextLine.substring(5)));
                    }
                } else if (taskType.equals("deadline")) {
                    try {
                        String description = nextLine.split(" /by ")[0].substring(9);
                        String by = nextLine.split(" /by ")[1];
                        task = Optional.of(new Deadline(description, by));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Eh? Did you mistype the format?");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Uh-oh! There's no deadline here!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Please type a valid date! (Format: YYYY-MM-DD)");
                    }
                } else if (taskType.equals("event")) {
                    try {
                        String description = nextLine.split(" /at ")[0].substring(6);
                        String at = nextLine.split(" /at ")[1];
                        task = Optional.of(new Event(description, at));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Eh? Did you mistype the format?");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Uh-oh! There's no event here!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Please type a valid date! (Format: YYYY-MM-DD)");
                    }
                } else {
                    System.out.println("Sorry! Please speak Duke :(");
                }
                try {
                    list.add(task.get());
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.get());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } catch (NoSuchElementException e) {
                    System.out.println("Please try again!");
                }
            }

            System.out.println("__________________________________________________");
            nextLine = br.readLine().strip().replaceAll(" +", " ");
        }

        System.out.println("__________________________________________________\n" 
                + "Bye. Hope to see you again soon!"
                + "\n__________________________________________________");
        br.close();
    }
}
