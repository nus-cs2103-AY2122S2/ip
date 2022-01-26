package duke.ui;

import duke.commands.Type;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TextStorage Object that handles storage of Tasks in a text file.
 * Checks for presence of the storage directory.
 * Creates storage directory if absent.
 * Creates text file if absent.
 */
public class TextStorage {
    private File file;
    private boolean isOpen;
    private String content;
    private UiManager uiManager;

    /**
     * Constructs the TextStorage Object.
     *
     * @param um UiManager used for input and output processes
     */
    public TextStorage(UiManager um) {
        this.file = new File("storage");
        if (!file.exists()) {
            file.mkdir();
        }
        this.isOpen = true;
        this.content = "";
        this.uiManager = um;
    }

    private void writeToFile(String string) throws IOException {
        FileWriter fw = new FileWriter("storage/stored.txt");
        fw.write(string);
        fw.close();
    }

    /**
     * Appends Task Object to task list in the form of a string
     *
     * @param task String representing Task Object
     * @param t Type of task object
     */
    public void append(String task, Type t) {
        switch (t) {
            case DEADLINE:
                this.content += "deadline " + task + "\n";
                break;
            case EVENT:
                this.content += "event " + task + "\n";
                break;
            case TODO:
                this.content += "todo " + task + "\n";
                break;
            case MARK:
                this.content += "mark " + task + "\n";
                break;
            case UNMARK:
                this.content += "unmark " + task + "\n";
                break;
            case DELETE:
                this.content += "delete " + task + "\n";
        }

    }

    /**
     * Saves task list into text file.
     * Prints error message if directory is absent.
     */
    public void save() {
        try {
            this.writeToFile(this.content);
        } catch (IOException e) {
            uiManager.errorMessage("Oops! This is not a valid path!\nCheck if the directory exists!");

        }
    }
}
