import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        ui = new Ui();

        ui.greeting();

        storage = new Storage();
        storage.checkFile();

        try {
            taskList = new TaskList(storage.readFile());
        } catch (LoadingException ex) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task> (100));
        }

        parser = new Parser();
    }

    public void run() {
        respond();

        storage.saveFile(taskList.getToDoList());
    }

    private void respond() {
        Scanner sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
            case "bye":
                ui.goodbye();
                break label;
            case "list":
                ui.displayList(taskList.getToDoList());
                sc.nextLine();
                break;
            case "mark":
            case "unmark":
                mark(sc.nextInt());
                sc.nextLine();
                break;
            case "todo":
            case "deadline":
            case "event":
                Task newTask;
                if (command.equals("todo")) {
                    command = sc.nextLine();
                    try {
                        newTask = makeToDo(command);
                    } catch (ToDoIllegalArgumentException ex) {
                        ui.showIllegalArgumentError(ex);
                        break;
                    }
                } else if (command.equals("deadline")) {
                    String[] s = parser.parseDeadline(sc.nextLine());
                    try {
                        newTask = makeDeadline(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        ui.showIncompleteArgumentError();
                        break;
                    }
                } else {
                    String[] s = parser.parseEvent(sc.nextLine());
                    try {
                        newTask = makeEvent(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        ui.showIncompleteArgumentError();
                        break;
                    }
                }
                taskList.add(newTask);
                ui.confirmAddition(newTask, taskList.getToDoList());
                break;
            case "delete":
                remove(sc.nextInt());
                break;
            default:
                ui.doNotUnderstand();
            }
        }
    }


    private void mark(int idx) {
        taskList.get(idx - 1).mark();
        if (taskList.get(idx - 1).getDone()) {
            ui.markAsDone(taskList.getToDoList(), idx);
        } else {
            ui.unmarkAsDone(taskList.getToDoList(), idx);
        }
    }

    private Task makeToDo(String name) throws ToDoIllegalArgumentException {
        if (name.isEmpty()) {
            throw new ToDoIllegalArgumentException("Illegal Argument");
        }
        return new ToDo(name);
    }

    private Task makeDeadline(String name, String by) {
        return new Deadline(name, by.trim());
    }

    private Task makeEvent(String name, String at) {
        return new Event(name, at.trim());
    }

    private void remove(int idx) {
        Task removed = taskList.remove(idx - 1);
        ui.confirmRemoval(removed, taskList.getToDoList());
    }


}