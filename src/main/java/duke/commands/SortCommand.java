package duke.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;


public class SortCommand extends Command {

    public enum SortType {
        Alphabetical("alphabetical", SortCommand::sortAlphabetically),
        Chronological("chronological", SortCommand::sortChronologically);

        private static final Map<String, SortType> BY_LABEL = new HashMap<>();

        static {
            for (SortType s: values()) {
                BY_LABEL.put(s.stringRepresentation, s);
            }
        }

        private final String stringRepresentation;
        private final Function<TaskList, ArrayList<String>> sortFunction;

        SortType(String stringRepresentation, Function<TaskList, ArrayList<String>> sortFunction) {
            this.stringRepresentation = stringRepresentation;
            this.sortFunction = sortFunction;
        }

        private static SortType valueOfLabel(String stringRepresentation) {
            return BY_LABEL.get(stringRepresentation);
        }

        private static ArrayList<String> getStringRepresentations() {
            return new ArrayList<>(BY_LABEL.keySet());
        }
    }
    protected SortType sortType = null;
    /**
     * Constructor of a sort command. Specifies that a sort command
     * requires storage to file.
     */
    public SortCommand() {
        modifyData = true;
        exitProgram = false;
    }

    /**
     * Handles user input and stores creates the enum which defines how
     * to sort the task list.
     * @param tokens a String array that represents the user input.
     * @throws DukeException when the input provided cannot be resolved to a valid sort
     *                       type.
     */
    @Override
    public void handleParam(String[] tokens) throws DukeException {
        try {
            sortType = SortType.valueOfLabel(tokens[1]);
        } catch (Exception exception) {
            throw new DukeException("Please enter a valid way to sort the task!");
        }

        if (sortType == null) {
            String output = "Invalid sorting method provided! Please select from one of these:\n";
            for (String s : SortType.getStringRepresentations()) {
                output += s + "\n";
            }
            throw new DukeException(output.trim());
        }
    }

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        return input.equals("sort");
    }

    /**
     * Executes the SortCommand object and returns a notification
     * to show the user the contents of the sorted task list.
     *
     * @param taskList a container of existing tasks in the program, used to
     *                 acquire the task to be deleted.
     * @return a String that contains the notification to show user the sorted
     *         content of the task list.
     */
    @Override
    public String execute(TaskList taskList) {
        assert taskList != null : "Task list provided must not be null.";
        assert sortType != null : "Sort type must be defined";
        ArrayList<String> taskSet = sortType.sortFunction.apply(taskList);
        String output = "Task list sorted! Here are the tasks in your list:" + "\n";
        for (int i = 0; i < taskSet.size(); i++) {
            output += i + 1 + ". " + taskSet.get(i) + "\n\n";
        }
        return output;
    }

    private static ArrayList<String> sortAlphabetically(TaskList taskList) {
        return taskList.sortAlphabetically();
    }

    private static ArrayList<String> sortChronologically(TaskList taskList) {
        return taskList.sortChronologically();
    }
}
