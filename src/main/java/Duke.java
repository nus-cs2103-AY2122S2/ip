public class Duke extends Exception {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    // public static void main(String args) throws IOException {

    // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // String bot = "";
    // String response = "";

    // do {
    // response = reader.readLine().trim();
    // if (response.equals(Commands.BYE.toString())) {
    // printLines();
    // System.out.println("Bye. Hope to see you again soon!");
    // printLines();
    // break;
    // } else if (response.equals(Commands.LIST.toString())) {
    // printLines();
    // System.out.println("Here are the tasks in your list:");
    // listAllTasks(tasks);
    // printLines();
    // // Handle Mark command
    // } else {
    // boolean valid = true;
    // Task newTask = null;

    // printLines();

    // if (response.equals("")) {
    // valid = false;
    // } else {
    // String[] split = response.split("\\s+");
    // if (split.length > 1 && split[0].equals(Commands.MARK.toString()) ||
    // split[0].equals(Commands.UNMARK.toString()) ||
    // split[0].equals(Commands.DELETE.toString())) {
    // try {
    // int digit = Integer.parseInt(split[1]);
    // if (digit > 0 && digit <= tasks.size()) {
    // handleResponse(split[0]);
    // if (split[0].equals(Commands.MARK.toString())) {
    // Task t = tasks.get(digit - 1);
    // t.mark();
    // System.out.println(t.toString());
    // } else if (split[0].equals(Commands.UNMARK.toString())) {
    // Task t = tasks.get(digit - 1);
    // t.unmark();
    // System.out.println(t.toString());
    // } else if (split[0].equals(Commands.DELETE.toString())) {
    // System.out.println(tasks.get(digit - 1).toString());
    // tasks.remove(digit - 1);
    // printNumberOfTask(tasks);
    // } else {
    // valid = false;
    // }
    // } else {
    // System.out.println("Task does not exist! Try again.");
    // }
    // } catch (Exception e) {
    // System.out.println("Invalid number. Try again!");
    // }
    // } else {
    // if (split.length > 1) {
    // String[] secondSplit;
    // if (split[0].equals(Commands.TODO.toString())) {
    // newTask = new Todo(removeSubString(response, "todo "));
    // } else if (split[0].equals(Commands.DEADLINE.toString())) {
    // secondSplit = response.split(" /by ");
    // if (secondSplit.length > 1) {
    // newTask = new Deadline(removeSubString(secondSplit[0], "deadline "),
    // secondSplit[1]);
    // } else {
    // valid = false;
    // }
    // } else if (split[0].equals(Commands.EVENT.toString())) {
    // secondSplit = response.split(" /at ");
    // if (secondSplit.length > 1) {
    // newTask = new Event(removeSubString(secondSplit[0], "event "),
    // secondSplit[1]);
    // } else {
    // valid = false;
    // }
    // } else {
    // valid = false;
    // }
    // if (valid) {
    // handleResponse(split[0]);
    // tasks.add(newTask);
    // System.out.println(newTask.toString());
    // printNumberOfTask(tasks);
    // }
    // } else {
    // valid = false;
    // }
    // }
    // }
    // if (valid == false) {
    // System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means
    // :-(");
    // } else {
    // oos.writeObject(tasks);
    // oos.close();
    // }
    // printLines();
    // }
    // } while (!response.equals(Commands.BYE.toString()));
    // }

    // ____________________________________________________________
    // START OF HELPER METHODS
    // ____________________________________________________________

    // public static void listAllTasks(ArrayList<Task> tasks) {
    // if (tasks.size() == 0) {
    // System.out.println("You have 0 task at the moment. Start by adding these
    // commands:\n");
    // printHelp();
    // } else {
    // for (int i = 0; i < tasks.size(); i++) {
    // System.out.println(i + 1 + ". " + tasks.get(i).toString());
    // }
    // }
    // }

    public static String removeSubString(String response, String word) {
        return response.replace(word, "");
    }

    // ____________________________________________________________
    // START OF COMMAND HANDLER
    // ____________________________________________________________

}
