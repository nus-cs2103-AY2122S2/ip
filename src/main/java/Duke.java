import java.util.Scanner;
import java.util.ArrayList;

import javafx.application.Application;
import tasks.*;
import backend.InputDecoder;
import backend.TaskList;
import exception.DukeException;
import storage.Storage;


public class Duke {
    private static Storage storage;
    private static InputDecoder inputDecoder;

    public Duke() {
        this.storage = new Storage();
        this.inputDecoder = new InputDecoder();
        ArrayList<Task> tasks = storage.loadList();
        TaskList.initialise(tasks);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    public String getResponse(String input) {
        String output = inputDecoder.decode(input);
        storage.updateTasks(TaskList.getTasks());
        return output;
    }
}
