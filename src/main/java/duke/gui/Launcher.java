package duke.gui;

import javafx.application.Application;

import java.io.File;
import java.io.IOException;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) throws IOException {
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Application.launch(Main.class, args);
    }
}
