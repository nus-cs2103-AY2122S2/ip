public class Duke {
    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage("data/duke.txt", taskList);

    public static void main(String[] args) {
        new Duke().listen();
    }

    private void listen(){
        Ui.printLogo();
        boolean running = true;
        while (running) {
            running = Ui.run(taskList);
            storage.write();
        }
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

}
