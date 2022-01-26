package narcibot;
import java.io.*;
import java.util.Scanner;

public class Narcibot {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for a Narcibot with indicating where the task will be saved
     * @param fileName name of the file to save
     * @param path path of the file to save
     */
    public Narcibot(String fileName, String path) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(fileName, path);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.noFile();
        }
    }

    public static void main(String[] args) {
        new Narcibot("tasks.txt","./data").run();
    }

    /**
     * Runs the Narcibot program.
     */
    public void run()  {
        ui.welcome();
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            input = sc.nextLine();
            if(command(parser.parse(input))) {
                break;
            }
        }

        try {
                taskList.store(storage.store());
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    private boolean command(String[] command) {
        boolean end = false;
        try {
            switch (command[0]) {
            case "bye":
                if (command.length != 1) {
                    throw new IncorrectFormatException("Please enter only one word for this command");
                }
                ui.bye();
                return true;
            case "list":
                if (command.length != 1) {
                    throw new IncorrectFormatException("Please enter only one word for this command.");
                }
                ui.list();
                taskList.list();
                break;
            case "mark":
                if (command.length != 2) {
                    throw new IncorrectFormatException("Please enter mark followed by a number for this command. Example: mark 8");
                }
                ui.mark();
                taskList.mark(command[1]);
                break;
            case "unmark":
                if (command.length != 2) {
                    throw new IncorrectFormatException("Please enter unmark followed by a number for this command. Example: unmark 7");
                }
                ui.unmark();
                taskList.unmark(command[1]);
                break;
            case "delete":
                if (command.length != 2) {
                    throw new IncorrectFormatException("Please enter delete followed by a number for this command. Example: delete 7");
                }
                ui.delete();
                taskList.delete(command[1]);
                break;
            case "todo":
                if (command.length != 2) {
                    throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is?");
                }
                ui.task(taskList.todo(command[1]));
                break;
            case "deadline":
                if (command.length != 3) {
                    throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is? The format is deadline (task) /by (time)");
                }
                ui.task(taskList.deadline(command[1], command[2]));
                break;
            case "event":
                if (command.length != 3) {
                    throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is? The format is event (task) /at (time)");
                }
                ui.task(taskList.event(command[1], command[2]));
                break;
            case "find":
                if (command.length != 2) {
                    throw new IncorrectFormatException("You need to give me a keyword to find something.");
                }
                ui.find();
                taskList.find(command[1]);
                break;
            default:
                ui.unknown();
            }
        }  catch (NumberFormatException e) {
            System.out.println("Are you even trying to specify a task? Please enter in digits if you're wondering.");
        } catch (IncorrectFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
