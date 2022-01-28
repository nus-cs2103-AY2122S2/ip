import exception.DukeException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private File newFile;
    private Parser parser;

    public Duke(String filePath) throws FileNotFoundException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        newFile = new File(filePath);
        tasks = new TaskList();
        parser = new Parser();

        if (newFile.exists()) {
            storage.loadFile(tasks.list);
        } else {
            throw new DukeException("Please create the text file data/duke.txt");
        }


    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);

        ui.greet();

        String tab = "    ";

        parser.parse(sc.nextLine());
        String task = parser.getTask();
        String item = parser.getItem();


        while (!task.equals("bye")) {
            if (task.equals("todo")) {
                if (!item.equals("")) {
                    tasks.add(new Todo(item));
                } else {
                    ui.reply("Can read instructions or not? Todo cannot be empty :/");
                }

            } else if (task.equals("deadline")) {
                if (!item.equals("")) {
                    String thing = item.split(" /by ")[0];
                    String time = item.split(" /by ")[1];
                    tasks.add(new Deadline(thing, time));
                } else {
                    ui.reply("Can read instructions or not? Deadline cannot be empty :/");
                }

            } else if (task.equals("event")) {
                if (!item.equals("")) {
                    String thing = item.split(" /at ")[0];
                    String time = item.split(" /at ")[1];
                    tasks.add(new Event(thing, time));
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
                tasks.deleteItem(item);

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



    public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/duke.txt").run();
        }



}




