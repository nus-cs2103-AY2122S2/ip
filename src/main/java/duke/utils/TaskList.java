package duke.utils;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;



public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> saved){
        this.tasks = saved;
    }

    public void addToDo(String desc) throws DukeException.DukeNoTaskGivenException {

        if (desc.replace(" ", "").equals("")) {
            throw new DukeException.DukeNoTaskGivenException();
        }
        ToDo curr = new ToDo(desc, false);
        this.tasks.add(curr);
        Ui.printTaskAddition(curr, getSize());
    }

    public void addDeadline(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Deadline curr = new Deadline(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            Ui.printTaskAddition(curr, getSize());
        } catch (DateTimeParseException e) {

            throw new DukeException.DukeNoTimeProvided();
        }
    }

    public void addEvent(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Event curr = new Event(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            Ui.printTaskAddition(curr, getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeNoTimeProvided();
        }
    }


    public void deleteTask(int toDelete) throws DukeException.DukeInvalidNumberException {
        if(toDelete < 0 || toDelete > tasks.size()){
            throw new DukeException.DukeInvalidNumberException();
        }
        Task toBeRemoved = tasks.get(toDelete - 1);
        this.tasks.remove(toDelete - 1);
        Ui.printTaskDeletion(toBeRemoved, getSize());
    }

    public void saveListToStorage(){
        Storage.saveListToDisk(tasks);
    }

    public void markTaskAsCompleted(int toMark) throws DukeException.DukeInvalidNumberException {
        if(toMark < 0 || toMark > tasks.size()) {
            throw new DukeException.DukeInvalidNumberException();
        }
        tasks.get(toMark - 1).markCompleted();
    }

    public void markTaskAsUncomplete(int toUnmark) throws DukeException.DukeInvalidNumberException {
        if(toUnmark < 0 || toUnmark > tasks.size()) {
            throw new DukeException.DukeInvalidNumberException();
        }
        tasks.get(toUnmark - 1).markNotCompleted();
    }

    public void printList(){
        Ui.printList(tasks);
    }

    public int getSize(){
        return tasks.size();
    }

    public void findEvent(String desc) {
        ArrayList<Task> matches = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(desc)) {
                matches.add(tasks.get(i));
            }
        }

        Ui.printSearchList(matches);
    }




}
