package duke.utils;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Command {


    protected String markTaskComplete(TaskList tl, StringTokenizer st) throws DukeException{
        try {
            int toMark = Integer.parseInt(st.nextToken());
            return tl.markTaskAsCompleted(toMark);
        } catch (DukeException | NumberFormatException e) {
            throw new DukeException.DukeInvalidNumberException();
        }
    }


    protected String markTaskIncomplete(TaskList tl, StringTokenizer st) throws DukeException{
        try {
            int toUnmark = Integer.parseInt(st.nextToken());
            return tl.markTaskAsUncomplete(toUnmark);
        } catch (DukeException | NumberFormatException e) {
            throw new DukeException.DukeInvalidNumberException();
        }
    }

    protected String createToDo(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            return tl.addToDo(st.nextToken(""));
        } catch (NoSuchElementException e) {
            throw new DukeException.DukeNoTaskGivenException();
        }
    }

    protected String createDeadline(String userInput, TaskList tl, String curr) throws DukeException {
        try {
            userInput = userInput.replace(curr, "");
            String[] spl = userInput.split("/by ");
            if (spl.length <= 1) {
                throw new DukeException.DukeNoTimeProvided();
            }
            return tl.addDeadline(spl[0], spl[1]);
        } catch (DukeException e) {
            throw e;
        }
    }

    protected String createEvent(String userInput, TaskList tl, String curr) throws DukeException {
        try {
            userInput = userInput.replace(curr, "");
            String[] splo = userInput.split("/at ");
            if (splo.length <= 1) {
                throw new DukeException.DukeNoTimeProvided();
            }
            return tl.addEvent(splo[0], splo[1]);
        } catch (DukeException e) {
            throw e;
        }
    }

    protected String deleteTask(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            int toDelete = Integer.parseInt(st.nextToken());
            return tl.deleteTask(toDelete);
        } catch (NumberFormatException | DukeException e) {
            throw new DukeException.DukeInvalidNumberException();
        }
    }

    protected String findTask(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            return tl.findEvent(st.nextToken(""));
        } catch (NoSuchElementException e) {
            throw new DukeException.DukeNoTaskGivenException();
        }
    }

    protected String sortTasks(TaskList tl, StringTokenizer st) throws DukeException {

        String whichTask = st.nextToken();

        if(whichTask.equals("deadline")) {
            return tl.getDeadlinesSorted();
        } else if (whichTask.equals("event")) {
            return tl.getEventsSorted();
        } else {
            throw new DukeException.DukeInvalidCommandException();
        }
    }
}
