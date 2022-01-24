import duke.*;
import tasks.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("Data/tasks.txt").run();
    }

    public void run() throws DukeException, IOException {
        ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        String str = sc.nextLine();
        List<Task> tasks = taskList.getTasks();
        while (!str.equals("bye")) {
            try {
                str = parser.parse(str);
                if (str.equals("list")) {
                    for (Task t : tasks) {
                        System.out.println(t);
                    }
                } else {
                    String[] temp = str.split(" ");
                    if (temp[0].equals("unmark") || temp[0].equals("mark") || temp[0].equals("delete")) {
                        int taskNumber = Integer.parseInt(temp[1]);
                        if (taskList.getTasks().size() < taskNumber) {
                            System.out.println("Invalid Task number!");
                        } else {
                            if (temp[0].equals("mark")) {
                                Task currTask = tasks.get(taskNumber - 1);
                                currTask.setDone();
                                System.out.println("Nice! I've marked this task as done: \n" + "  " + currTask);
                            } else if (temp[0].equals("delete")) {
                                int index = Integer.parseInt(str.substring(7));
                                Task task = tasks.get(index - 1);
                                tasks.remove(index - 1);
                                System.out.println("Okay, I have deleted " + task);
                            } else {
                                Task currTask = tasks.get(taskNumber - 1);
                                currTask.setNotDone();
                                System.out.println("OK, I've marked this task as not done yet:: \n" + "  " + currTask);
                            }
                        }
                    } else {
                        if (temp[0].equals("todo")) {
                            Todo todo = new Todo(str.substring(5));
                            taskList.addTask(todo);
                        } else if (temp[0].equals("event")) {
                            Event event = new Event(str.substring(6));
                            taskList.addTask(event);
                        } else if (temp[0].equals("deadline")) {
                            Deadline deadline = new Deadline(str.substring(9));
                            taskList.addTask(deadline);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                str = sc.nextLine();
            }
        }
        storage.save(taskList);
        ui.printByeMessage();
        sc.close();
    }
}

