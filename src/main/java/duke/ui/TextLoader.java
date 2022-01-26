package duke.ui;

import duke.commands.AddTaskCommand;
import duke.commands.NumCommand;
import duke.commands.Type;
import duke.exceptions.DateException;
import duke.exceptions.TaskIndexException;
import duke.tasks.TaskManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * TextLoader Object that handles loading of task list from
 * a saved text file during program initialisation.
 */
public class TextLoader {
    private final UiManager um;
    private final TaskManager tm;
    private final TextStorage ts;


    /**
     * Constructs TextLoader Object
     *
     * @param uiManager UiManager Object to handle input and output processes
     * @param taskManager TaskManager Object to hold Task Objects
     * @param textStorage TextStorage Object to store task list
     */
    public TextLoader(UiManager uiManager, TaskManager taskManager, TextStorage textStorage) {
        this.um = uiManager;
        this.tm = taskManager;
        this.ts = textStorage;
    }

    /**
     * Loads saved task list into TaskManager Object
     *
     * @throws FileNotFoundException if saved text file is not present
     * @throws TaskIndexException if saved text file holds corrupted entries
     */
    public void load() throws FileNotFoundException, TaskIndexException {
        File f = new File("storage/stored.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()) {
            try {
                String[] command = um.parseCommand(sc.nextLine());
                switch (command[0]) {
                    case "deadline":
                        AddTaskCommand deadline = new AddTaskCommand(this.um, this.tm, command[1], Type.DEADLINE);
                        deadline.insert();
                        ts.append(command[1], Type.TODO);
                        break;
                    case "event":
                        AddTaskCommand event = new AddTaskCommand(this.um, this.tm, command[1], Type.EVENT);
                        event.insert();
                        ts.append(command[1], Type.TODO);
                        break;
                    case "todo":
                        AddTaskCommand todo = new AddTaskCommand(this.um, this.tm, command[1], Type.TODO);
                        todo.insert();
                        ts.append(command[1], Type.TODO);
                        break;
                    case "mark":
                        NumCommand mark = new NumCommand(this.um, this.tm, command[1], Type.MARK);
                        mark.insert();
                        ts.append(command[1], Type.MARK);
                        break;
                    case "unmark":
                        NumCommand unmark = new NumCommand(this.um, this.tm, command[1], Type.UNMARK);
                        unmark.insert();
                        ts.append(command[1], Type.UNMARK);
                        break;
                    case "delete":
                        NumCommand delete = new NumCommand(this.um, this.tm, command[1], Type.DELETE);
                        delete.insert();
                        ts.append(command[1], Type.DELETE);
                }
            } catch (DateException e) {
                um.errorMessage("Invalid date in your saved entry!");
            }
        }
        sc.close();
        um.printLoad(this.tm);
    }

}
