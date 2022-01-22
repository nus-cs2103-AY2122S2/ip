import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Duke {

    public static void main(String[] args) throws IOException {
        String name = "Enkel";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
<<<<<<< HEAD
        String frame = "______________________________________________";
        System.out.println(frame + "\nHello! I\'m " + name + "\nWhat can I do for you?\n" + frame + "\n");
=======
        String frame = "_____________________________________________";
        System.out.println(frame + "\nHello! I'm " + name
                + "\nWhat can I do for you?\n" + frame + "\n");
>>>>>>> branch-Level-7

        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> tasks = new ArrayList<>();
        String currentDir = System.getProperty("user.dir");
        File directory = new File(currentDir + "/data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File records = new File(currentDir + "/data/records.txt");
        if (!records.createNewFile()) {
            Scanner sc_file = new Scanner(records);
            while (sc_file.hasNext()) {
                String[] record = sc_file.nextLine().split(" ", 3);
                switch (record[0]) {
                    case "T":
                        tasks.add(new Todo(record[2]));
                        break;
                    case "D":
                        String[] desc_by = record[2].split(" /by ", 2);
                        DukeDateTime by = DukeDateTime.parse(desc_by[1]);
                        tasks.add(new Deadline(desc_by[0], by));
                        break;
                    case "E":
                        String[] desc_at = record[2].split(" /at ", 2);
                        DukeDateTime at = DukeDateTime.parse(desc_at[1]);
                        tasks.add(new Event(desc_at[0], at));
                        break;
                }
                if (record[1].equals("1")) {
                    tasks.set(tasks.size() - 1, tasks.get(tasks.size() - 1).mark());
                }
            }
        }
        while (true) {
            input = sc.nextLine();
            String[] splited = input.split(" ", 2);
            String firstWord = splited[0];
            String remaining = "";
            boolean modifiedTasks = false;
            if (splited.length == 2) {
                remaining = splited[1];
            }
            System.out.println(frame);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" + frame);
                break;
            } else if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println("There are no tasks in your list~");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else if (input.matches("delete [1-9]+\\d*")) {
                int i = Integer.parseInt(remaining);
                if (i <= tasks.size()) {
                    modifiedTasks = true;
                    System.out.println("Noted. I've removed this task:\n" + tasks.get(i - 1));
                    tasks.remove(i - 1);
                } else {
                    System.out.println("Index is invalid");
                }
            } else if (input.matches("(un)?mark [1-9]+\\d*")) {
                int i = Integer.parseInt(remaining);
                if (firstWord.equals("unmark")) {
                    if (i <= tasks.size()) {
                        modifiedTasks = true;
                        tasks.set(i - 1, tasks.get(i - 1).unmark());
                        System.out.println("OK, I've marked this task as not done yet:\n"
                                + tasks.get(i - 1));
                    } else {
                        System.out.println("Index is invalid");
                    }
                } else {
                    if (i <= tasks.size()) {
                        modifiedTasks = true;
                        tasks.set(i - 1, tasks.get(i - 1).mark());
                        System.out.println("Nice! I've marked this task as done:\n"
                                + tasks.get(i - 1));
                    } else {
                        System.out.println("Index is invalid");
                    }
                }
            } else if (firstWord.equals("todo")) {
                if (remaining.equals("")) {
                    System.out.println("The description of a todo cannot be empty");
                } else {
                    modifiedTasks = true;
                    Task t = new Todo(remaining);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n" + t
                            + "\nNow you have " + tasks.size() + " tasks in your list.");
                }
            } else if (firstWord.equals("deadline")) {
                if (remaining.equals("")) {
                    System.out.println("The description of a deadline cannot be empty");
                } else {
                    String[] desc_by = remaining.split(" /by ", 2);
                    if (desc_by.length < 2) {
                        System.out.println("Must specify a deadline\n" + frame + "\n");
                        continue;
                    }
                    DukeDateTime by = DukeDateTime.parse(desc_by[1]);
                    if (by == null) {
                        System.out.println("Invalid input for date/time\n" + frame + "\n");
                        continue;
                    }
                    modifiedTasks = true;
                    Task t = new Deadline(desc_by[0], by);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n" + t
                            + "\nNow you have " + tasks.size() + " tasks in your list.");
                }
            } else if (firstWord.equals("event")) {
                if (remaining.equals("")) {
                    System.out.println("The description of an event cannot be empty");
                } else {
                    modifiedTasks = true;
                    String[] desc_at = remaining.split(" /at ", 2);
                    if (desc_at.length < 2) {
                        System.out.println("Must specify the time of event\n" + frame + "\n");
                        continue;
                    }
                    DukeDateTime at = DukeDateTime.parse(desc_at[1]);
                    if (at == null) {
                        System.out.println("Invalid input for date/time\n" + frame + "\n");
                        continue;
                    }
                    modifiedTasks = true;
                    Task t = new Deadline(desc_at[0], at);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n" + t
                            + "\nNow you have " + tasks.size() + " tasks in your list.");
                }
            } else {
                System.out.println("I'm sorry, but I don't know what that means :(");
            }
            System.out.println(frame + "\n");

            if (modifiedTasks) {
                records.delete();
                records.createNewFile();
                FileWriter fw = new FileWriter(records.getPath());
                for (Task t : tasks) {
                    fw.write(t.getIcon() + " " + t.getStatus() + " " + t.getDescription() + "\n");
                }
                fw.close();
            }
        }
    }

}
