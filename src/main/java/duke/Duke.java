package duke;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Duke extends Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        while (parser.isPolling) {
            String input = sc.nextLine();
            parser.inputHandler(input);
        }

        sc.close();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        helloWorld.setFont(new Font(50));
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
