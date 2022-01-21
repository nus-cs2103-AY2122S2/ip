import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;

public class Duke {
    static String PATH = "data";
    static String FILENAME = "todolist.txt";

    public void writeToFile(File file, ArrayList<Task> taskArrayList) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            for (Task task : taskArrayList) {
                fileWriter.write(task.fileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error with writing to the file occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(PATH);
        File directory = new File(PATH + "/");
        if (! directory.exists()){
            boolean created = directory.mkdir();
        }

        File file = new File(PATH, FILENAME);
        try {
            boolean isNotMadeYet = file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /* Create file, directory
        How to write to file
        How to delete line number from file
         */

        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");
        String input = sc.nextLine();
        String[] splitted = input.split(" ", 2);
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (splitted[0].equals("delete")) {
                int index = Integer.parseInt(splitted[1]);
                System.out.println("____________________________________________________________\n" +
                        "Noted. I've removed this task:\n" +
                         taskList.get(index - 1) +
                        "\nNow you have " + taskList.size() + " tasks in the list."
                        + "\n____________________________________________________________");
                taskList.remove(index -1);
                dk.writeToFile(file, taskList);

            } else if (splitted[0].equals("todo")) {
                if (input.equals("todo") || input.equals("todo ")) {
                    System.out.println("____________________________________________________________\n" +
                            " ☹ OOPS!!! Please describe your todo :-(\n" +
                            "____________________________________________________________");
                } else {
                    taskList.add(new Todo(splitted[1], false));
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: \n" +
                            taskList.get(taskList.size() -1) +
                            "\nNow you have " + taskList.size() + " tasks in the list."
                            + "\n____________________________________________________________");
                    dk.writeToFile(file, taskList);
                }
            } else if (splitted[0].equals("deadline")) {
                String[] time = splitted[1].split("/by", 2);
                taskList.add(new Deadline(time[0], false, time[1]));
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task: \n" +
                        taskList.get(taskList.size() -1) +
                        "\nNow you have " + taskList.size() + " tasks in the list."
                        + "\n____________________________________________________________");
                dk.writeToFile(file, taskList);
            } else if (splitted[0].equals("event")) {
                String[] time = splitted[1].split("/at");
                taskList.add(new Event(time[0], false, time[1]));
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task: \n" +
                        taskList.get(taskList.size() - 1) +
                        "\nNow you have " + taskList.size() + " tasks in the list."
                        + "\n____________________________________________________________");
                dk.writeToFile(file, taskList);
            } else if (splitted[0].equals("mark")) {
                int index = Integer.parseInt(splitted[1]);
                taskList.get(index - 1).done = true;
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done: \n" +
                        taskList.get(index - 1)
                + "\n____________________________________________________________");
                dk.writeToFile(file, taskList);
            } else if (splitted[0].equals("unmark")) {
                int index = Integer.parseInt(splitted[1]);
                taskList.get(index - 1).done = false;
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet: \n" +
                        taskList.get(index - 1)
                        + "\n____________________________________________________________");
                dk.writeToFile(file, taskList);
            } else {
                // throw new DukeException("I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________\n" +
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________");
            }
            input = sc.nextLine();
            splitted = input.split(" ", 2);
        }
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
