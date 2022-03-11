package duke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import duke.exceptions.DukeException;
import duke.exceptions.DukeEmptyTaskDescriptionException;
import duke.exceptions.DukeIncorrectIndexException;
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return this.listOfTasks;
    }

    public String addTodo(String task) {
        ToDo toDo = new ToDo(task);
        listOfTasks.add(toDo);
        return Ui.addTaskMessage(toDo);
    }

    public String addDeadline(String taskStatement) throws DukeException {
        if (taskStatement.equals("")) {
            throw new DukeEmptyTaskDescriptionException();
        }
        String description = Parser.getDescription(taskStatement);
        String by = Parser.getDateAndTime(taskStatement);
        Deadline deadline = new Deadline(description, by);
        listOfTasks.add(deadline);
        return Ui.addTaskMessage(deadline);
    }

    public String addEvent(String taskStatement) throws DukeException {
        String description = Parser.getDescription(taskStatement);
        String at = Parser.getDateAndTime(taskStatement);
        Event event = new Event(description, at);
        listOfTasks.add(event);
        return Ui.addTaskMessage(event);
    }

    private boolean isEmptyString(String string) {
        return string.equals("");
    }

    public String markTask(String description) throws DukeException {
        String markAsDone = "I have marked these tasks as done \n";
        String[] indices = description.split(" ");
        for (String indexStr : indices) {
            
            int index = Integer.parseInt(indexStr);
            if(isEmptyString(indexStr) || index > listOfTasks.size()) {
                throw new DukeIncorrectIndexException();
            }
            listOfTasks.get(index - 1).markIsDone();
            markAsDone = markAsDone +  Ui.markAsDone(listOfTasks.get(index - 1));
        }
        return markAsDone;
        
    }

    public String unmarkTask(String description) throws DukeException {
        String markAsDone = "I have marked these tasks as undone \n";
        String[] indices = description.split(" ");
        for (String indexStr : indices) {
            
            int index = Integer.parseInt(indexStr);
            if(isEmptyString(indexStr) || index > listOfTasks.size()) {
                throw new DukeIncorrectIndexException();
            }
            listOfTasks.get(index - 1).unmarkIsDone();
            markAsDone = markAsDone +  Ui.markAsNotDone(listOfTasks.get(index - 1));
        }
        return markAsDone;

    }

    public String deleteTask(String description) throws DukeException {
        String[] indices = description.split(" ");
        String deleteString = "The deleted tasks is/are \n";
        Integer[] indicesInt = new Integer[indices.length];
        int i = 0;
        for (String indexStr : indices) {
            int index = Integer.parseInt(indexStr);
            if(isEmptyString(indexStr) || index > listOfTasks.size()) {
                throw new DukeIncorrectIndexException();
            }
            indicesInt[i] = Integer.parseInt(indexStr);
            i++;
        }
        Arrays.sort(indicesInt, Collections.reverseOrder());
        for (int index : indicesInt) {
            assert index <= listOfTasks.size();
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            deleteString += Ui.deleteMessage(task, listOfTasks.size());

        }
        return deleteString + "You now have " + listOfTasks.size() + " tasks";

    }

    public String findTask(String description, ArrayList<Task> taskList) throws DukeException {
        if (description.equals("")) {
            throw new DukeEmptyTaskDescriptionException();
        }
        assert !description.equals("");
        ArrayList<Task> temp = new ArrayList<Task>();

        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                temp.add(task);
            }
        }
        return Ui.findMessage(temp);
    }



}
