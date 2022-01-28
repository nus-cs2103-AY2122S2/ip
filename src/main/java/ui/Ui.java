package duke.ui;

import duke.command.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.dukeexceptions.InvalidCommand;
import duke.praser.Praser;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.uicomponents.*;

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
        this.menu = new Menu();
        this.exitScreen = new ExitScreen();
        this.taskListDisplay = new TaskListDisplay();
        this.markTaskDisplay = new MarkTaskDisplay();
        this.unmarkedTaskDisplay = new UnmarkedTaskDisplay();
        this.newTaskDisplay = new NewTaskDisplay();
        this.deleteTaskDisplay = new DeleteTaskDisplay();
        this.errorMenu = new ErrorMenu();
    }

    public void showMenu() {
        menu.run();
    }

    public Command showUserCommandLine(String userInput) throws InvalidCommand {
        return Praser.prase(userInput);
    }

    public void showCommandError(DukeExceptions exceptions) {
        this.errorMenu.run(exceptions.getMessage());
    }

    public void showExitScreen() {
        this.exitScreen.run();
    }

    void showTaskListDisplay(TaskList taskList) {
        this.taskListDisplay.run(taskList);
    }

    public void showMarkedTaskDisplay(String task) {
        this.markTaskDisplay.run(task);
    }

    public void showUnmarkedTaskDisplay(String task) {
        this.unmarkedTaskDisplay.run(task);
    }

    public void newTaskDisplay(Task task, TaskList taskList) {
        this.newTaskDisplay.run(task, taskList);
    }

    public void showDeleteTaskDisplay(String task, TaskList taskList) {
        this.deleteTaskDisplay.run(task, taskList);
    }
}
