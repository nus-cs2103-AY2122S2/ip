import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private Scanner sc = new Scanner(System.in);
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    public void readUsrInput() throws IOException {
        whileLoop:
        while (sc.hasNextLine()) {
            String usrInput = sc.next();
            String task;
            switch (usrInput) {
            case "bye":
                ui.exit();
                sc.close();
                break whileLoop;
            case "list":
                ui.list(taskList);
                break;
            case "mark": {
                int taskNum = Integer.parseInt(sc.next());
                Task markedTask = taskList.mark(taskNum);
                ui.markTask(markedTask);
                storage.save(taskList);
                break;
            }
            case "unmark": {
                int taskNum = Integer.parseInt(sc.next());
                Task unmarkedTask = taskList.unmark(taskNum);
                ui.unmarkTask(unmarkedTask);
                storage.save(taskList);
                break;
            }
            case "delete": {
                int taskNum = Integer.parseInt(sc.next());
                Task deletedTask = taskList.delete(taskNum);
                ui.deleteTask(taskList, deletedTask);
                storage.save(taskList);
                break;
            }
            case "todo": {
                usrInput = sc.nextLine();
                if (usrInput.equals("")) {
                    ui.throwError("todo");
                    break;
                }
                taskList.addTodo(usrInput.substring(1));
                ui.addTask(taskList);
                storage.save(taskList);
                break;
            }
            case "deadline": {
                task = sc.next();
                while (sc.hasNext()) {
                    String currStr = sc.next();
                    if (currStr.equals("/by")) {
                        String time = sc.nextLine();
                        time = time.substring(1);
                        taskList.addDeadline(task, time);
                        ui.addTask(taskList);
                        storage.save(taskList);
                        break;
                    } else {
                        task += " " + currStr;
                    }
                }
                break;
            }
            case "event": {
                task = sc.next();
                while (sc.hasNext()) {
                    String currStr = sc.next();
                    if (currStr.equals("/at")) {
                        String time = sc.nextLine();
                        time = time.substring(1);
                        taskList.addEvent(task, time);
                        ui.addTask(taskList);
                        storage.save(taskList);
                        break;
                    } else {
                        task += " " + currStr;
                    }
                }
                break;
            }
            default:
                ui.throwError("");
                break;
            }
        }
    }
}
