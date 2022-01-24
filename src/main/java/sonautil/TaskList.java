package sonautil;

import task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * contains the task list. Has operations to add/delete tasks in the list
 */
public class TaskList {

    private final ArrayList<Task> list;
    private static int tasksAdded_index;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        tasksAdded_index = list.size();
    }

    public TaskList() {
        list = new ArrayList<>(100);
        tasksAdded_index = 0;
    }

    /**
     * Executes the user command on the task list. Does nothing when keyword is "unknown"
     *
     * @param command details of command retrieved from Parser
     * @throws DukeException if command is invalid
     */
    public void executeCommand(String[] command) throws DukeException {
        String keyword = command[0];
        switch (keyword) {
            case "list":
                if (tasksAdded_index == 0) {
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
                Ui.todoEnteredSuccessMessage(task, tasksAdded_index);

                break;
            }

            case "deadline": {
                Task task = new Deadline(command[1], LocalDateTime.parse(command[2]));
                addTask(task);
                Ui.deadlineEnterSuccessMessage(task, command[3], tasksAdded_index);

                break;
            }

            case "event": {
                Task task = new Event(command[1], LocalDateTime.parse(command[2]));
                addTask(task);
                Ui.eventEnterSuccessMessage(task, command[3], tasksAdded_index);

                break;
            }

            case "mark": {
                int taskIndex = Integer.parseInt(command[1]);

                //user trying to mark a non-existing task
                if (taskIndex >= tasksAdded_index || taskIndex < 0) {
                    throw new DukeException(Ui.taskDontExistMessage(taskIndex));
                }

                //user trying to mark a done task as done again
                if (list.get(taskIndex).isDone) {
                    throw new DukeException(Ui.markRepeatMessage());
                }

                list.get(taskIndex).markAsDone();
                Ui.markSuccessMessage(list.get(taskIndex));

                break;
            }

            case "unmark": {
                int taskIndex = Integer.parseInt(command[1]);
                //user trying to unmark a non-existing task
                if (taskIndex >= tasksAdded_index || taskIndex < 0) {
                    throw new DukeException(Ui.taskDontExistMessage(taskIndex));
                }
                //user trying to mark a undone task as undone again
                if (!list.get(taskIndex).isDone) {
                    throw new DukeException(Ui.unmarkRepeatMessage());
                }
                list.get(taskIndex).unmark();
                Ui.unmarkSuccessMessage(list.get(taskIndex));

                break;
            }

            case "delete": {
                //list is empty
                if (tasksAdded_index == 0) {
                    throw new DukeException(Ui.listEmptyMessage());
                }
                int taskIndex = Integer.parseInt(command[1]);
                //user trying to delete a non-existing task
                if (taskIndex >= tasksAdded_index || taskIndex < 0) {
                    throw new DukeException(Ui.taskDontExistMessage(taskIndex));
                }
                Ui.taskRemovedMessage(getTask(taskIndex));
                removeTask(taskIndex);

                break;
            }

            case "schedule":
                //list is empty
                if (tasksAdded_index == 0) {
                    throw new DukeException(Ui.scheduleEmptyMessage());
                }
                ArrayList<Task> scheduleList = new ArrayList<>(100);
                LocalDate date = LocalDate.parse(command[1]);

                for (int i = 0; i < tasksAdded_index; i++) {
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

            case "unknown":

        }
    }

    /**
     * Returns the task of the given index
     *
     * @param index index of task in the list
     * @return Task of the given index
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Prints out all tasks in the list
     */
    private void showList() {
        for (int i = 0; i < tasksAdded_index; i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Adds task into the list
     *
     * @param task task to be added into the list
     */
    private void addTask(Task task) {
        list.add(task);
        tasksAdded_index ++;
    }

    /**
     * Removes task from the list
     *
     * @param taskIndex index of the task to be removed
     */
    private void removeTask(int taskIndex) {
        list.remove(taskIndex);
        tasksAdded_index --;
    }

}
