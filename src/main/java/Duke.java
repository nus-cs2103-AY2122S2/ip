public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Menu menu = new Menu(taskList);
        menu.run();
    }
}