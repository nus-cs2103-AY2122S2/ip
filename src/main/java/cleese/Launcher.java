package cleese;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Cleese.initialize();
        Application.launch(Cleese.class, args);
    }
}
