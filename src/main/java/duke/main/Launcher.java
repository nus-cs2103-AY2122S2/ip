package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

//TODO
/*
1. Try creating another stage and showing it! What happens?
2. Add padding between each DialogBox
3. Add padding between each ImageView and its Label
4. Clip the ImageView into a circle
5. Add background color to each dialog box
7. Extend MainWindow to have a Stage as a root Node.
8. Customize the appearance of the application further with CSS.

 */
