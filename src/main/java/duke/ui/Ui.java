package duke.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import duke.Duke;
import duke.exception.DukeException;
import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeIoException;
import duke.util.Printable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ui {
    private static final String SEPARATOR = "\t------------------------------------";
    private static final String BOT_NAME = "Megumin";
    private static final String ERROR_INVALID_COMMAND = "I do not understand you!";
    private static final String ERROR_INVALID_SYNTAX = "There was a problem understanding you:";
    private static final String ERROR_IO = "I had a problem reading / writing my memory!";

    private static Ui instance;
    private MainWindow mainWindowController;

    private Ui() {

    }

    /**
     * Inflates the MainWindow user-interface onto the supplied stage and attaches the supplied input
     * handler.
     * @param stage The JavaFX stage to create the user-interface on.
     * @param inputHandler The handler to be fired when the user enters some input.
     * @throws DukeIoException If the MainWindow layout FXML file cannot be found.
     */
    public void buildStage(Stage stage, Consumer<String> inputHandler) throws DukeIoException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            this.mainWindowController = fxmlLoader.getController();
            this.mainWindowController.setInputHandler(inputHandler);
        } catch (IOException e) {
            throw new DukeIoException("Unable to read MainWindow Layout file");
        }
    }

    /**
     * Prints a greeting for the initial startup.
     * @return The global instance of the <code>Ui</code> class.
     */
    public Ui greet() {
        printCommand((linePrinter) -> {
            linePrinter.print("Hi! I'm " + BOT_NAME);
            linePrinter.print("What do you need?");
            return true;
        });
        return this;
    }

    /**
     * Provides a {@link Printable} object that can be printed to.
     * Handles formatting of printed contents, including indentation and surrounding separators.
     * @param action A <code>Function</code> object that accepts the provided {@link Printable} object
     *               and returns a boolean to indicate if the application should continue running.
     * @return <code>Boolean</code> indicating if the application should continue running.
     */
    public boolean printCommand(Function<Printable, Boolean> action) {
        ArrayList<String> lines = new ArrayList<>();
        final boolean isRunning = action.apply((line) -> {
            lines.add(line);
        });
        String message = lines.stream().reduce((x, y) -> x + "\n" + y).orElse("");
        this.mainWindowController.printBotMessage(message);
        return isRunning;
    }

    /**
     * Prints an error message onto the {@link Printable} object provided.
     * Error message printer depends on the type of the provided {@link DukeException} object.
     * @param linePrinter {@link Printable} object that the error should be printed to.
     * @param ex Exception object that the error message should be printed for.
     */
    public void printError(Printable linePrinter, DukeException ex) {
        if (ex instanceof DukeInvalidCommandException) {
            linePrinter.print(ERROR_INVALID_COMMAND);
        } else if (ex instanceof DukeIllegalArgumentException) {
            linePrinter.print(ERROR_INVALID_SYNTAX);
            linePrinter.print(ex.getMessage());
        } else if (ex instanceof DukeIoException) {
            linePrinter.print(ERROR_IO);
        }
    }

    /**
     * Returns the global instance of the {@link Ui} class.
     * @return Global instance of the <code>Ui</code> class.
     */
    public static Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }
}
