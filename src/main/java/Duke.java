
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        Scanner sc = new Scanner(System.in);

        Ui ui = new Ui();

        File newFile = new File("data/duke.txt");

        Storage storage = new Storage("data/duke.txt");

        Parser parser = new Parser();

        ui.greet();

        if (!newFile.exists()) {
            throw new DukeException("Please create file under directory data");
        }

        TaskList tasks = new TaskList();
        storage.loadFile(tasks.list);


        String tab = "    ";

        parser.parse(sc.nextLine());
        String task = parser.getTask();
        String item = parser.getItem();


        while (!task.equals("bye")) {
            if (task.equals("todo")) {
                if (!item.equals("")) {
                    tasks.add(new Todo(item));
                    ui.reply(addTask(tasks.get(tasks.size() - 1), tasks.size()));
                } else {
                    throw new DukeException("Can read instructions or not? Todo cannot be empty :/");
                }

            } else if (task.equals("deadline")) {
                if (!item.equals("")) {
                    String thing = item.split(" /by ")[0];
                    String time = item.split(" /by ")[1];
                    tasks.add(new Deadline(thing, time));
                    ui.reply(addTask(tasks.get(tasks.size() - 1), tasks.size()));
                } else {
                    ui.reply("Can read instructions or not? Deadline cannot be empty :/");
                }

            } else if (task.equals("event")) {
                if (!item.equals("")) {
                    String thing = item.split(" /at ")[0];
                    String time = item.split(" /at ")[1];
                    tasks.add(new Event(thing, time));
                    ui.reply(addTask(tasks.get(tasks.size() - 1), tasks.size()));
                } else {
                    ui.reply("Can read instructions or not? Event cannot be empty :/");
                }

            } else if (task.equals("list")){
                String lists = "";
                for (int i = 0; i < tasks.size(); i++) {
                    if (i != 0) {
                        lists += "\n" + tab;
                    }
                    lists += String.format("%d. %s", i + 1, tasks.get(i).toString());

                }
                ui.reply(lists);

            } else if (task.equals("mark")) {
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsDone();
                    ui.reply("Good job for accomplishing something today! I've marked this task as done:\n      "
                            + tasks.get(index - 1).toString());
                } catch (IndexOutOfBoundsException e) {
                    ui.reply("You can't do that! It's not in the list!");
                }

            } else if (task.equals("unmark")) {
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsUndone();
                    ui.reply("Stop procrastinating you lazy prick! I've marked this task as not done yet:\n      "
                            + tasks.get(index - 1).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You can't do that! It's not in the list!");
                }

            } else if (task.equals("delete")) {
                try {
                    int index = Integer.parseInt(item);
                    Task t = tasks.get(index - 1);
                    tasks.remove(index - 1);
                    ui.reply(removeTask(t, tasks.size()));
                } catch (IndexOutOfBoundsException e) {
                    ui.reply("You can't do that! It's not on the list!");
                }


            } else {
                ui.reply("What is this? Can you read English?");
            }

            parser.parse(sc.nextLine());
            task = parser.getTask();
            item = parser.getItem();
        }
        storage.writeToFile(tasks.list);

        ui.exit();
        sc.close();
    }

    private static String addTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Ah sure. I've added this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;
        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }

        return firstLine + secondLine + thirdLine;

    }

    private static String removeTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Less work for you then less work for me then. I've removed this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;

        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }
        return firstLine + secondLine + thirdLine;




    }

}




