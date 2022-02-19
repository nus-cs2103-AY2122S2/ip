package ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Function;

import bot.Bot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import tasklist.StorageTaskList;
import tasklist.TaskList;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String APP_PATH = "/test";

    private final Function<String, String> processQuery;

    /**
     * Instantiates a ui.Main object that represents the entry point to the GUI
     * of Duke.
     *
     * @throws Exception If any operation related to storage or UI fails.
     */
    public Main() throws Exception {
        final Parser parser = new Parser();
        final ByteArrayOutputStream botOutput = new ByteArrayOutputStream();
        final Ui ui = new Ui(System.in, botOutput, false);
        final TaskList tasklist = new StorageTaskList(new Storage(Main.APP_PATH));
        final Bot duke = new Bot(parser, ui, tasklist);
        this.processQuery = query -> {
            duke.execute(query);
            final String response = botOutput.toString();
            botOutput.reset();
            return response;
        };
    }

    @Override
    public void start(Stage stage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            final AnchorPane ap = fxmlLoader.load();
            final Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setProcessQuery(this.processQuery);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
