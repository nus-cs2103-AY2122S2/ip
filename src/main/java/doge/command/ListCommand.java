package doge.command;

import doge.Doge;
import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;
import doge.exception.ListTasksDueException;
import doge.task.Deadline;
import doge.task.Event;
import doge.task.Task;

import java.time.LocalDateTime;

public class ListCommand extends Command {
    private String message;

    public ListCommand(String details) {
        super(details);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        if (this.details.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n").append(i + 1).append(") ➜ ").append(tasks.getTask(i));
            }
        } else {
            LocalDateTime dueDateTime;
            String[] tempStr = this.details.split(" ");

            try {
                dueDateTime = Doge.getDateTime(tempStr[1].trim() + " " + tempStr[2].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new ListTasksDueException("Please state an appropriate duration for the occurrence?");
            }

            int numbering = 1;

            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.getTask(i);
                LocalDateTime currDateTime;

                if (currTask instanceof Deadline) {
                    Deadline currDeadline = (Deadline) currTask;
                    currDateTime = currDeadline.getDateTime();
                } else if (currTask instanceof Event) {
                    Event currEvent= (Event) currTask;
                    currDateTime = currEvent.getDateTime();
                } else {
                    continue;
                }

                if (currDateTime != null) {
                    switch (tempStr[0].trim()) {
                    case "=":
                        if (!currDateTime.isEqual(dueDateTime)) {
                            continue;
                        }
                        break;
                    case ">":
                        if (!currDateTime.isAfter(dueDateTime)) {
                            continue;
                        }
                        break;
                    case "<":
                        if (!currDateTime.isBefore(dueDateTime)) {
                            continue;
                        }
                        break;
                    case ">=":
                        if (!currDateTime.isAfter(dueDateTime) || !currDateTime.isEqual(dueDateTime)) {
                            continue;
                        }
                        break;
                    case "<=":
                        if (!currDateTime.isBefore(dueDateTime) || !currDateTime.isEqual(dueDateTime)) {
                            continue;
                        }
                        break;
                    default:
                        throw new DogeException("Invalid limiter specified!");
                    }
                }
                output.append("\n").append(numbering).append(") ➜ ").append(currTask);
                numbering++;
            }
        }
        this.message = output.toString();
    }

    @Override
    public String toString() {
        return message;
    }
}
