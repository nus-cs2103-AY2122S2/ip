package duke.sonautil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * contains the Duke.task list. Has operations to add/delete tasks in the list
 */
public class TaskList {

    private static int taskAddedIndex;
    private final ArrayList<Task> list;

    /**
     * Constructor for object TaskList
     *
     * @param list an arraylist with Task
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        taskAddedIndex = list.size();
    }

    /**
     * Constructor for object TaskList
     */
    public TaskList() {
        list = new ArrayList<>(100);
        taskAddedIndex = 0;
    }

    /**
     * Executes the user command on the Duke.task list. Does nothing when keyword is "unknown"
     *
     * @param command details of command retrieved from Parser
     * @throws DukeException if command is invalid
     */
    public void executeCommand(String[] command) throws DukeException {
        String keyword = command[0];
        switch (keyword) {
        case "list":
            if (taskAddedIndex == 0) {
                Ui.emptyListMessage();
            } else {
                Ui.showListMessage();
                showList();
                Ui.showLine();
            }

            break;

        case "todo": {
            Task task = new Todo(command[1]);
            addTask(task);
            Ui.todoEnteredSuccessMessage(task, taskAddedIndex);

            break;
        }

        case "deadline": {
            Task task = new Deadline(command[1], LocalDateTime.parse(command[2]));
            addTask(task);
            Ui.deadlineEnterSuccessMessage(task, command[3], taskAddedIndex);

            break;
        }

        case "event": {
            Task task = new Event(command[1], LocalDateTime.parse(command[2]));
            addTask(task);
            Ui.eventEnterSuccessMessage(task, command[3], taskAddedIndex);

            break;
        }

        case "mark": {
            int taskIndex = Integer.parseInt(command[1]);

            //user trying to mark a non-existing Duke.task
            if (taskIndex >= taskAddedIndex || taskIndex < 0) {
                throw new DukeException(Ui.taskDontExistMessage(taskIndex));
            }

            //user trying to mark a done Duke.task as done again
            if (list.get(taskIndex).isDone()) {
                throw new DukeException(Ui.markRepeatMessage());
            }

            list.get(taskIndex).markAsDone();
            Ui.markSuccessMessage(list.get(taskIndex));

            break;
        }

        case "unmark": {
            int taskIndex = Integer.parseInt(command[1]);
            //user trying to unmark a non-existing Duke.task
            if (taskIndex >= taskAddedIndex || taskIndex < 0) {
                throw new DukeException(Ui.taskDontExistMessage(taskIndex));
            }
            //user trying to mark a undone Duke.task as undone again
            if (!list.get(taskIndex).isDone()) {
                throw new DukeException(Ui.unmarkRepeatMessage());
            }
            list.get(taskIndex).unmark();
            Ui.unmarkSuccessMessage(list.get(taskIndex));

            break;
        }

        case "delete": {
            //list is empty
            if (taskAddedIndex == 0) {
                throw new DukeException(Ui.listEmptyMessage());
            }
            int taskIndex = Integer.parseInt(command[1]);
            //user trying to delete a non-existing Duke.task
            if (taskIndex >= taskAddedIndex || taskIndex < 0) {
                throw new DukeException(Ui.taskDontExistMessage(taskIndex));
            }
            Ui.taskRemovedMessage(getTask(taskIndex));
            removeTask(taskIndex);

            break;
        }

        case "schedule":
            //list is empty
            if (taskAddedIndex == 0) {
                throw new DukeException(Ui.scheduleEmptyMessage());
            }
            ArrayList<Task> scheduleList = new ArrayList<>(100);
            LocalDate date = LocalDate.parse(command[1]);

            for (int i = 0; i < taskAddedIndex; i++) {
                if (list.get(i) instanceof Event) {
                    Event task = (Event) list.get(i);
                    if (date.isEqual(task.getAt().toLocalDate())) {
                        scheduleList.add(task);
                    }
                }
                if (list.get(i) instanceof Deadline) {
                    Deadline task = (Deadline) list.get(i);
                    if (date.isEqual((task.getBy().toLocalDate()))) {
                        scheduleList.add(task);
                    }
                }
            }

            if (scheduleList.size() == 0) {
                System.out.println(Ui.scheduleEmptyMessage());
            } else {
                Ui.showSchedule(date.toString());
                for (int i = 0; i < scheduleList.size(); i++) {
                    System.out.println((i + 1) + "." + scheduleList.get(i).toString());
                }
                Ui.showLine();
            }

            break;

        case "find":

            String findWord = command[1];
            ArrayList<Task> searchList = new ArrayList<>(100);

            for (int i = 0; i < taskAddedIndex; i++) {
                String taskDescription = list.get(i).getDescription();
                if (taskDescription.contains(findWord)) {
                    searchList.add(list.get(i));
                }
            }

            if (searchList.size() == 0) {
                Ui.findNoMatchError();

            } else {
                Ui.findShowResult();
                for (int i = 0; i < searchList.size(); i++) {
                    System.out.println((i + 1) + "." + searchList.get(i).toString());
                }
                Ui.showLine();
            }

            break;

        case "unknown":
            //fallthrough

        default:
        }
    }

    /**
     * Returns the Duke.task of the given index
     *
     * @param index index of Duke.task in the list
     * @return Task of the given index
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Prints out all tasks in the list
     */
    private void showList() {
        for (int i = 0; i < taskAddedIndex; i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Adds Duke.task into the list
     *
     * @param task Duke.task to be added into the list
     */
    private void addTask(Task task) {
        list.add(task);
        taskAddedIndex++;
    }

    /**
     * Removes Duke.task from the list
     *
     * @param taskIndex index of the Duke.task to be removed
     */
    private void removeTask(int taskIndex) {
        list.remove(taskIndex);
        taskAddedIndex--;
    }

}
