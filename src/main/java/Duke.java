import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import util.Parser;
import util.Storage;
import util.Ui;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * initialise the util classes of Duke
     * @param filePath filePath of text file to store tasks
     */


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        File newFile = new File(filePath);
        tasks = new TaskList();
        parser = new Parser();

        try {
            if (newFile.exists()) {
                storage.loadFile(tasks.list);
            }
        } catch (FileNotFoundException e) {
            ui.reply("Please create the text file data/duke.txt");
        }



    }

    /**
     * Runs the program
     */

    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.greet();

        String tab = "    ";

        parser.parse(sc.nextLine());
        String task = parser.getTask();
        String item = parser.getItem();


        while (!task.equals("bye")) {
            switch (task) {
            case "todo":
                if (!item.equals("")) {
                    tasks.add(new Todo(item));
                } else {
                    ui.reply("Can read instructions or not? Todo cannot be empty :/");
                }

                break;
            case "deadline":
                if (!item.equals("")) {
                    String thing = item.split(" /by ")[0];
                    String time = item.split(" /by ")[1];
                    tasks.add(new Deadline(thing, time));
                } else {
                    ui.reply("Can read instructions or not? Deadline cannot be empty :/");
                }

                break;
            case "event":
                if (!item.equals("")) {
                    String thing = item.split(" /at ")[0];
                    String time = item.split(" /at ")[1];
                    tasks.add(new Event(thing, time));
                } else {
                    ui.reply("Can read instructions or not? Event cannot be empty :/");
                }

                break;
            case "list":
                StringBuilder lists = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    if (i != 0) {
                        lists.append("\n").append(tab);
                    }
                    lists.append(String.format("%d. %s", i + 1, tasks.get(i).toString()));

                }
                ui.reply(lists.toString());

                break;
            case "mark":
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsDone();
                    ui.reply("Good job for accomplishing something today! I've marked this task as done:\n      "
                            + tasks.get(index - 1).toString());
                } catch (IndexOutOfBoundsException e) {
                    ui.reply("You can't do that! It's not in the list!");
                }

                break;
            case "unmark":
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsUndone();
                    ui.reply("Stop procrastinating you lazy prick! I've marked this task as not done yet:\n      "
                            + tasks.get(index - 1).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You can't do that! It's not in the list!");
                }

                break;
            case "delete":
                tasks.deleteItem(item);

                break;
            case "find":
                tasks.find(item);

                break;
            default:
                ui.reply("What is this? Can you read English?");
                break;
            }

            parser.parse(sc.nextLine());
            task = parser.getTask();
            item = parser.getItem();
        }

        try {
            storage.writeToFile(tasks.list);
        } catch (IOException e) {
            ui.reply("IOException detected");
        }


        ui.exit();
        sc.close();
    }


    

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}




