package chatbot;

import chatbot.command.CommandList;
import chatbot.command.DeadlineCommand;
import chatbot.command.DeleteCommand;
import chatbot.command.EventCommand;
import chatbot.command.FindCommand;
import chatbot.command.HelpCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.OwoCommand;
import chatbot.command.ResetCommand;
import chatbot.command.TerminateCommand;
import chatbot.command.ToDoCommand;
import chatbot.command.UnmarkCommand;
import chatbot.command.UwuCommand;
import chatbot.gui.MainWindow;
import chatbot.task.TaskList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Task management chat bot.
 */
public class ChatBot extends Application {
    public static final String APPLICATION_NAME = "Delphine v1.0";
    public static final String SAVE_FILE = "./data/save_file";

    private final CommandList commandList;
    private final TaskList taskList;

    public ChatBot() {
        this.taskList = TaskList.create(SAVE_FILE);
        this.commandList = new CommandList();
        this.commandList.addCommand(new TerminateCommand());
        this.commandList.addCommand(new DeadlineCommand());
        this.commandList.addCommand(new DeleteCommand());
        this.commandList.addCommand(new EventCommand());
        this.commandList.addCommand(new ListCommand());
        this.commandList.addCommand(new MarkCommand());
        this.commandList.addCommand(new ToDoCommand());
        this.commandList.addCommand(new UnmarkCommand());
        this.commandList.addCommand(new ResetCommand());
        this.commandList.addCommand(new FindCommand());
        this.commandList.addCommand(new HelpCommand());
        this.commandList.addCommand(new UwuCommand());
        this.commandList.addCommand(new OwoCommand());
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/MainWindow.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));

            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setCommandList(commandList);
            mainWindow.setTaskList(taskList);
            mainWindow.setTerminateCallback(() -> {
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> {
                    stage.close();
                });
                delay.play();
            });

            stage.setTitle(APPLICATION_NAME);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}