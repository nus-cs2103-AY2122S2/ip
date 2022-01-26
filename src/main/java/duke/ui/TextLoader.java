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

public class TextLoader {
    private final UiManager uiManager;
    private final TaskManager taskManager;
    private final TextStorage storage;


    public TextLoader(UiManager uiManager, TaskManager taskManager, TextStorage textStorage) {
        this.uiManager = uiManager;
        this.taskManager = taskManager;
        this.storage = textStorage;
    }

    public void load() throws FileNotFoundException, TaskIndexException {
        File f = new File("storage/stored.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()) {
            try {
                String[] command = uiManager.parseCommand(sc.nextLine());
                switch (command[0]) {
                case "deadline":
                    AddTaskCommand deadline =
                            new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.DEADLINE);
                    deadline.insert();
                    storage.append(command[1], Type.TODO);
                    break;
                case "event":
                    AddTaskCommand event = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.EVENT);
                    event.insert();
                    storage.append(command[1], Type.TODO);
                    break;
                case "todo":
                    AddTaskCommand todo = new AddTaskCommand(this.uiManager, this.taskManager, command[1], Type.TODO);
                    todo.insert();
                    storage.append(command[1], Type.TODO);
                    break;
                case "mark":
                    NumCommand mark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.MARK);
                    mark.insert();
                    storage.append(command[1], Type.MARK);
                    break;
                case "unmark":
                    NumCommand unmark = new NumCommand(this.uiManager, this.taskManager, command[1], Type.UNMARK);
                    unmark.insert();
                    storage.append(command[1], Type.UNMARK);
                    break;
                case "delete":
                    NumCommand delete = new NumCommand(this.uiManager, this.taskManager, command[1], Type.DELETE);
                    delete.insert();
                    storage.append(command[1], Type.DELETE);
                    break;
                }
            } catch (DateException e) {
                uiManager.showErrorMessage("Invalid date in your saved entry!");
            }
        }
        sc.close();
        uiManager.printLoad(this.taskManager);
    }

}
