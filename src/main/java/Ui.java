import java.util.Scanner;

public class Ui {
    Menu menu;
    ExitScreen exitScreen;
    ErrorMenu errorMenu;
    TaskListDisplay taskListDisplay;
    MarkTaskDisplay markTaskDisplay;
    UnmarkedTaskDisplay unmarkedTaskDisplay;
    NewTaskDisplay newTaskDisplay;
    DeleteTaskDisplay deleteTaskDisplay;

    Ui() {
        this.menu = new Menu();
        this.exitScreen = new ExitScreen();
        this.taskListDisplay = new TaskListDisplay();
        this.markTaskDisplay = new MarkTaskDisplay();
        this.unmarkedTaskDisplay = new UnmarkedTaskDisplay();
        this.newTaskDisplay = new NewTaskDisplay();
        this.deleteTaskDisplay = new DeleteTaskDisplay();
        this.errorMenu = new ErrorMenu();
    }

    void showMenu() {
        menu.run();
    }

    Command showUserCommandLine(String userInput) throws InvalidCommand {
        return Praser.prase(userInput);
    }

    void showCommandError(DukeExceptions exceptions) {
        this.errorMenu.run(exceptions.getMessage());
    }

    void showExitScreen() {
        this.exitScreen.run();
    }

    void showTaskListDisplay(TaskList taskList) {
        this.taskListDisplay.run(taskList);
    }

    void showMarkedTaskDisplay(String task) {
        this.markTaskDisplay.run(task);
    }

    void showUnmarkedTaskDisplay(String task) {
        this.unmarkedTaskDisplay.run(task);
    }

    public void newTaskDisplay(Task task, TaskList taskList) {
        this.newTaskDisplay.run(task, taskList);
    }

    public void showDeleteTaskDisplay(String task, TaskList taskList) {
        this.deleteTaskDisplay.run(task, taskList);
    }
}
