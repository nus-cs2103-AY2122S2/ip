package duke;

public class Duke {

    public static void main(String[] args) {
        // load in the TaskList
        TaskList taskList = new TaskList(new Storage());
        Ui.initialize();
        Storage.save();

    }


}

