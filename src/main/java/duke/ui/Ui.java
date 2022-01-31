package duke.ui;

import duke.command.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.dukeexceptions.InvalidCommand;
import duke.praser.Parser;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.uicomponents.DeleteTaskDisplay;
import duke.uicomponents.ErrorMenu;
import duke.uicomponents.ExitScreen;
import duke.uicomponents.MarkTaskDisplay;
import duke.uicomponents.Menu;
import duke.uicomponents.NewTaskDisplay;
import duke.uicomponents.TaskListDisplay;
import duke.uicomponents.UnmarkedTaskDisplay;

public class Ui {
    Menu menu;
    ExitScreen exitScreen;
    ErrorMenu errorMenu;
    public TaskListDisplay taskListDisplay;
    MarkTaskDisplay markTaskDisplay;
    UnmarkedTaskDisplay unmarkedTaskDisplay;
    NewTaskDisplay newTaskDisplay;
    DeleteTaskDisplay deleteTaskDisplay;

    public Ui() {
        menu = new Menu();
        exitScreen = new ExitScreen();
        taskListDisplay = new TaskListDisplay();
        markTaskDisplay = new MarkTaskDisplay();
        unmarkedTaskDisplay = new UnmarkedTaskDisplay();
        newTaskDisplay = new NewTaskDisplay();
        deleteTaskDisplay = new DeleteTaskDisplay();
        errorMenu = new ErrorMenu();
    }

    public void showMenu() {
        menu.run();
    }

    public Command showUserCommandLine(String userInput) throws InvalidCommand {
        return Parser.parse(userInput);
    }

    public void showCommandError(DukeExceptions exceptions) {
        errorMenu.run(exceptions.getMessage());
    }

    public void showExitScreen() {
        exitScreen.run();
    }

    void showTaskListDisplay(TaskList taskList) {
        taskListDisplay.run(taskList);
    }

    public void showMarkedTaskDisplay(String task) {
        markTaskDisplay.run(task);
    }

    public void showUnmarkedTaskDisplay(String task) {
        unmarkedTaskDisplay.run(task);
    }

    public void newTaskDisplay(Task task, TaskList taskList) {
        newTaskDisplay.run(task, taskList);
    }

    public void showDeleteTaskDisplay(String task, TaskList taskList) {
        deleteTaskDisplay.run(task, taskList);
    }
}
