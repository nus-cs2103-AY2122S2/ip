package sana.command;

import sana.TaskList;
import sana.exception.IncompleteCommandException;
import sana.task.Deadline;
import sana.task.Event;
import sana.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTask extends Command {
    @Override
    public String executeCommand (String[] args, TaskList taskList) {
        try {
            if (args[0].equals("todo")) { // task is a todo
                String todoName = args[1];
                if (todoName.isBlank()) {
                    throw new IncompleteCommandException();
                }

                ToDo newTodo = new ToDo(todoName);
                taskList.addTask(newTodo);

                memory.updateMemory(taskList.toList());

                return sanaResponse.addNewTask(newTodo, taskList.taskAmt());
            } else if (args[0].equals("event")) { // task is an event
                String eventName = args[1];
                LocalDate eventTime = LocalDate.parse(args[2]);
                if (eventName.isBlank()) {
                    throw new IncompleteCommandException();
                }

                Event newEvent = new Event(eventName, eventTime);
                taskList.addTask(newEvent);

                memory.updateMemory(taskList.toList());

                return sanaResponse.addNewTask(newEvent, taskList.taskAmt());
            } else { // task is a deadline
                String deadlineName = args[1];
                LocalDate deadlineDate = LocalDate.parse(args[2]);
                if (deadlineName.isBlank()) {
                    throw new IncompleteCommandException();
                }

                Deadline newDeadline = new Deadline(deadlineName, deadlineDate);
                taskList.addTask(newDeadline);

                memory.updateMemory(taskList.toList());

                return sanaResponse.addNewTask(newDeadline, taskList.taskAmt());
            }
        } catch (IncompleteCommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return sanaResponse.dateFormatError();
        }
    }
}
