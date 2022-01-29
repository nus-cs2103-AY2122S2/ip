package duke;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises duke.Duke the chat-bot along with its necessary classes and files.
     *
     * @param filePath Path directory of the saved file containing the list of tasks.
     */
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

    /**
     * Runs duke.Duke the chatbot and interacts with the user based on user input.
     *
     * @throws DukeException
     * @throws IOException
     */
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
                            storage.save(taskList);
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
                        } else if (temp[0].equals("find")) {
                            System.out.println("Listed below are the matching tasks in your list: ");
                            for (Task t : taskList.getTasks()) {
                                if (t.toString().contains(str.substring(5))) {
                                    System.out.println(t);
                                }
                            }
                        }
                        storage.save(taskList);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                str = sc.nextLine();
            }
        }
        ui.printByeMessage();
        sc.close();
    }
}

