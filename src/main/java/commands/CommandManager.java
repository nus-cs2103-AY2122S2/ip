package commands;

import tasks.TaskManager;
import exceptions.DateException;
import exceptions.DukeException;
import exceptions.TaskIndexException;
import ui.UiManager;
import java.util.Scanner;

public class CommandManager {
    private final TaskManager tm;
    private final UiManager um;
    private boolean isOpen;

    public CommandManager() {
        this.um = new UiManager();
        this.tm = new TaskManager(this.um);
        this.isOpen = true;
    }

    public void open() {
        this.isOpen = true;
    }

    public void close() {
        this.isOpen = false;
    }

    public void run() {
        um.welcome();
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            s = sc.nextLine();
            try {
               String[] command = um.parseCommand(s);
                switch (command[0]) {
                    case "bye":
                        this.close();
                        um.exit();
                        break;
                    case "list":
                        um.printList(tm);
                        break;
                    case "todo":
                        AddTaskCommand todo = new AddTaskCommand(this.um, this.tm, command[1], Type.TODO);
                        todo.execute();
                        break;
                    case "deadline":
                        AddTaskCommand deadline = new AddTaskCommand(this.um, this.tm, command[1], Type.DEADLINE);
                        deadline.execute();
                        break;
                    case "event":
                        AddTaskCommand event = new AddTaskCommand(this.um, this.tm, command[1], Type.EVENT);
                        event.execute();
                        break;
                    case "mark":
                        NumCommand mark = new NumCommand(this.um, this.tm, command[1], Type.MARK);
                        mark.execute();
                        break;
                    case "unmark":
                        NumCommand unmark = new NumCommand(this.um, this.tm, command[1], Type.UNMARK);
                        unmark.execute();
                        break;
                    case "delete":
                        NumCommand delete = new NumCommand(this.um, this.tm, command[1], Type.DELETE);
                        delete.execute();
                        break;
                    default:
                        throw new DukeException();
                }
            } catch (DateException | TaskIndexException | DukeException e) {
                um.errorMessage(e.toString());
            } catch (NumberFormatException e) {
                um.errorMessage("Give me the task number in numbers please!");
            }

        } while (this.isOpen);
        sc.close();
    }
}
